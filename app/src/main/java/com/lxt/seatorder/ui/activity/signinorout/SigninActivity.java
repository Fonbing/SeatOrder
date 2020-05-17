package com.lxt.seatorder.ui.activity.signinorout;

import android.content.Intent;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.lxt.seatorder.R;
import com.lxt.seatorder.mvp.MVPBaseActivity;
import com.lxt.seatorder.utils.AppConfig;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zbar.ZBarView;

public class SigninActivity extends MVPBaseActivity<SignInOrOutContract.View, SignInOrOutPresenter> implements QRCodeView.Delegate {

    private static final int REQUEST_CODE_CHOOSE_QRCODE_FROM_GALLERY = 666;
    private ZBarView mZBarView;
    private android.widget.Switch mSwtLight;

    @Override
    protected void onStart() {
        super.onStart();
        mZBarView.startCamera(); // 打开后置摄像头开始预览，但是并未开始识别
//        mZBarView.startCamera(Camera.CameraInfo.CAMERA_FACING_FRONT); // 打开前置摄像头开始预览，但是并未开始识别
        mZBarView.startSpotAndShowRect(); // 显示扫描框，并开始识别
    }

    @Override
    protected void onStop() {
        mZBarView.stopCamera(); // 关闭摄像头预览，并且隐藏扫描框
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        mZBarView.onDestroy(); // 销毁二维码扫描控件
        super.onDestroy();
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        vibrate();
        int seatId = -1;
        Intent intent = null;
        try {

            seatId = Integer.parseInt(result.split("=")[1]);
            intent = new Intent();
            intent.putExtra("seatId", seatId);
//        mZBarView.startSpot(); // 开始识别
            setResult(1100, intent);
        } catch (NumberFormatException e) {
            showToast(result);
            e.printStackTrace();
        }
        finish();
    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        // 这里是通过修改提示文案来展示环境是否过暗的状态，接入方也可以根据 isDark 的值来实现其他交互效果
        String tipText = mZBarView.getScanBoxView().getTipText();
        String ambientBrightnessTip = "环境过暗，请打开闪光灯";
        if (isDark) {
            showToast(ambientBrightnessTip);
            mSwtLight.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        showToast("打开相机出错");
        Log.e(AppConfig.TAG, "打开相机出错");
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_signin;
    }

    @Override
    protected void initView() {
        mZBarView = findViewById(R.id.zbarview);
        mSwtLight = (Switch) findViewById(R.id.swt_light);
        setTopTitle("签到");
        addBackButton();
    }

    @Override
    protected void initListener() {
        mZBarView.setDelegate(this);
        mSwtLight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mZBarView.openFlashlight();
                } else {
                    mZBarView.closeFlashlight();
                }
            }
        });
    }

    @Override
    protected void initData() {

    }


}

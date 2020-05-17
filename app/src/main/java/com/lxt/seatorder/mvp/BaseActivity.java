package com.lxt.seatorder.mvp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.lxt.seatorder.MyApplication;
import com.lxt.seatorder.R;
import com.lxt.seatorder.utils.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Lxt Jxfen on 2019-12-11.
 * email: 1771874056@qq.com
 */
public class BaseActivity extends AppCompatActivity implements BaseView {
    private MaterialDialog mLoadingDialog;
    private Toast mToast;
    protected MyApplication mApp;
    protected Context mContext;
    private Dialog mBottomAlertDialog;
    private Dialog mBottomDialog;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.transparencyBar(this);         //设置状态栏全透明
//        StatusBarUtil.StatusBarLightMode(this);     //设置白底黑字
        mContext = this;
        mApp = (MyApplication) getApplication();
        mLoadingDialog = new MaterialDialog.Builder(this).
                widgetColorRes(R.color.colorPrimary).progress(true, 0).cancelable(true).build();

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(R.anim.view_fade_in, R.anim.view_fade_out);
    }


    //设置activity顶部标题
    protected void setTopTitle(String title) {
        ((TextView) findViewById(R.id.tv_main_title)).setText(title);
    }


    //设置activity回退按钮
    protected void addBackButton() {
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setVisibility(View.VISIBLE);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    //设置activity回退按钮
    protected void showNoDataText() {
        LinearLayout ll = findViewById(R.id.ll_no_data);
        ll.setVisibility(View.VISIBLE);
    }

    @Override
    public void showLoadingDialog() {
        if (!mLoadingDialog.isShowing()) {
            mLoadingDialog.setContent("数据加载中...");
            mLoadingDialog.show();
        }
    }

    @Override
    public void dismissLoadingDialog() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    @Override
    public void showToast(String msg) {
        if (mToast == null) {
            mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(msg);
        }
        mToast.show();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.view_fade_in, R.anim.view_fade_out);
    }

    public void startActivity(Class<?> activity) {
        Intent intent = new Intent(mContext, activity);
        startActivity(intent);
    }

    @Override
    public void showErr() {
        showToast(getResources().getString(R.string.api_error_msg));
    }

    @Override
    public Context getContext() {
        return this;
    }


    public void showBottomDialog(String dialogTitle, final String leftBtn, String rightBtn, View.OnClickListener leftBtnListener, View.OnClickListener rightBtnListener) {
        mBottomDialog = new Dialog(getContext(), R.style.BottomDialogStyle);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_bottom, null);

        TextView mTvDialogTitle = (TextView) view.findViewById(R.id.tv_dialog_title);
        TextView mTvLeftBtn = (TextView) view.findViewById(R.id.tv_left_btn);
        TextView mTvRightBtn = (TextView) view.findViewById(R.id.tv_right_btn);
        mTvDialogTitle.setText(dialogTitle);
        mTvLeftBtn.setText(leftBtn);
        mTvRightBtn.setText(rightBtn);

        mBottomDialog.setCancelable(false);
        mBottomDialog.setCanceledOnTouchOutside(false);
        mTvLeftBtn.setOnClickListener(leftBtnListener);
        if (rightBtnListener == null) {
            mTvRightBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mBottomDialog.dismiss();
                }
            });
        } else mTvRightBtn.setOnClickListener(rightBtnListener);

        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        view.setMinimumWidth((int) (width * 0.97));
        view.setMinimumHeight(height / 4);
        Window dialogWindow = mBottomDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        mBottomDialog.setContentView(view);
        mBottomDialog.show();
    }

    public void dismissBottomDialog() {
        if (mBottomDialog != null && mBottomDialog.isShowing()) {
            mBottomDialog.dismiss();
        }
    }

    protected void showBottomAlertDialog(String title, String line1, String line2, String finishBtn, final String leftBtn, String rightBtn, View.OnClickListener leftBtnListener) {
        mBottomAlertDialog = new Dialog(this, R.style.BottomDialogStyle);
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_bottom_alert, null);
        TextView mTvAlertTitle = view.findViewById(R.id.tv_alert_title);
        TextView mTvOrderTime = view.findViewById(R.id.tv_order_time);
        TextView mTvOrderRoom = view.findViewById(R.id.tv_order_room);
        TextView mTvFinishBtn = view.findViewById(R.id.tv_finish_btn);
        TextView mTvLeftBtn = view.findViewById(R.id.tv_left_btn);
        TextView mTvRightBtn = view.findViewById(R.id.tv_right_btn);
        LinearLayout mLlBottomBtn = findViewById(R.id.ll_bottom_btn);

        mTvAlertTitle.setText(title);
        mTvOrderTime.setText(line1);
        mTvOrderRoom.setText(line2);
        mTvFinishBtn.setText(finishBtn);
        mTvLeftBtn.setText(leftBtn);
        mTvRightBtn.setText(rightBtn);

        if ("预约成功".equals(title)) {
            mLlBottomBtn.setVisibility(View.GONE);
        } else mTvFinishBtn.setVisibility(View.GONE);

        mTvLeftBtn.setOnClickListener(leftBtnListener);
        mTvFinishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomAlertDialog.dismiss();
            }
        });
        mTvRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBottomAlertDialog.dismiss();
            }
        });
        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        view.setMinimumWidth((int) (width * 0.97));
        view.setMinimumHeight(height / 4);
        Window dialogWindow = mBottomAlertDialog.getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        mBottomAlertDialog.setContentView(view);
        mBottomAlertDialog.show();
    }

    protected void dismissBottomAlertDialog() {
        if (mBottomAlertDialog != null && mBottomAlertDialog.isShowing()) {
            mBottomAlertDialog.dismiss();
        }
    }


}

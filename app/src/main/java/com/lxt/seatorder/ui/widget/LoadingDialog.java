package com.lxt.seatorder.ui.widget;

import android.os.Bundle;

import com.lxt.seatorder.R;
import com.wang.avi.AVLoadingIndicatorView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by Lxt Jxfen on 2019-11-24.
 * email: 1771874056@qq.com
 * */
public class LoadingDialog extends AppCompatActivity {


    private com.wang.avi.AVLoadingIndicatorView mAviLoad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_loading);
    }

    private void initView() {
        mAviLoad = (AVLoadingIndicatorView) findViewById(R.id.avi_loading);
        mAviLoad.setIndicator("BallScaleRippleIndicator");
        mAviLoad.show();
    }
}

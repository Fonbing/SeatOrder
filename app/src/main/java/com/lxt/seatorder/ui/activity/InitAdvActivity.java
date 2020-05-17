package com.lxt.seatorder.ui.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.lxt.seatorder.R;
import com.lxt.seatorder.mvp.BaseActivity;
import com.lxt.seatorder.ui.activity.login.LoginActivity;
import com.lxt.seatorder.utils.SpUtils;

import java.util.Random;

/**
 * Created by Lxt Jxfen on 2020-05-09.
 * email: 1771874056@qq.com
 */

public class InitAdvActivity extends BaseActivity {
    private int[] picsLayout = {R.layout.layout_hello, R.layout.layout_hello2,
            R.layout.layout_hello3, R.layout.layout_hello4, R.layout.layout_hello5};
    private int i;
    private int count = 5;
    private Button mBtnSkip;
    private LinearLayout linearLayout;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                mBtnSkip.setText("跳过 (" + getCount() + ")");
                handler.sendEmptyMessageDelayed(0, 1000);
            }
        }
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Random r = new Random();
//        i = r.nextInt(5);
        i = 1;
        Log.i("lxt", "随机数是:" + i);
        if (i < 5) {
            //随机概率会出现广告页
            setContentView(R.layout.activity_adv);
            initView2();
        } else {
            if (SpUtils.isContains(mContext, "UserToken")) {
                startActivity(MainActivity.class);
            } else startActivity(LoginActivity.class);
            finish();
        }
    }

    private void initView2() {
        linearLayout = findViewById(R.id.advLine);
        View view = View.inflate(InitAdvActivity.this, picsLayout[i], null);
        linearLayout.addView(view);
        mBtnSkip = view.findViewById(R.id.btn_skip);
        mBtnSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (SpUtils.isContains(mContext, "UserToken")) {
                    startActivity(MainActivity.class);
                } else startActivity(LoginActivity.class);
                handler.removeMessages(0);
                finish();
            }
        });
        handler.sendEmptyMessageDelayed(0, 1000);
    }

    public int getCount() {
        count--;
        if (count == 0) {
            if (SpUtils.isContains(mContext, "UserToken")) {
                startActivity(MainActivity.class);
            } else startActivity(LoginActivity.class);
            finish();
        }
        return count;
    }
}

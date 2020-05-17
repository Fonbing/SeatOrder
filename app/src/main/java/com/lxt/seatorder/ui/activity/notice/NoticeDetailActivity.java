package com.lxt.seatorder.ui.activity.notice;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.lxt.seatorder.R;
import com.lxt.seatorder.mvp.BaseActivity;

public class NoticeDetailActivity extends BaseActivity {

    private TextView mTvNoticeContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_detail);
        initView();
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        String notice_content = intent.getStringExtra("notice_content");
        mTvNoticeContent.setText(notice_content);
    }

    private void initView() {
        mTvNoticeContent = (TextView) findViewById(R.id.tv_notice_content);
        addBackButton();
        setTopTitle("查看通知");
    }
}

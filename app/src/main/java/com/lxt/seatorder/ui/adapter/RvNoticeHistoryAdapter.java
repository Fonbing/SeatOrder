package com.lxt.seatorder.ui.adapter;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.Notice;
import com.lxt.seatorder.ui.activity.notice.NoticeDetailActivity;
import com.lxt.seatorder.utils.DateUtil;

import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * Created by Lxt Jxfen on 2020/4/1.
 * email: 1771874056@qq.com
 */
public class RvNoticeHistoryAdapter extends BGARecyclerViewAdapter<Notice> {


    private TextView mTvNoticeInfo;
    private TextView mTvNoticeTime;
    private TextView mTvNoticeLook;

    public RvNoticeHistoryAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_notice_record_activity);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, final Notice model) {
        initView(helper);
        mTvNoticeInfo.setText(model.getTitle());
        mTvNoticeTime.setText(DateUtil.dateToString(model.getCreateTime(), DateUtil.ALL));
        mTvNoticeLook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, NoticeDetailActivity.class);
                intent.putExtra("notice_content", model.getContent());
                mContext.startActivity(intent);
            }
        });
    }


    private void initView(BGAViewHolderHelper helper) {
        mContext = helper.getConvertView().getContext();
        mTvNoticeInfo = helper.getTextView(R.id.tv_notice_info);
        mTvNoticeTime = helper.getTextView(R.id.tv_notice_time);
        mTvNoticeLook = helper.getTextView(R.id.tv_notice_look);
    }
}

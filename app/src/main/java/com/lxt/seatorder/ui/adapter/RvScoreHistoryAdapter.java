package com.lxt.seatorder.ui.adapter;

import android.content.Context;
import android.widget.TextView;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.UserScore;
import com.lxt.seatorder.utils.DateUtil;

import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * Created by Lxt Jxfen on 2020/4/1.
 * email: 1771874056@qq.com
 */
public class RvScoreHistoryAdapter extends BGARecyclerViewAdapter<UserScore> {
    private TextView mTvScoreInfo;
    private TextView mTvScoreTime;
    private TextView mTvScoreNum;
    private Context mContext;


    public RvScoreHistoryAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_score_history_activity);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, UserScore model) {
        initView(helper);
        mTvScoreInfo.setText(model.getReason());
        mTvScoreTime.setText(DateUtil.millsecondsToStrDate(model.getCreateTime(),DateUtil.ALL));
        mTvScoreNum.setText(model.getType() == 1 ? "+" + model.getScore() : "-" + model.getScore());
    }


    private void initView(BGAViewHolderHelper helper) {
        mContext = helper.getConvertView().getContext();
        mTvScoreInfo = helper.getView(R.id.tv_score_info);
        mTvScoreTime = helper.getView(R.id.tv_score_time);
        mTvScoreNum = helper.getView(R.id.tv_score_num);
    }
}

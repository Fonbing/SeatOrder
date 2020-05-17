package com.lxt.seatorder.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.OrderRecord;
import com.lxt.seatorder.ui.activity.recorddetail.RecordDetailActivity;
import com.lxt.seatorder.ui.widget.CircleTextView;
import com.lxt.seatorder.utils.DateUtil;
import com.lxt.seatorder.utils.OrderTypeUtil;

import androidx.recyclerview.widget.RecyclerView;
import cn.bingoogolapple.baseadapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.baseadapter.BGAViewHolderHelper;

/**
 * Created by Lxt Jxfen on 2020/4/1.
 * email: 1771874056@qq.com
 */
public class RvOrderHistoryAdapter extends BGARecyclerViewAdapter<OrderRecord> {
    private Context mContext;
    private TextView mTvRecordTime;
    private TextView mTvRecordRoom;
    private TextView mTvRecordStatus;
    //    private CircleTextImage mCtiRecord;
    private CircleTextView mCtvbRecord;

    public RvOrderHistoryAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_order_record_fragment);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, final OrderRecord record) {
        initView(helper);
        helper.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, RecordDetailActivity.class);
                intent.putExtra("itemId",record.getOrderItemId());
                mContext.startActivity(intent);
            }
        });
        String time = DateUtil.millsecondsToStrDate(record.getStartTime(), DateUtil.ALL) + "-" + DateUtil.millsecondsToStrDate(record.getEndTime(), DateUtil.HOUR_MINUTE);
        String roomName = record.getBuildName() + "-" + record.getFloorName() + "-" + record.getResourceDisplayName();
        mTvRecordTime.setText(time);
        mCtvbRecord.setText(record.getBuildName());
        mTvRecordRoom.setText(roomName);
        mTvRecordStatus.setText(OrderTypeUtil.getStatus(record.getItemStatus()));
        mTvRecordStatus.setTextColor(OrderTypeUtil.getStatusColor(mContext, record.getItemStatus()));
    }

    private void initView(BGAViewHolderHelper helper) {
        mContext = helper.getConvertView().getContext();
//        mCtiRecord = helper.getView(R.id.cti_record_img);
        mCtvbRecord = helper.getView(R.id.ctv_record_img);
        mTvRecordTime = helper.getView(R.id.tv_record_time);
        mTvRecordRoom = helper.getView(R.id.tv_record_room);
        mTvRecordStatus = helper.getView(R.id.tv_record_status);
    }
}

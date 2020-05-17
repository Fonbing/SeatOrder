package com.lxt.seatorder.ui.activity.recorddetail;


import android.content.Intent;
import android.view.View;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.OrderRecord;
import com.lxt.seatorder.bean.RecordDetail;
import com.lxt.seatorder.mvp.MVPBaseActivity;
import com.lxt.seatorder.ui.adapter.NodeProgressAdapter;
import com.lxt.seatorder.ui.widget.CircleTextView;
import com.lxt.seatorder.utils.DateUtil;
import com.lxt.seatorder.utils.OrderTypeUtil;
import com.lxt.seatorder.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class RecordDetailActivity extends MVPBaseActivity<RecordDetailContract.View, RecordDetailPresenter> implements RecordDetailContract.View {

    private android.widget.TextView mTvRecordVenue;
    private android.widget.TextView mTvRecordSeat;
    private android.widget.TextView mTvRecordTime;
    private com.lxt.seatorder.ui.widget.NodeProgressView mNpvRecordProgress;
    private List<RecordDetail> mRecordDetails;
    private android.widget.LinearLayout mLlSignIn;
    private android.widget.ImageView mIvBack;
    private android.widget.TextView mTvMainTitle;
    private android.widget.LinearLayout mLlSignOut;
    private com.lxt.seatorder.ui.widget.CircleTextView mCtvRecordSignin;
    private com.lxt.seatorder.ui.widget.CircleTextView mCtvRecordCancel;
    private com.lxt.seatorder.ui.widget.CircleTextView mCtvRecordSignout;
    private CircleTextView mCtvRecordFinish;
    private int mItemId;

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_history_detail;
    }

    @Override
    protected void initView() {
        setTopTitle("预约详情");
        addBackButton();
        mTvRecordVenue = findViewById(R.id.tv_record_venue);
        mTvRecordSeat = findViewById(R.id.tv_record_seat);
        mTvRecordTime = findViewById(R.id.tv_record_time);
        mNpvRecordProgress = findViewById(R.id.npv_record_progress);
        mCtvRecordCancel = findViewById(R.id.ctv_record_cancel);
        mCtvRecordFinish = findViewById(R.id.ctv_record_finish);
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        mItemId = intent.getIntExtra("itemId", -1);
        showLoadingDialog();
        mPresenter.getRecordDetail(mItemId);
    }

    @Override
    public void getRecordDetailSuccess(OrderRecord record) {
        String time = DateUtil.millsecondsToStrDate(record.getStartTime(), DateUtil.ALL) + "-" + DateUtil.millsecondsToStrDate(record.getEndTime(), DateUtil.HOUR_MINUTE);
        String venueName = record.getBuildName() + "-" + record.getFloorName();
        final List<OrderRecord.BehaviorRecordsBean> behaviorRecords = record.getBehaviorRecords();
        mRecordDetails = new ArrayList<>();
        setStatusBtn(record.getItemStatus());
        for (int i = behaviorRecords.size() - 1; i >= 0; i--) {
            RecordDetail r = new RecordDetail();
            OrderRecord.BehaviorRecordsBean br = behaviorRecords.get(i);
            r.setTime(DateUtil.millsecondsToStrDate(br.getCreateTimeX(), DateUtil.ALL));
            r.setContext(OrderTypeUtil.getStatus(br.getBehaviorType()) + "成功");
            mRecordDetails.add(r);
        }
        mTvRecordVenue.setText(venueName);
        mTvRecordTime.setText(time);
        mTvRecordSeat.setText(record.getResourceDisplayName());
        mNpvRecordProgress.setNodeProgressAdapter(new NodeProgressAdapter() {
            @Override
            public int getCount() {
                return mRecordDetails.size();
            }

            @Override
            public List<RecordDetail> getData() {
                return mRecordDetails;
            }
        });
        dismissLoadingDialog();
    }

    @Override
    public void getRecordDetailFail(String msg) {
        showToast(msg);
    }

    @Override
    public void canceleOrderSucc(String msg) {
        dismissLoadingDialog();
        showToast(msg);
    }

    @Override
    public void cancelOrderFail(String msg) {
        dismissLoadingDialog();
        showToast(msg);
    }

    private void setStatusBtn(int itemStatus) {
        switch (itemStatus) {
            case 11:
                mCtvRecordCancel.setVisibility(View.VISIBLE);
                mCtvRecordCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showLoadingDialog();
                        mPresenter.cancelOrder(mItemId, 61, String.valueOf(SpUtils.get(mContext, "username", "")));
                        mCtvRecordCancel.setVisibility(View.GONE);
                        mCtvRecordFinish.setVisibility(View.VISIBLE);
                        mCtvRecordFinish.setText(OrderTypeUtil.getStatus(61));
                    }
                });
                mCtvRecordFinish.setVisibility(View.GONE);
                break;
            default:
                mCtvRecordCancel.setVisibility(View.GONE);
                mCtvRecordFinish.setVisibility(View.VISIBLE);
                mCtvRecordFinish.setText(OrderTypeUtil.getStatus(itemStatus));
                break;
        }
    }


}

package com.lxt.seatorder.ui.activity.room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.RoomInfo;
import com.lxt.seatorder.bean.RoomOrderInfo;
import com.lxt.seatorder.mvp.MVPBaseActivity;
import com.lxt.seatorder.ui.adapter.RvRoomAdapter;
import com.lxt.seatorder.utils.AppConfig;
import com.lxt.seatorder.utils.DensityUtil;
import com.lxt.seatorder.utils.ItemDecoration;
import com.lxt.seatorder.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RoomActivity extends MVPBaseActivity<RoomContract.View, RoomPresenter> implements RoomContract.View {

    private TextView mTvMainTitle;
    private RecyclerView mRvRoom;
    private List<RoomInfo> mRoomInfoList = new ArrayList<>();
    private RvRoomAdapter mRvRoomAdapter;
    private HorizontalScrollView mHsvRoom;
    private LinearLayout mLlRoom;
    private TextView mTvRoomVenue;
    private TextView mTvRoomTime;
    private LinearLayout mLlRoomSeat;
    private TextView mTvRoomSeat;
    private Button mBtnOkSeat;
    private ImageView mIvBack;
    private LinearLayout mLlRoomFloor;
    private RadioGroup mRgFloor;
    private RadioButton mRbThreeFloor;
    private RadioButton mRbFourFloor;
    private RadioButton mRbFiveFloor;
    private int mRoomId;
    private String mETime;
    private String mSTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_room;
    }

    @Override
    protected void initView() {
        mRvRoom = findViewById(R.id.rv_room);
//        mHsvRoom = findViewById(R.id.hsv_room);
        mLlRoom = findViewById(R.id.ll_room);
        mTvRoomVenue = findViewById(R.id.tv_room_venue);
        mTvRoomTime = findViewById(R.id.tv_room_time);
        mTvRoomSeat = findViewById(R.id.tv_room_seat);
        mBtnOkSeat = findViewById(R.id.btn_ok_seat);
        mLlRoomFloor = findViewById(R.id.ll_room_floor);
        mRgFloor = findViewById(R.id.rg_floor);
        mRbThreeFloor = findViewById(R.id.rb_three_floor);
        mRbFourFloor = findViewById(R.id.rb_four_floor);
        mRbFiveFloor = findViewById(R.id.rb_five_floor);

        addBackButton();
        setTopTitle("选择座位");

////        GestureViewBinder bind = GestureViewBinder.bind(getContext(), mHsvRoom, mRvRoom);
////        bind.setFullGroup(true);

    }

    @Override
    protected void initListener() {
        mBtnOkSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();

                intent.putExtra("seat_name", mTvRoomSeat.getText().toString());
                intent.putExtra("seat_id", (int) mTvRoomSeat.getTag());
                setResult(1001, intent);
                finish();
            }
        });
    }

    @Override
    protected void initData() {
        Intent i = getIntent();
        mTvRoomVenue.setText(i.getStringExtra("order_venue"));
        mTvRoomTime.setText(i.getStringExtra("order_time"));
        mRoomId = i.getIntExtra("room_id", 0);
        mSTime = i.getStringExtra("sTime");
        mETime = i.getStringExtra("eTime");
        mLlRoomFloor.setVisibility(View.GONE);

        mPresenter.getRoomInfo(mRoomId);

    }

    private void TestData() {
        RoomInfo mRoomInfo = null;
        int x = 0;
        for (int i = 0; i < 23; i++) {
            mRoomInfo = new RoomInfo();
            mRoomInfo.setDirName(String.format("%03d", i + 1));
            List<RoomInfo.Seat> seatList = new ArrayList<>();
            char c = 'A';
            for (int j = 0; j < 4; j++) {
                RoomInfo.Seat seat = new RoomInfo.Seat();
                seat.setId(x++);
                seat.setResourceSysName(String.valueOf(c++));
                seat.setResourceDisplayName(String.format("%03d", i + 1) + "-" + (char) (c - 1));
                seatList.add(seat);
            }
            mRoomInfo.setResources(seatList);
//        mAddressInfo.setDirName("回廊楼层");
//        mAddressInfo.setDirName("图书馆二层");
            mRoomInfoList.add(mRoomInfo);
        }
    }

    @Override
    public void getRoomOrderInfoSuccess(List<RoomOrderInfo> roomOrderInfoList) {

        for (RoomInfo ri :
                mRoomInfoList) {
            for (RoomInfo.Seat seat
                    : ri.getResources()) {
                for (RoomOrderInfo roi :
                        roomOrderInfoList) {
                    if (seat.getResourceId() == roi.getResourceId()) {
                        seat.setSeatStatus(roi.getResource().getSeatStatus());
                    }
                }
            }

        }
        mRvRoomAdapter = new RvRoomAdapter(mRvRoom, mRoomInfoList, this);
        mRvRoomAdapter.setData(mRoomInfoList);
        final GridLayoutManager gm = new GridLayoutManager(getContext(), 5);
        gm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position == 15)
                    return 5;
                else return 1;
            }
        });
        mRvRoom.addItemDecoration(new ItemDecoration(DensityUtil.dip2px(mContext, 10), 30));
        mRvRoom.setLayoutManager(gm);
        mRvRoom.setAdapter(mRvRoomAdapter);

    }

    @Override
    public void getRoomOrderInfoFail(String msg) {
        showToast(msg);
    }

    @Override
    public void getRoomInfoSuccess(List<RoomInfo> roomInfoList) {
        mRoomInfoList.clear();
        mRoomInfoList.addAll(roomInfoList);

        JsonObject jsonObject = new JsonObject();

        jsonObject.addProperty("startTime", mSTime + ":00");
        jsonObject.addProperty("endTime", mETime + ":00");
        JsonArray list = new JsonArray();
        list.add(jsonObject);
        String s = list.toString();
        Log.i(AppConfig.TAG, "getRoomInfoSuccess: " + s);

        mPresenter.getRoomOrderInfo(mRoomId, ((String) SpUtils.get(mContext, "username", "")), s);

    }

    @Override
    public void getRoomInfoFail(String msg) {

    }
}

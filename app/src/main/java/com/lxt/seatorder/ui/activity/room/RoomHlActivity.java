package com.lxt.seatorder.ui.activity.room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
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
import com.lxt.seatorder.ui.adapter.RvRoomHlAdapter;
import com.lxt.seatorder.utils.AppConfig;
import com.lxt.seatorder.utils.ItemDecoration;
import com.lxt.seatorder.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.lxt.seatorder.utils.AppConfig.TAG;

public class RoomHlActivity extends MVPBaseActivity<RoomContract.View, RoomPresenter> implements View.OnClickListener, RoomContract.View {

    private TextView mTvMainTitle;
    private List<RoomInfo> mRoomInfoListAll = new ArrayList<>();

    private List<RoomInfo> mRoomInfoList1 = new ArrayList<>();
    private List<RoomInfo> mRoomInfoList2 = new ArrayList<>();
    private List<RoomInfo> mRoomInfoList3 = new ArrayList<>();

    private RvRoomHlAdapter mRvRoomHlAdapterLeft;
    private RvRoomHlAdapter mRvRoomHlAdapterRight;
    private RvRoomHlAdapter mRvRoomHlAdapterMiddle;
    private HorizontalScrollView mHsvRoom;
    private LinearLayout mLlRoom;
    private TextView mTvRoomVenue;
    private TextView mTvRoomTime;
    private TextView mTvRoomSeat;
    private Button mBtnOkSeat;
    private View tempV;
    private RecyclerView mRvRoomLeft;
    private RecyclerView mRvRoomMiddle;
    private RecyclerView mRvRoomRight;
    private TextView mTvRoomFloor;
    private LinearLayout mLlRoomFllor;
    private RadioGroup mRgFloor;
    private RadioButton mRbThreeFloor;
    private RadioButton mRbFourFloor;
    private RadioButton mRbFiveFloor;
    private int mRoomId;
    private List<RoomInfo> floorListThree;
    private List<RoomInfo> floorListFour;
    private List<RoomInfo> floorListFive;
    private String mSTime;
    private String mETime;

    private enum Floor {
        THREE, FOUR, FIVE
    }

    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_room_hl;
    }

    @Override
    protected void initView() {
        setTopTitle("选择座位");
        addBackButton();
        mHsvRoom = findViewById(R.id.hsv_room);
        mLlRoom = findViewById(R.id.ll_room);
        mTvRoomVenue = findViewById(R.id.tv_room_venue);
        mTvRoomTime = findViewById(R.id.tv_room_time);
        mTvRoomSeat = findViewById(R.id.tv_room_seat);
        mBtnOkSeat = findViewById(R.id.btn_ok_seat);
        mRvRoomLeft = findViewById(R.id.rv_room_left);
        mRvRoomMiddle = findViewById(R.id.rv_room_middle);
        mRvRoomRight = findViewById(R.id.rv_room_right);
        mTvRoomFloor = findViewById(R.id.tv_room_floor);
        mLlRoomFllor = findViewById(R.id.ll_room_floor);
        mRgFloor = findViewById(R.id.rg_floor);
        mRbThreeFloor = findViewById(R.id.rb_three_floor);
        mRbFourFloor = findViewById(R.id.rb_four_floor);
        mRbFiveFloor = findViewById(R.id.rb_five_floor);

        mRbThreeFloor.setChecked(true);

        tempV = mTvRoomSeat;

//        TestData(56);
//        initRecyclerView();


//        scaleWindow();
//        GestureViewBinder bind = GestureViewBinder.bind(getContext(), mHsvRoom, mRvRoomLeft);
//        bind.setFullGroup(true);


    }

    private void TestData(int count) {
        mRoomInfoList1.clear();
        mRoomInfoList2.clear();
        mRoomInfoList3.clear();
        TestData1();
        TestData2(count);
        TestData3();
    }


    private void initRecyclerView() {
        initLeftRecyclerView();
        initMiddleRecyclerView();
        initRightRecyclerView();
    }

    private void initLeftRecyclerView() {
        mRvRoomHlAdapterLeft = new RvRoomHlAdapter(mRvRoomLeft, mRoomInfoList1, this, new int[]{R.layout.item_desk_four});
        mRvRoomHlAdapterLeft.setData(mRoomInfoList1);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        setRecyclerView(mRvRoomLeft, mRvRoomHlAdapterLeft, llm, new ItemDecoration(0, 70));
    }


    private void initMiddleRecyclerView() {
        mRvRoomHlAdapterMiddle = new RvRoomHlAdapter(mRvRoomMiddle, mRoomInfoList2, this, new int[]{R.layout.item_desk_one});
//        mRvRoomLeft.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvRoomHlAdapterMiddle.setData(mRoomInfoList2);
        final GridLayoutManager gm = new GridLayoutManager(getContext(), 18) {
            @Override
            public boolean canScrollVertically() {  //recycleview不再滑动
                return false;
            }
        };

        gm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (position >= 18 && position < mRoomInfoList2.size() - 18 && position % 2 == 0) {
                    return 17;
                } else return 1;
            }
        });

        setRecyclerView(mRvRoomMiddle, mRvRoomHlAdapterMiddle, gm, new ItemDecoration(0, 0));

    }


    private void initRightRecyclerView() {
        mRvRoomHlAdapterRight = new RvRoomHlAdapter(mRvRoomRight, mRoomInfoList3, this, new int[]{R.layout.item_desk_four});
        mRvRoomHlAdapterRight.setData(mRoomInfoList3);
        final LinearLayoutManager llm = new LinearLayoutManager(getContext(), LinearLayout.VERTICAL, false) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        setRecyclerView(mRvRoomRight, mRvRoomHlAdapterRight, llm, new ItemDecoration(0, 50));
    }


    private void setRecyclerView(RecyclerView rv, RvRoomHlAdapter rvRoomHlAdapter, RecyclerView.LayoutManager lm, ItemDecoration itemSpaceing) {
        rv.setNestedScrollingEnabled(false);
        rv.setHasFixedSize(false);
        rv.setFocusable(false);
        rv.addItemDecoration(itemSpaceing);
        rv.setItemViewCacheSize(10);
        rv.setLayoutManager(lm);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.getItemAnimator().setChangeDuration(0);
        rv.setAdapter(rvRoomHlAdapter);
    }


    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_three_floor:
                    updateData(Floor.THREE);
                    mTvRoomFloor.setText("回廊3层");
                    break;
                case R.id.rb_four_floor:
                    updateData(Floor.FOUR);
                    mTvRoomFloor.setText("回廊4层");
                    break;
                case R.id.rb_five_floor:
                    updateData(Floor.FIVE);
                    mTvRoomFloor.setText("回廊5层");
                    break;
                default:
                    break;
            }
        }
    };

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
        mRgFloor.setOnCheckedChangeListener(mOnCheckedChangeListener);
    }

    @Override
    protected void initData() {
        Intent i = getIntent();
        mTvRoomVenue.setText(i.getStringExtra("order_venue"));
        mTvRoomTime.setText(i.getStringExtra("order_time"));
        mRoomId = i.getIntExtra("room_id", 0);
        mSTime = i.getStringExtra("sTime");
        mETime = i.getStringExtra("eTime");
        mPresenter.getRoomInfo(mRoomId);
    }

    private void TestData1() {
        RoomInfo mRoomInfo = null;
        int x = 0;
        for (int i = 0; i < 4; i++) {
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
            mRoomInfoList1.add(mRoomInfo);
        }

    }

    private void TestData2(int count) {
        RoomInfo mRoomInfo = null;
        int x = 0;
        for (int i = 0; i < count; i++) {
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
            mRoomInfoList2.add(mRoomInfo);
        }

    }

    private void TestData3() {
        RoomInfo mRoomInfo = null;
        int x = 0;
        for (int i = 0; i < 5; i++) {
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
            mRoomInfoList3.add(mRoomInfo);
        }

    }

    @Override
    public void onClick(View view) {
        if (view instanceof TextView) {
            TextView v = (TextView) (view);
            Log.i(TAG, "onClick: " + v.getText());
            tempV.setEnabled(true);
            tempV = view;
            view.setEnabled(false);
            mBtnOkSeat.setEnabled(true);
            mBtnOkSeat.setText("确认选座");
            RoomInfo.Seat seat1 = (RoomInfo.Seat) view.getTag();
            mTvRoomSeat.setTag(seat1.getId());
            showToast(String.valueOf(seat1.getId()));
            mTvRoomSeat.setText(seat1.getResourceDisplayName());
        }
    }


    @Override
    public void getRoomOrderInfoSuccess(List<RoomOrderInfo> roomOrderInfoList) {
        for (RoomInfo ri :
                mRoomInfoListAll) {
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
        floorListThree = new ArrayList<>(mRoomInfoListAll.subList(0, 64));
        floorListFour = new ArrayList<>(mRoomInfoListAll.subList(64, 110));
        floorListFive = new ArrayList<>(mRoomInfoListAll.subList(110, 156));

        addFloorData(Floor.THREE);
        initRecyclerView();
    }

    private void addFloorData(Floor floor) {
        mRoomInfoList1.clear();
        mRoomInfoList2.clear();
        mRoomInfoList3.clear();
        GridLayoutManager gm = (GridLayoutManager) mRvRoomMiddle.getLayoutManager();
        if (gm == null) {
            gm = new GridLayoutManager(getContext(), 18);
        }
        switch (floor) {
            case THREE:
                List<RoomInfo> rooms = floorListThree.subList(0, 56);
                mRoomInfoList2.addAll(rooms.subList(0, 18));
                List<RoomInfo> rooms3 = rooms.subList(18, 39);
                int i;
                for (i = 0; i < rooms3.size() / 2; i++) {
                    mRoomInfoList2.add(rooms3.get(i));
                    mRoomInfoList2.add(rooms3.get(i + 11));
                }
                mRoomInfoList2.add(rooms3.get(i));
                mRoomInfoList2.addAll(rooms.subList(39, 56));

                mRoomInfoList1.addAll(floorListThree.subList(floorListThree.size() - 8, floorListThree.size() - 4));
                mRoomInfoList3.addAll(floorListThree.subList(floorListThree.size() - 4, floorListThree.size()));
                mRoomInfoList3.add(0, mRoomInfoListAll.get(188));
                gm.setSpanCount(18);
                gm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position >= 18 && position < mRoomInfoList2.size() - 18 && position % 2 == 0) {
                            return 17;
                        } else return 1;
                    }
                });
                break;
            case FOUR:
                mRoomInfoList2.add(floorListFour.get(0));
                mRoomInfoList2.add(mRoomInfoListAll.get(75));
                mRoomInfoList2.addAll(mRoomInfoListAll.subList(156, 172));
                List<RoomInfo> rooms4 = floorListFour.subList(1, 21);
                for (i = 0; i < (rooms4.size() - 1) / 2; i++) {
                    mRoomInfoList2.add(rooms4.get(i));
                    mRoomInfoList2.add(rooms4.get(i + 11));
                }
                mRoomInfoList2.add(rooms4.get(i));
                mRoomInfoList2.addAll(mRoomInfoListAll.subList(85, 102));
                mRoomInfoList2.addAll(mRoomInfoListAll.subList(191, 193));

                mRoomInfoList1.addAll(floorListFour.subList(floorListFour.size() - 8, floorListFour.size() - 4));
                mRoomInfoList3.addAll(floorListFour.subList(floorListFour.size() - 4, floorListFour.size()));
                gm.setSpanCount(20);
                gm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position >= 18 && position < (mRoomInfoList2.size() - 20)) {
                            if (position % 2 == 0)
                                return 17;
                            else return 3;
                        } else return 1;
                    }
                });

//                setRecyclerView(mRvRoomMiddle, mRvRoomHlAdapterMiddle, gm, new ItemDecoration(0, 0));
                break;
            case FIVE:
                mRoomInfoList2.addAll(mRoomInfoListAll.subList(189, 191));
                mRoomInfoList2.add(floorListFive.get(0));
                mRoomInfoList2.add(mRoomInfoListAll.get(121));
                mRoomInfoList2.addAll(mRoomInfoListAll.subList(172, 188));
                List<RoomInfo> rooms5 = floorListFive.subList(1, 21);
                for (i = 0; i < (rooms5.size() - 1) / 2; i++) {
                    for (int j = 0; j < 3; j++) {
                        mRoomInfoList2.add(rooms5.get(i));   //占位
                    }
                    mRoomInfoList2.add(rooms5.get(i + 11));
                }
                for (int j = 0; j < 3; j++) {
                    mRoomInfoList2.add(rooms5.get(i));   //占位
                }
                mRoomInfoList2.addAll(floorListFive.subList(21, 38));

                mRoomInfoList1.addAll(floorListFive.subList(floorListFive.size() - 8, floorListFive.size() - 4));
                mRoomInfoList3.addAll(floorListFive.subList(floorListFive.size() - 4, floorListFive.size()));

                gm.setSpanCount(20);
                gm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if (position >= 20 && position < (mRoomInfoList2.size() - 18)) {
                            if (position % 4 == 2)
                                return 17;
                            else return 1;
                        } else return 1;
                    }
                });
                break;
        }


    }

    private void updateData(Floor floor) {
        addFloorData(floor);

        mRvRoomHlAdapterLeft.setData(mRoomInfoList1);
        mRvRoomHlAdapterMiddle.setData(mRoomInfoList2);
        mRvRoomHlAdapterRight.setData(mRoomInfoList3);

//        mRvRoomHlAdapterLeft.notifyDataSetChanged();
//        mRvRoomHlAdapterMiddle.notifyDataSetChanged();
//        mRvRoomHlAdapterRight.notifyDataSetChanged();
    }

    @Override
    public void getRoomOrderInfoFail(String msg) {

    }

    @Override
    public void getRoomInfoSuccess(List<RoomInfo> roomInfoList) {
        mRoomInfoListAll.clear();
        mRoomInfoListAll.addAll(roomInfoList);

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

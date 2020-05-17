package com.lxt.seatorder.ui.fragment.order;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.contrarywind.view.WheelView;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.OrderInfo;
import com.lxt.seatorder.bean.OrderRecord;
import com.lxt.seatorder.bean.UserToken;
import com.lxt.seatorder.mvp.MVPBaseFragment;
import com.lxt.seatorder.ui.activity.room.RoomActivity;
import com.lxt.seatorder.ui.activity.room.RoomHlActivity;
import com.lxt.seatorder.utils.AppConfig;
import com.lxt.seatorder.utils.DateUtil;
import com.lxt.seatorder.utils.SpUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import androidx.annotation.Nullable;

import static com.lxt.seatorder.utils.AppConfig.ENDTIME;
import static com.lxt.seatorder.utils.AppConfig.STARTTIME;


/**
 * Created by Lxt Jxfen on 2019-11-24.
 * email: 1771874056@qq.com
 */
public class OrderFragment extends MVPBaseFragment<OrderContract.View, OrderPresenter> implements OrderContract.View, View.OnClickListener {

    private TextView mTvSelDate;
    private TextView mTvSelStartTime;
    private TextView mTvSelEndTime;
    private TextView mTvOrderHour;
    private TextView mTvSelSeat;
    private Button mBtnOkOrder;
    private TextView mTvHint;
    private TextView mTvSelVenue;
    private int roomId;
    private int mSeatId;
    private OptionsPickerView<String> mPvNoLinkOptions;
    private List<String> veneList = new ArrayList<>();
    private SparseArray veneMap = new SparseArray();
    private List<String> dateList = new ArrayList<>();
    private List<String> timeList = new ArrayList<>();
    private OrderRecord mOrderRecord;

    @Override
    public void onResume() {
        super.onResume();
        if (!mTvSelSeat.getText().toString().equals("请选择")) {
            mBtnOkOrder.setEnabled(true);
            mBtnOkOrder.setText("立即预约");
        } else {
            mBtnOkOrder.setEnabled(false);
            mBtnOkOrder.setText("请先选座");
        }
    }


    @Override
    public int getLayoutResourceID() {
        return R.layout.fragment_order;
    }

    @Override
    protected void initView() {
        mTvSelDate = findViewById(R.id.tv_sel_date);
        mTvSelStartTime = findViewById(R.id.tv_sel_Start_time);
        mTvSelEndTime = findViewById(R.id.tv_sel_end_time);
        mTvOrderHour = findViewById(R.id.tv_order_hour);
        mTvSelSeat = findViewById(R.id.tv_sel_seat);
        mTvHint = findViewById(R.id.tv_hint);
        mBtnOkOrder = findViewById(R.id.btn_ok_order);
        mTvSelVenue = findViewById(R.id.tv_sel_venue);
        initVeneData();
        initDateTimeData();
        initNoLinkOptionsPicker();
        initViewData();
//        initDatePickerDialog();
//        initTimePickerDialog();
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }


    private void initVeneData() {
        veneList.add("图书馆-学术研究中心");
        veneList.add("图书馆-回廊楼层");
        veneList.add("利行楼-一层");
        veneList.add("利行楼-二层");
        veneList.add("利行楼-三层");
        veneMap.put(2700, veneList.get(0));
        veneMap.put(2725, veneList.get(1));
        veneMap.put(2916, veneList.get(2));
        veneMap.put(2963, veneList.get(3));
        veneMap.put(3027, veneList.get(4));

    }


    @SuppressLint("DefaultLocale")
    private void initDateTimeData() {
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();
        endDate.set(Calendar.DAY_OF_MONTH, endDate.get(Calendar.DAY_OF_MONTH) + 1);
        dateList.add(DateUtil.calendarTOString(startDate, DateUtil.YEAR_MONTH_DAY));
        dateList.add(DateUtil.calendarTOString(endDate, DateUtil.YEAR_MONTH_DAY));
        for (int i = STARTTIME; i < ENDTIME + 1; i++) {
            timeList.add(String.format("%02d", i) + ":00");
        }
    }


    @Override
    public void initListener() {
        mTvHint.setSelected(true);
        mTvSelVenue.setOnClickListener(this);
        mTvSelDate.setOnClickListener(this);
        mTvSelStartTime.setOnClickListener(this);
        mTvSelEndTime.setOnClickListener(this);
        mTvSelSeat.setOnClickListener(this);
        mBtnOkOrder.setOnClickListener(this);

    }

    private void initViewData() {
        mTvSelVenue.setText(String.valueOf(veneMap.valueAt(0)));
        mTvSelDate.setText(DateUtil.calendarTOString(null, DateUtil.YEAR_MONTH_DAY));
        roomId = veneMap.keyAt(0);
        if (SpUtils.isContains(mActivity, "orderRecordList")) {
            mOrderRecord = SpUtils.getList(mActivity, "orderRecordList", OrderRecord.class).get(0);
            String venue = mOrderRecord.getBuildName() + "-" + mOrderRecord.getFloorName();
            mTvSelVenue.setText(venue);
            mTvSelSeat.setText(mOrderRecord.getResourceDisplayName());
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        long startT = calendar.getTimeInMillis();
        calcEndTime(startT);
    }

    private void calcEndTime(long startT) {
        long hour_6 = AppConfig.MAX_ORDER_H * 60 * 60 * 1000;
        long endT = startT + hour_6;
        Calendar startTime = Calendar.getInstance();
        Calendar endTime = Calendar.getInstance();
        startTime.set(Calendar.HOUR_OF_DAY, AppConfig.STARTTIME);
        startTime.set(Calendar.MINUTE, 0);
        endTime.set(Calendar.HOUR_OF_DAY, AppConfig.ENDTIME);
        endTime.set(Calendar.MINUTE, 0);
        mTvSelStartTime.setText(DateUtil.millsecondsToStrDate(startT, DateUtil.HOUR_MINUTE));
        mTvSelEndTime.setText(DateUtil.millsecondsToStrDate(endT, DateUtil.HOUR_MINUTE));
        if (endT > endTime.getTimeInMillis() || startT >= endTime.getTimeInMillis()) {
            endT = endTime.getTimeInMillis();
            mTvSelStartTime.setText(DateUtil.millsecondsToStrDate(startT, DateUtil.HOUR_MINUTE));
            mTvSelEndTime.setText(DateUtil.millsecondsToStrDate(endT, DateUtil.HOUR_MINUTE));
        }
        mTvOrderHour.setText(getTimeDifference(startT, endT));
    }

    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        mPvNoLinkOptions = new OptionsPickerBuilder(getContext(), new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int i, int i1, int i2, View view) {
                switch (view.getId()) {
                    case R.id.tv_sel_venue:
                        ((TextView) view).setText(veneList.get(i));
                        roomId = veneMap.keyAt(i);
                        break;
                    case R.id.tv_sel_date:
                        ((TextView) view).setText(dateList.get(i));
                        break;
                    case R.id.tv_sel_Start_time:
                    case R.id.tv_sel_end_time:
                        ((TextView) view).setText(timeList.get(i));
                        String sTm = mTvSelStartTime.getText().toString();
                        long sMill = DateUtil.strDateToMillseconds(sTm, DateUtil.HOUR_MINUTE);
                        if (view.getId() == R.id.tv_sel_Start_time)
                            calcEndTime(sMill);
                        String eTm = mTvSelEndTime.getText().toString();
                        long eMill = DateUtil.strDateToMillseconds(eTm, DateUtil.HOUR_MINUTE);
                        mTvOrderHour.setText(getTimeDifference(sMill, eMill));
                        break;
                }
            }
        })
                .setTitleText("选择场馆")//标题文字
                .setContentTextSize(20)//滚轮文字大小
                .setTitleSize(18)//标题文字大小
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .setTitleColor(Color.WHITE)//标题文字颜色
                .setSubmitColor(Color.WHITE)//确定按钮文字颜色
                .setCancelColor(Color.WHITE)//取消按钮文字颜色
                .setTitleBgColor(getResources().getColor(R.color.colorPrimary))//标题背景颜色 Night mode
                .setBgColor(Color.WHITE)//滚轮背景颜色 Night mode
                .setItemVisibleCount(5)
                .isNeedFormatInt(true)
                .setTextColorCenter(Color.RED)
                .setDividerColor(getResources().getColor(R.color.colorPrimary))
                .setTextColorOut(getResources().getColor(R.color.darkgray))
                .setDividerType(WheelView.DividerType.FILL)
                .setItemVisibleCount(5)
                .setSelectOptions(0)
                .build();
    }

    private void setOptionsPicker(String title, String selectText, List<String> list) {
        mPvNoLinkOptions.setTitleText(title);
        mPvNoLinkOptions.setSelectOptions(list.indexOf(selectText));
        mPvNoLinkOptions.setPicker(list);
    }


    @Override
    public void onClick(View view) {
        String str = "";
        if (view instanceof TextView) {
            TextView tv = (TextView) view;
            str = tv.getText().toString().trim();
        }
        switch (view.getId()) {
            case R.id.tv_sel_venue:
                setOptionsPicker("选择场馆", str, veneList);
                mPvNoLinkOptions.show(view);
                break;
            case R.id.tv_sel_date:
                setOptionsPicker("选择日期", str, dateList);
                mPvNoLinkOptions.show(view);
                break;
            case R.id.tv_sel_Start_time:
                setOptionsPicker("选择开始时间", str, timeList);
                mPvNoLinkOptions.show(view);
                break;
            case R.id.tv_sel_end_time:
                setOptionsPicker("选择结束时间", str, timeList);
                mPvNoLinkOptions.show(view);
                break;
                /*setTimePicker(view);
                mPvTime.show(view);*/
//                mPvTime.setDate((DateUtil.StringToCalendar(tv.getText().toString(), tv.getId() == R.id.tv_sel_date ? DateUtil.YEAR_MONTH_DAY : DateUtil.HOUR_MINUTE)));
            case R.id.tv_sel_seat:
                Intent intent = new Intent(getContext(), roomId == veneMap.keyAt(1) ? RoomHlActivity.class : RoomActivity.class);
                String sDate = mTvSelDate.getText().toString();
                String today = DateUtil.dateToString(new Date(), DateUtil.YEAR_MONTH_DAY);
                String day = "明天";
                if (today.equals(sDate)) {
                    day = "今天";
                }
                String sT = mTvSelStartTime.getText().toString();
                String eT = mTvSelEndTime.getText().toString();
                intent.putExtra("order_venue", mTvSelVenue.getText().toString());
                intent.putExtra("sTime", sDate + " " + sT);
                intent.putExtra("eTime", sDate + " " + eT);
                intent.putExtra("order_time", day + " " + sDate + " " + sT + "-" + eT);
                intent.putExtra("room_id", roomId);
                startActivityForResult(intent, 1000);
                break;
            case R.id.btn_ok_order:
                UserToken userToken = SpUtils.getBean(getContext(), UserToken.class);
                String date = mTvSelDate.getText().toString();
                String sTm = mTvSelStartTime.getText().toString();
                String eTm = mTvSelEndTime.getText().toString();

                long sMill = DateUtil.strDateToMillseconds(date + " " + sTm, DateUtil.ALL);
                long eMill = DateUtil.strDateToMillseconds(date + " " + eTm, DateUtil.ALL);
                String seat = mTvSelSeat.getText().toString();
                JsonObject jsonObject = new JsonObject();

                jsonObject.addProperty("endTime", eMill);
                jsonObject.addProperty("startTime", sMill);
                JsonArray list = new JsonArray();
                list.add(jsonObject);
                Log.i(AppConfig.TAG, "onClick: payload=" + list);
                showLoadingDialog();
                mPresenter.submitOrder(userToken.getAccessToken(), roomId, mSeatId, list);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000 && resultCode == 1001) {
            mSeatId = data.getIntExtra("seat_id", -1);
            String seatName = data.getStringExtra("seat_name");
            mTvSelSeat.setText(seatName);
        }
    }


    //求时间差
    private String getTimeDifference(long start, long end) {
        long l = end - start;
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
//        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (day < 0 || hour <= 0 || min < 0) {
            showToast("结束时间须晚于开始时间，请检查后重新选择！");
            return "请重新选择预约时间";
        }
        if (hour > 6) {
            showToast("预约时长不合法");
            return "请重新选择预约时间";
        }
        StringBuilder strB = new StringBuilder();
//        strB.append(day + "天" + hour + "时" + min + "分");
        strB.append(hour + "小时" + min + "分");
        return strB.toString();
    }

    @Override
    public void orderSuccess(OrderInfo orderInfo) {
        dismissLoadingDialog();
        showToast("预约成功");
//        showToast(String.join(",", orderInfo.getResourceName()));
    }

    @Override
    public void orderFail(String msg) {
        dismissLoadingDialog();
        showToast(msg);
    }

    public void setVenueAndSeat(String venue, String seat) {
        mTvSelVenue.setText(venue);
        mTvSelSeat.setText(seat);
        mBtnOkOrder.setEnabled(true);
        mBtnOkOrder.setText("立即预约");
    }
}

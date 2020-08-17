package com.lxt.seatorder.ui.activity.extend;


import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.OrderRecord;
import com.lxt.seatorder.bean.ScheduleTime;
import com.lxt.seatorder.bean.TaskSchedules;
import com.lxt.seatorder.mvp.MVPBaseActivity;
import com.lxt.seatorder.utils.AppConfig;
import com.lxt.seatorder.utils.DateUtil;
import com.lxt.seatorder.utils.JsonUtils;
import com.lxt.seatorder.utils.SpUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import androidx.appcompat.app.AlertDialog;


/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class ExtendActivity extends MVPBaseActivity<ExtendContract.View, ExtendPresenter> implements ExtendContract.View, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    private final int STIME = 0;
    private final int ETIME = 1;
    private final int TIMEINTERVAL = 2;

    private android.widget.TextView mTvSigninStartTime;
    private android.widget.TextView mTvSigninDays;
    private android.widget.TextView mTvSignoutEndTime;
    private android.widget.TextView mTvSignoutDays;
    private android.widget.TextView mTvOrderTimeInterval;
    private android.widget.TextView mTvSeatAddress;
    private android.widget.TextView mTvOrderDays;
    private android.widget.Button mBtnOkService;
    private List<OrderRecord> mOrderRecordList;
    private List<String> mOrderSTimeList = new ArrayList<>();
    private List<String> mOrderETimeList = new ArrayList<>();
    private List<String> mOrderTimeIntervalList = new ArrayList<>();
    private List<String> mOrderSeatNameList = new ArrayList<>();
    private List<String> mOrderRoomSearIdList = new ArrayList<>();

    private List<String> sTimeSaves = new ArrayList<>();
    private List<String> eTimeSaves = new ArrayList<>();
    private List<String> intervalSaves = new ArrayList<>();
    private String seatNameSaves = new String();
    private String roomSeatIdSaves = new String();

    private android.widget.LinearLayout mLlRightBtn;
    private TextView mTvRightBtn;
    private AlertDialog mMultipleDialog;
    private AlertDialog mSingleDialog;
    private List<TaskSchedules> mSigninSchedules;
    private List<TaskSchedules> mSignoutSchedules;
    private List<TaskSchedules> mOrderSchedules;
    private android.widget.Switch mSwtSigninStatus;
    private android.widget.Switch mSwtSignoutStatus;
    private android.widget.Switch mSwtOrderStatus;
    private String mUsername;
    private TextView mTvSigninFinishDate;
    private TextView mTvSignoutFinishDate;
    private TextView mTvOrderFinishDate;

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_extend;
    }

    @Override
    protected void initView() {
        mTvSigninStartTime = (TextView) findViewById(R.id.tv_signin_start_time);
        mTvSigninDays = (TextView) findViewById(R.id.tv_signin_days);
        mTvSignoutEndTime = (TextView) findViewById(R.id.tv_signout_end_time);
        mTvSignoutDays = (TextView) findViewById(R.id.tv_signout_days);
        mTvOrderTimeInterval = (TextView) findViewById(R.id.tv_order_time_interval);
        mTvSeatAddress = (TextView) findViewById(R.id.tv_seat_address);
        mTvOrderDays = (TextView) findViewById(R.id.tv_order_days);
        mLlRightBtn = (LinearLayout) findViewById(R.id.ll_right_btn);
        mTvRightBtn = (TextView) findViewById(R.id.tv_right_btn);
        mSwtSigninStatus = (Switch) findViewById(R.id.swt_signin_status);
        mSwtSignoutStatus = (Switch) findViewById(R.id.swt_signout_status);
        mSwtOrderStatus = (Switch) findViewById(R.id.swt_order_status);
        mTvSigninFinishDate = (TextView) findViewById(R.id.tv_signin_finish_date);
        mTvSignoutFinishDate = (TextView) findViewById(R.id.tv_signout_finish_date);
        mTvOrderFinishDate = (TextView) findViewById(R.id.tv_order_finish_date);

        setTopTitle("通用模块");
        addBackButton();
    }


    @Override
    protected void initListener() {
        mTvSigninStartTime.setOnClickListener(this);
        mTvSigninDays.setOnClickListener(this);
        mTvSignoutEndTime.setOnClickListener(this);
        mTvOrderTimeInterval.setOnClickListener(this);
        mTvSignoutDays.setOnClickListener(this);
        mTvSeatAddress.setOnClickListener(this);
        mTvOrderDays.setOnClickListener(this);
        mSwtSigninStatus.setOnCheckedChangeListener(this);
        mSwtSignoutStatus.setOnCheckedChangeListener(this);
        mSwtOrderStatus.setOnCheckedChangeListener(this);
        mTvSigninFinishDate.setOnClickListener(this);
        mTvSignoutFinishDate.setOnClickListener(this);
        mTvOrderFinishDate.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mUsername = String.valueOf(SpUtils.get(mContext, "username", ""));
        initListData();
        fillSigninViewData();
        fillSignoutViewData();
        fillOrderViewData();
        showLoadingDialog();
        mPresenter.getScheduleTask(mUsername, 66);
    }

    private void initListData() {
        mOrderRecordList = SpUtils.getList(mContext, "orderRecordList", OrderRecord.class);
        List<String> sTList = new ArrayList<>();
        List<String> eTList = new ArrayList<>();
        List<String> seatList = new ArrayList<>();
        List<String> intervalList = new ArrayList<>();
        List<String> roomSeatIdList = new ArrayList<>();
        for (OrderRecord or :
                mOrderRecordList) {
            String sT = DateUtil.millsecondsToStrDate(or.getStartTime(), DateUtil.HOUR_MINUTE);
            String eT = DateUtil.millsecondsToStrDate(or.getEndTime(), DateUtil.HOUR_MINUTE);
            sTList.add(sT);
            eTList.add(eT);
            seatList.add(or.getBuildName() + "-" + or.getFloorName() + "-" + or.getResourceDisplayName());
            intervalList.add(sT + "-" + eT);
            roomSeatIdList.add(or.getRoomId() + "-" + or.getResourceId());
        }
        mOrderSTimeList = removeDuplicate(sTList);
        mOrderETimeList = removeDuplicate(eTList);
        mOrderSeatNameList = removeDuplicate(seatList);
        mOrderTimeIntervalList = removeDuplicate(intervalList);
        mOrderRoomSearIdList = removeDuplicate(roomSeatIdList);
    }

    public void showMutilAlertDialog(String title, final List<String> list, final int status) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(title);
        alertBuilder.setCancelable(false);
        /**
         *第一个参数:弹出框的消息集合，一般为字符串集合
         * 第二个参数：默认被选中的，布尔类数组
         * 第三个参数：勾选事件监听
         */
        alertBuilder.setMultiChoiceItems(list.toArray(new String[list.size()]), null, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean isChecked) {
                switch (status) {
                    case STIME:
                        if (isChecked) {
                            sTimeSaves.add(list.get(i));
                            if (sTimeSaves.size() > 2) {
                                showToast("最多仅支持两个时间段，请重新选择");
                            }
                        } else {
                            sTimeSaves.remove(list.get(i));
                        }
                        break;
                    case ETIME:
                        if (isChecked) {
                            eTimeSaves.add(list.get(i));
                            if (eTimeSaves.size() > 2) {
                                showToast("最多仅支持两个时间段，请重新选择");
                            }
                        } else {
                            eTimeSaves.remove(list.get(i));
                        }
                        break;
                    case TIMEINTERVAL:
                        if (isChecked) {
                            intervalSaves.add(list.get(i));
                            if (intervalSaves.size() > 2) {
                                showToast("最多仅支持两个时间段，请重新选择");
                                intervalSaves.add(list.get(i));
                            }
                        } else {
                            intervalSaves.remove(list.get(i));
                        }
                        break;
                }

            }
        });
        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                StringBuffer sb;
                switch (status) {
                    case STIME:
                        if (sTimeSaves != null && sTimeSaves.size() > 2) {
                            showToast("最多仅支持两个时间段，已为您保留前两个");
                        } else if (sTimeSaves == null || sTimeSaves.size() == 0) {
                            showToast("您未选择时间段");
                        } else {
                            sb = new StringBuffer();
                            for (int j = 0; j < sTimeSaves.size(); j++) {
                                if (j > 1) {
                                    sTimeSaves.remove(sTimeSaves.size() - 1);
                                    break;
                                }
                                sb.append(sTimeSaves.get(j)).append(",");
                            }
                            sb.deleteCharAt(sb.length() - 1);
                            mTvSigninStartTime.setText(prefixionDate(1, sb.toString()));
                        }
                        break;
                    case ETIME:
                        if (eTimeSaves != null && eTimeSaves.size() > 2) {
                            showToast("最多仅支持两个时间段，已为您保留前两个");
                        } else if (eTimeSaves == null || eTimeSaves.size() == 0) {
                            showToast("您未选择时间段");
                        } else {
                            sb = new StringBuffer();
                            for (int j = 0; j < eTimeSaves.size(); j++) {
                                if (j > 1) {
                                    eTimeSaves.remove(eTimeSaves.size() - 1);
                                    break;
                                }
                                sb.append(eTimeSaves.get(j)).append(",");
                            }
                            sb.deleteCharAt(sb.length() - 1);
                            mTvSignoutEndTime.setText(prefixionDate(1, sb.toString()));
                        }
                        break;
                    case TIMEINTERVAL:
                        if (intervalSaves != null && intervalSaves.size() > 2) {
                            showToast("最多仅支持两个时间段，已为您保留前两个");
                        } else if (intervalSaves == null || intervalSaves.size() == 0) {
                            showToast("您未选择时间段");
                        } else {
                            sb = new StringBuffer();
                            for (int j = 0; j < intervalSaves.size(); j++) {
                                if (j > 1) {
                                    intervalSaves.remove(intervalSaves.size() - 1);
                                    break;
                                }
                                sb.append(intervalSaves.get(j)).append(",");
                            }
                            sb.deleteCharAt(sb.length() - 1);
                            mTvOrderTimeInterval.setText(prefixionDate(2, sb.toString()));
                        }
                        break;
                }
            }
        });
        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mMultipleDialog.dismiss();
            }
        });
        mMultipleDialog = alertBuilder.create();
        mMultipleDialog.show();
    }

    private void showSingleAlertDialog(String title, final List<String> list) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle(title);
        alertBuilder.setCancelable(false);
        roomSeatIdSaves = mOrderRoomSearIdList.get(0);
        seatNameSaves = mOrderSeatNameList.get(0);
        alertBuilder.setSingleChoiceItems(list.toArray(new String[list.size()]), 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                seatNameSaves = list.get(i);
                roomSeatIdSaves = mOrderRoomSearIdList.get(i);
            }
        });

        alertBuilder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mTvSeatAddress.setText(seatNameSaves);
            }
        });

        alertBuilder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                roomSeatIdSaves = "";
                seatNameSaves = "";
                mSingleDialog.dismiss();
            }
        });
        mSingleDialog = alertBuilder.create();
        mSingleDialog.show();
    }

    private <T> List<T> removeDuplicate(List<T> list) {
        HashSet h = new HashSet(list);
        list.clear();
        list.addAll(h);
        return list;
    }

    private void openRecordBtn() {
        mLlRightBtn.setVisibility(View.VISIBLE);
        mTvRightBtn.setText("记录");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_signin_start_time:
                if (mSigninSchedules != null && mSigninSchedules.size() > 0) {
                    showToast("不可重新选择");
                    return;
                }
                sTimeSaves.clear();
                showMutilAlertDialog("选择预约开始时间", mOrderSTimeList, STIME);
                break;
            case R.id.tv_signout_end_time:
                if (mSignoutSchedules != null && mSignoutSchedules.size() > 0) {
                    showToast("不可重新选择");
                    return;
                }
                eTimeSaves.clear();
                showMutilAlertDialog("选择预约结束时间", mOrderETimeList, ETIME);
                break;
            case R.id.tv_signin_days:
            case R.id.tv_signout_days:
            case R.id.tv_order_days:
                showToast("暂时不支持选择服务天数");
                break;
            case R.id.tv_order_time_interval:
                if (mOrderSchedules != null && mOrderSchedules.size() > 0) {
                    showToast("不可重新选择");
                    return;
                }
                intervalSaves.clear();
                showMutilAlertDialog("选择预约时间段", mOrderTimeIntervalList, TIMEINTERVAL);
                break;
            case R.id.tv_seat_address:
                if (mOrderSchedules != null && mOrderSchedules.size() > 0) {
                    showToast("不可重新选择");
                    return;
                }
                showSingleAlertDialog("选择座位地址", mOrderSeatNameList);
                break;
            case R.id.tv_signin_finish_date:
            case R.id.tv_signout_finish_date:
            case R.id.tv_order_finish_date:
                showToast("不可选择服务结束时间");
                break;
            default:
                break;
        }
    }

    @Override
    public void getScheduleTaskSucc(JsonObject jsonObject) {
        dismissLoadingDialog();
        JsonArray siJa = jsonObject.getAsJsonArray("signinSchedules");
        JsonArray soJa = jsonObject.getAsJsonArray("signoutSchedules");
        JsonArray osJa = jsonObject.getAsJsonArray("orderSchedules");
        if (siJa != null) {
            mSigninSchedules = JsonUtils.json2List(siJa.toString(), TaskSchedules.class);
            fillSigninViewData();
        }
        if (soJa != null) {
            mSignoutSchedules = JsonUtils.json2List(soJa.toString(), TaskSchedules.class);
            fillSignoutViewData();
        }
        if (osJa != null) {
            mOrderSchedules = JsonUtils.json2List(osJa.toString(), TaskSchedules.class);
            fillOrderViewData();
        }
    }

    private void fillSigninViewData() {
        if (mSigninSchedules == null || mSigninSchedules.size() == 0) {
            mSwtSigninStatus.setChecked(false);
            mTvSigninStartTime.setText("请选择");
            mTvSigninDays.setText(AppConfig.Service_Days + "天");
            mTvSigninFinishDate.setText(calcFinishDate());
        } else {
            StringBuilder sb = new StringBuilder();
            sTimeSaves.clear();
            for (TaskSchedules t :
                    mSigninSchedules) {
                sTimeSaves.add(DateUtil.dateToString(t.getOrderStartTime(), DateUtil.ALL));
                mTvSigninFinishDate.setText(DateUtil.dateToString(t.getFinishTime(), DateUtil.ALL));
                mSwtSigninStatus.setChecked(t.getStatus() != 0);
                mTvSigninDays.setText(calcDaysDiffe(new Date().getTime(), t.getFinishTime().getTime()));
                sb.append(DateUtil.dateToString(t.getOrderStartTime(), DateUtil.HOUR_MINUTE)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            mTvSigninStartTime.setText(prefixionDate(1, sb.toString()));
        }
    }

    private void fillSignoutViewData() {
        if (mSignoutSchedules == null || mSignoutSchedules.size() == 0) {
            mSwtSignoutStatus.setChecked(false);
            mTvSignoutEndTime.setText("请选择");
            mTvSignoutDays.setText(AppConfig.Service_Days + "天");
            mTvSignoutFinishDate.setText(calcFinishDate());
        } else {
            StringBuilder sb = new StringBuilder();
            eTimeSaves.clear();
            for (TaskSchedules t :
                    mSignoutSchedules) {
                eTimeSaves.add(DateUtil.dateToString(t.getOrderEndTime(), DateUtil.ALL));
                mTvSignoutFinishDate.setText(DateUtil.dateToString(t.getFinishTime(), DateUtil.ALL));
                mSwtSignoutStatus.setChecked(t.getStatus() != 0);
                mTvSignoutDays.setText(calcDaysDiffe(new Date().getTime(), t.getFinishTime().getTime()));
                sb.append(DateUtil.dateToString(t.getOrderEndTime(), DateUtil.HOUR_MINUTE)).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            mTvSignoutEndTime.setText(prefixionDate(1, sb.toString()));
        }
    }

    private void fillOrderViewData() {
        if (mOrderSchedules == null || mOrderSchedules.size() == 0) {
            mSwtOrderStatus.setChecked(false);
            mTvSeatAddress.setText("请选择");
            mTvOrderTimeInterval.setText("请选择");
            mTvOrderDays.setText(AppConfig.Service_Days + "天");
            mTvOrderFinishDate.setText(calcFinishDate());
        } else {
            StringBuilder sb = new StringBuilder();
            intervalSaves.clear();  //添加之前一定要清除
            for (TaskSchedules t :
                    mOrderSchedules) {
                seatNameSaves = t.getSeatName();
                mTvSeatAddress.setText(t.getSeatName());
                mTvOrderFinishDate.setText(DateUtil.dateToString(t.getFinishTime(), DateUtil.ALL));
                mTvOrderDays.setText(calcDaysDiffe(new Date().getTime(), t.getFinishTime().getTime()));
                mSwtOrderStatus.setChecked(t.getStatus() != 0);
                String intT = DateUtil.dateToString(t.getOrderStartTime(), DateUtil.HOUR_MINUTE) + "-" + DateUtil.dateToString(t.getOrderEndTime(), DateUtil.HOUR_MINUTE);
                intervalSaves.add(intT);
                sb.append(intT).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            mTvOrderTimeInterval.setText(prefixionDate(2, sb.toString()));
        }
    }

    @Override
    public void getScheduleTaskFail(String msg) {
        showToast(msg);
        dismissLoadingDialog();
    }

    @Override
    public void addScheduleTaskSucc(String msg, int type) {
        showToast(msg);
        dismissLoadingDialog();
        switch (type) {
            case 11:
                mPresenter.getScheduleTask(mUsername, 11);
                mSwtOrderStatus.setChecked(!mSwtOrderStatus.isChecked());
                break;
            case 21:
                mPresenter.getScheduleTask(mUsername, 21);
                mSwtSigninStatus.setChecked(!mSwtSigninStatus.isChecked());
                break;
            case 51:
                mPresenter.getScheduleTask(mUsername, 51);
                mSwtSignoutStatus.setChecked(!mSwtSignoutStatus.isChecked());
                break;
        }
    }

    @Override
    public void addScheduleTaskFail(String msg, int type) {
        showToast(msg);
        dismissLoadingDialog();
        switch (type) {
            case 11 :
                mSwtOrderStatus.setChecked(mSwtOrderStatus.isChecked());
                break;
            case 21:
                mSwtSigninStatus.setChecked(mSwtSigninStatus.isChecked());
                break;
            case 51:
                mSwtSignoutStatus.setChecked(mSwtSignoutStatus.isChecked());
                break;
        }
    }

    @Override
    public void updateScheduleStatusSucc(String msg, int type) {
        dismissLoadingDialog();
        showToast(msg);
        switch (type) {
            case 11:
                mPresenter.getScheduleTask(mUsername, 11);
                mSwtOrderStatus.setChecked(!mSwtOrderStatus.isChecked());
                break;
            case 21:
                mPresenter.getScheduleTask(mUsername, 21);
                mSwtSigninStatus.setChecked(!mSwtSigninStatus.isChecked());
                break;
            case 51:
                mPresenter.getScheduleTask(mUsername, 51);
                mSwtSignoutStatus.setChecked(!mSwtSignoutStatus.isChecked());
                break;
        }
    }

    @Override
    public void updateScheduleStatusFail(String msg, int type) {
        showToast(msg);
        dismissLoadingDialog();
        switch (type) {
            case 11:
                mSwtOrderStatus.setChecked(mSwtOrderStatus.isChecked());
                break;
            case 21:
                mSwtSigninStatus.setChecked(mSwtSigninStatus.isChecked());
                break;
            case 51:
                mSwtSignoutStatus.setChecked(mSwtSignoutStatus.isChecked());
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (!compoundButton.isPressed())        //如果不是手动点击触发的该事件
            return;
        compoundButton.setChecked(!b);
        switch (compoundButton.getId()) {
            case R.id.swt_signin_status:
                if (b) {
                    ScheduleTime st1 = new ScheduleTime();
                    st1.setUserId(mUsername);
                    st1.setType(21);
                    if (sTimeSaves.size() == 0) {
                        showToast("您尚未选择时间段");
                        compoundButton.setChecked(false);
                        break;
                    } else if (sTimeSaves.size() == 2) {
                        st1.setOrderStartTime1(DateUtil.stringToDate(prefixionDate(1, sTimeSaves.get(0)), DateUtil.ALL));
                        st1.setOrderStartTime2(DateUtil.stringToDate(prefixionDate(1, sTimeSaves.get(1)), DateUtil.ALL));
                    } else if (sTimeSaves.size() == 1)
                        st1.setOrderStartTime1(DateUtil.stringToDate(prefixionDate(1, sTimeSaves.get(0)), DateUtil.ALL));
                    showLoadingDialog();
                    mPresenter.addScheduleTask(st1);
                } else {
                    showBottomDialog("关闭后结束时间内可再次开启，您确定要\n关闭签到服务吗？", "确定", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showLoadingDialog();
                            dismissBottomDialog();
                            mPresenter.updateScheduleStatus(mUsername, 0, 21);
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mSwtSigninStatus.setChecked(true);
                            dismissBottomDialog();
                        }
                    });
                }
                break;
            case R.id.swt_signout_status:
                if (b) {
                    ScheduleTime st2 = new ScheduleTime();
                    st2.setUserId(mUsername);
                    st2.setType(51);
                    if (eTimeSaves.size() == 0) {
                        showToast("您尚未选择时间段");
                        compoundButton.setChecked(false);
                        break;
                    } else if (eTimeSaves.size() == 2) {
                        st2.setOrderEndTime1(DateUtil.stringToDate(prefixionDate(1, eTimeSaves.get(0)), DateUtil.ALL));
                        st2.setOrderEndTime2(DateUtil.stringToDate(prefixionDate(1, eTimeSaves.get(1)), DateUtil.ALL));
                    } else if (eTimeSaves.size() == 1)
                        st2.setOrderEndTime1(DateUtil.stringToDate(prefixionDate(1, eTimeSaves.get(0)), DateUtil.ALL));
                    showLoadingDialog();
                    mPresenter.addScheduleTask(st2);
                } else {
                    showBottomDialog("关闭后结束时间内可再次开启，您确定要\n关闭签退服务吗？", "确定", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showLoadingDialog();
                            dismissBottomDialog();
                            mPresenter.updateScheduleStatus(mUsername, 0, 51);
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mSwtSignoutStatus.setChecked(true);
                            dismissBottomDialog();
                        }
                    });
                }
                break;
            case R.id.swt_order_status:
                if (b) {
                    String[] sp1;
                    String[] sp2;
                    ScheduleTime st3 = new ScheduleTime();
                    st3.setUserId(mUsername);
                    st3.setType(11);
                    if (intervalSaves.size() == 0) {
                        showToast("您尚未选择时间段");
                        compoundButton.setChecked(false);
                        break;
                    } else if (TextUtils.isEmpty(seatNameSaves)) {
                        showToast("您尚未选择座位地址");
                        compoundButton.setChecked(false);
                        break;
                    } else if (intervalSaves.size() == 2) {
                        sp1 = intervalSaves.get(0).split("-");
                        sp2 = intervalSaves.get(1).split("-");
                        st3.setOrderStartTime1(DateUtil.stringToDate(prefixionDate(2, sp1[0]), DateUtil.ALL));
                        st3.setOrderEndTime1(DateUtil.stringToDate(prefixionDate(2, sp1[1]), DateUtil.ALL));
                        st3.setOrderStartTime2(DateUtil.stringToDate(prefixionDate(2, sp2[0]), DateUtil.ALL));
                        st3.setOrderEndTime2(DateUtil.stringToDate(prefixionDate(2, sp2[1]), DateUtil.ALL));
                    } else if (intervalSaves.size() == 1) {
                        sp1 = intervalSaves.get(0).split("-");
                        st3.setOrderStartTime1(DateUtil.stringToDate(prefixionDate(2, sp1[0]), DateUtil.ALL));
                        st3.setOrderEndTime1(DateUtil.stringToDate(prefixionDate(2, sp1[1]), DateUtil.ALL));
                    }
                    st3.setSeatName(seatNameSaves);
                    st3.setRoomSeatId(roomSeatIdSaves);
                    showLoadingDialog();
                    mPresenter.addScheduleTask(st3);
                } else {
                    showBottomDialog("关闭后结束时间内可再次开启，您确定要\n关闭预约服务吗？", "确定", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            showLoadingDialog();
                            dismissBottomDialog();
                            mPresenter.updateScheduleStatus(mUsername, 0, 11);
                        }
                    }, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mSwtOrderStatus.setChecked(true);
                            dismissBottomDialog();
                        }
                    });
                }
                break;
            default:
                break;
        }
    }

    private String prefixionDate(int days, String hM) {
        return DateUtil.dateToString(DateUtil.dateAddDays(new Date(), days), DateUtil.YEAR_MONTH_DAY) + " " + hM;
    }

    private String calcFinishDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 1);
        Date zero = calendar.getTime();
        Date finishDate = DateUtil.dateAddDays(zero, AppConfig.Service_Days + 1);
        return DateUtil.dateToString(finishDate, DateUtil.ALL);
    }

    private String calcDaysDiffe(long start, long end) {
        long l = end - start;
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
//        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

        return String.valueOf(day) + "天";
    }
}

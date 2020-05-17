package com.lxt.seatorder.ui.activity.personinfo;


import android.app.Dialog;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.contrarywind.view.WheelView;
import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.OrderUser;
import com.lxt.seatorder.bean.User;
import com.lxt.seatorder.mvp.MVPBaseActivity;
import com.lxt.seatorder.utils.DateUtil;
import com.lxt.seatorder.utils.SpUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 邮箱 bing61@qq.com
 */

public class PersonInfoActivity extends MVPBaseActivity<PersonInfoContract.View, PersonInfoPresenter> implements PersonInfoContract.View, View.OnClickListener {

    private TimePickerBuilder mPickerBuilder;
    private TimePickerView mPvTime;
    private android.widget.ImageView mIvBack;
    private TextView mTvMainTitle;
    private TextView mTvSelNumber;
    private TextView mTvSelName;
    private TextView mTvSelCard;
    private TextView mTvSelDepartment;
    private TextView mTvSelBirthday;
    private TextView mTvSelSex;
    private TextView mTvSelEmail;
    private TextView mTvSelPhone;
    private TextView mTvChangePassword;
    private List<String> sexList = new ArrayList<>();
    private OptionsPickerView<String> mPvNoLinkOptions;
    private Dialog mEditDialog;
    private OrderUser mOrderUser;

    private final int Birthday = 0;
    private final int Gender = 1;
    private final int Email = 2;
    private final int Mobile = 3;
    private final int Pwd = 4;
    private User mUser = new User();

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initView() {
        setTopTitle("个人资料");
        addBackButton();
        mIvBack = (ImageView) findViewById(R.id.iv_back);
        mTvMainTitle = (TextView) findViewById(R.id.tv_main_title);
        mTvSelNumber = (TextView) findViewById(R.id.tv_sel_number);
        mTvSelName = (TextView) findViewById(R.id.tv_sel_name);
        mTvSelCard = (TextView) findViewById(R.id.tv_sel_card);
        mTvSelDepartment = (TextView) findViewById(R.id.tv_sel_department);
        mTvSelBirthday = (TextView) findViewById(R.id.tv_sel_birthday);
        mTvSelSex = (TextView) findViewById(R.id.tv_sel_sex);
        mTvSelEmail = (TextView) findViewById(R.id.tv_sel_email);
        mTvSelPhone = (TextView) findViewById(R.id.tv_sel_phone);
//        mTvChangePassword = (TextView) findViewById(R.id.tv_change_password);

    }

    @Override
    protected void initListener() {
        mTvSelBirthday.setOnClickListener(this);
        mTvSelSex.setOnClickListener(this);
        mTvSelEmail.setOnClickListener(this);
    }

    @Override
    protected void initData() {
//        sexList.add("不告诉你");
        sexList.add("男");
        sexList.add("女");
        initTimePicker();
        initNoLinkOptionsPicker();
        if (SpUtils.isContains(mContext, OrderUser.class.getSimpleName())) {
            mOrderUser = SpUtils.getBean(mContext, OrderUser.class);
            mTvSelNumber.setText(mOrderUser.getUserId());
            mTvSelName.setText(mOrderUser.getNickName());
            mTvSelCard.setText(mOrderUser.getCardNumber());
            mTvSelDepartment.setText(mOrderUser.getDepartmentName());
            mUser.setPassword(String.valueOf(SpUtils.get(mContext, "pwd", "")));
            mUser.setUserId(mOrderUser.getUserId());
            mUser.setNickname(mOrderUser.getNickName());
            mUser.setCardNumber(mOrderUser.getCardNumber());
            mUser.setDepartmentName(mOrderUser.getDepartmentName());
            mUser.setDepartmentId(mOrderUser.getDepartmentId());
            mUser.setRealName(mOrderUser.getRealName());
        }
        if (SpUtils.isContains(mContext, User.class.getSimpleName())) {
            mUser = SpUtils.getBean(mContext, User.class);
            if (mUser.getBirthday() != null) {
                mTvSelBirthday.setText(DateUtil.dateToString(mUser.getBirthday(), DateUtil.YEAR_MONTH_DAY));
            } else {
                mTvSelBirthday.setText("请选择");
            }
            mTvSelSex.setText(TextUtils.isEmpty(mUser.getGender()) ? "请选择" : mUser.getGender());
            mTvSelEmail.setText(TextUtils.isEmpty(mUser.getMail()) ? "请填写" : mUser.getMail());
            mTvSelPhone.setText(TextUtils.isEmpty(mUser.getMobile()) ? "绑定" : mUser.getMobile());
        }
    }

    private void initTimePicker() {
        mPickerBuilder = new TimePickerBuilder(this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
//                ((TextView) v).setText(DateUtil.dateToString(date, DateUtil.YEAR_MONTH_DAY));
                showLoadingDialog();
                mUser.setBirthday(date);
                mPresenter.updateUserInfo(mUser, Birthday);
            }
        })
                .setType(new boolean[]{true, true, true, false, false, false})// 默认全部显示
                .setCancelText("取消")//取消按钮文字
                .setSubmitText("确定")//确认按钮文字
                .setContentTextSize(20)//滚轮文字大小
                .setTitleSize(18)//标题文字大小
                .setTitleText("选择时间")//标题文字
                .setOutSideCancelable(true)//点击屏幕，点在控件外部范围时，是否取消显示
                .isCyclic(false)//是否循环滚动
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
                .isCenterLabel(false); //是否只显示中间选中项的label文字，false则每项item全部都带有label。

    }

    private void setTimePicker() {
        mPvTime = null;
        Calendar startDate = Calendar.getInstance();
        Calendar endDate = Calendar.getInstance();

        startDate.set(Calendar.YEAR, 1990);
        endDate.set(Calendar.YEAR, 2010);
//        calendar.set(Calendar.YEAR, 1997);
        String str = mTvSelBirthday.getText().toString().trim();
        Calendar calendar = DateUtil.StringToCalendar(str.equals("请选择") ? "1997-06-01" : str, DateUtil.YEAR_MONTH_DAY);
        mPvTime = mPickerBuilder
                .setTitleText("选择日期")
                .setRangDate(startDate, endDate)  //起始终止年月日设定
                .setDate(calendar)      // 设置为已选择的日期
                .build();
    }

    private void initNoLinkOptionsPicker() {// 不联动的多级选项
        mPvNoLinkOptions = new OptionsPickerBuilder(this, new OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int i, int i1, int i2, View view) {
//                ((TextView) view).setText(sexList.get(i));
                showLoadingDialog();
                mUser.setGender(sexList.get(i));
                mPresenter.updateUserInfo(mUser, Gender);
            }
        })
                .setTitleText("选择性别")//标题文字
                .setContentTextSize(22)//滚轮文字大小
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
        mPvNoLinkOptions.setNPicker(sexList, null, null);
    }

    private void showEditDialog() {
        mEditDialog = new Dialog(getContext(), R.style.BottomDialogStyle);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_edit, null);

        TextView mTvDialogEditTitle = (TextView) view.findViewById(R.id.tv_dialog_edit_title);
        final EditText mEtDialogEditEmail = (EditText) view.findViewById(R.id.et_dialog_edit_email);
        TextView mTvLeftBtn = (TextView) view.findViewById(R.id.tv_left_btn);
        TextView mTvRightBtn = (TextView) view.findViewById(R.id.tv_right_btn);

        mEditDialog.setCancelable(true);
        mEditDialog.setCanceledOnTouchOutside(true);
        mTvLeftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEtDialogEditEmail.getText().toString();
                if (isEmail(email)) {
                    showLoadingDialog();
                    mUser.setMail(email);
                    mEditDialog.dismiss();
                    mPresenter.updateUserInfo(mUser, Email);
                } else {
                    showToast("邮箱格式不正确");
                }
            }
        });
        mTvRightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEditDialog.dismiss();
            }
        });

        WindowManager manager = this.getWindowManager();
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        int width = outMetrics.widthPixels;
        int height = outMetrics.heightPixels;
        view.setMinimumWidth((int) (width * 0.97));
        view.setMinimumHeight(height / 4);
        Window dialogWindow = mEditDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        mEditDialog.setContentView(view);
        mEditDialog.show();
    }

    public void dismissBottomDialog() {
        if (mEditDialog != null && mEditDialog.isShowing()) {
            mEditDialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_sel_birthday:
                setTimePicker();
                mPvTime.show(v);
                break;
            case R.id.tv_sel_sex:
                mPvNoLinkOptions.show(v);
                break;
            case R.id.tv_sel_email:
                showEditDialog();
                break;
//            case R.id.tv_sel_phone:
//                break;
//            case R.id.tv_change_password:
//                break;
            default:
                break;
        }
    }


    @Override
    public void updateUserInfoSucc(User user, String msg, int type) {
        dismissLoadingDialog();
        mUser = user;
        if (mUser != null) {
            SpUtils.putBean(mContext, user);
        } else {
            return;
        }
        switch (type) {
            case Birthday:
                mTvSelBirthday.setText(DateUtil.dateToString(mUser.getBirthday(), DateUtil.YEAR_MONTH_DAY));
                break;
            case Gender:
                mTvSelSex.setText(mUser.getGender());
                break;
            case Email:
                mTvSelEmail.setText(mUser.getMail());
                break;
            case Mobile:
                mTvSelPhone.setText(mUser.getMobile());
                break;
            case Pwd:
                mTvChangePassword.setText("已绑定");
                mTvChangePassword.setEnabled(false);
                break;
            default:
                break;
        }
    }

    @Override
    public void updateUserInfoFail(String msg, int type) {
        showToast(msg);
        dismissLoadingDialog();
        switch (type) {
            case Birthday:
                mTvSelBirthday.setText(mTvSelBirthday.getText());
                break;
            case Gender:
                mTvSelSex.setText(mTvSelSex.getText());
                break;
            case Email:
                mTvSelEmail.setText(mTvSelEmail.getText());
                break;
            case Mobile:
                mTvSelPhone.setText(mTvSelPhone.getText());
                break;
            case Pwd:
                mTvChangePassword.setText("绑定");
                mTvChangePassword.setEnabled(true);
                break;
            default:
                break;
        }
    }

    private boolean isEmail(String email) {
        if (null == email || "".equals(email)) return false;
        //Pattern p = Pattern.compile("\\w+@(\\w+.)+[a-z]{2,3}"); //简单匹配
        Pattern p = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");//复杂匹配
        Matcher m = p.matcher(email);
        return m.matches();
    }
}

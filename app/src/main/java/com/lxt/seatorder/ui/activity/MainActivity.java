package com.lxt.seatorder.ui.activity;

import android.Manifest;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.OrderRecord;
import com.lxt.seatorder.mvp.MVPBaseActivity;
import com.lxt.seatorder.ui.activity.signinorout.SignInOrOutContract;
import com.lxt.seatorder.ui.activity.signinorout.SignInOrOutPresenter;
import com.lxt.seatorder.ui.activity.signinorout.SigninActivity;
import com.lxt.seatorder.ui.adapter.HomeFragmentPagerAdapter;
import com.lxt.seatorder.ui.fragment.history.HistoryFragment;
import com.lxt.seatorder.ui.fragment.my.MyFragment;
import com.lxt.seatorder.ui.fragment.order.OrderFragment;
import com.lxt.seatorder.utils.DateUtil;
import com.lxt.seatorder.utils.SpUtils;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends MVPBaseActivity<SignInOrOutContract.View, SignInOrOutPresenter> implements SignInOrOutContract.View, EasyPermissions.PermissionCallbacks {

    private static final int REQUEST_CODE_QRCODE_PERMISSIONS = 1;

    private final int PAGE_ONE = 0;
    private final int PAGE_TWO = 1;
    private final int PAGE_THREE = 2;
    private TextView mTvCurrentCoin;
    private TextView mTvCoinNum;
    private ViewPager mVpContent;
    private FrameLayout mFlContent;
    private RadioGroup mRgTabs;
    private RadioButton mRbHome;
    private RadioButton mRbOrder;
    private RadioButton mRbMy;
    private LinearLayout mLlSignIn;
    private ImageView mIvBack;
    private TextView mTvMainTitle;
    private LinearLayout mLlSignOut;

    private int mseatId = -1;
    private List<Fragment> mFragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_main;
    }


    protected void initView() {
        mVpContent = findViewById(R.id.vp_content);
        mFlContent = findViewById(R.id.fl_content);
        mRgTabs = findViewById(R.id.rg_tabs);
        mRbHome = findViewById(R.id.rb_home);
        mRbOrder = findViewById(R.id.rb_order);
        mRbMy = findViewById(R.id.rb_my);
        mLlSignIn = (LinearLayout) findViewById(R.id.ll_left_btn);
        mLlSignOut = (LinearLayout) findViewById(R.id.ll_right_btn);

        resetRadioButtonImage(mRbOrder);
        resetRadioButtonImage(mRbHome);
        resetRadioButtonImage(mRbMy);

    }

    protected void initListener() {
        mVpContent.addOnPageChangeListener(mPageChangeListener);
        mRgTabs.setOnCheckedChangeListener(mOnCheckedChangeListener);
        setSignInOrOut(View.VISIBLE);
    }

    protected void initData() {
        //    private GridView mGvHome;
        mFragmentList = new ArrayList<>();
        mFragmentList.add(new OrderFragment());
        mFragmentList.add(new HistoryFragment());
        mFragmentList.add(new MyFragment());
        HomeFragmentPagerAdapter fragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager(), mFragmentList);

        mVpContent.setAdapter(fragmentPagerAdapter);
        mVpContent.setOffscreenPageLimit(3);   //设置缓存fragment数

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mVpContent.removeOnPageChangeListener(mPageChangeListener);
    }

    private ViewPager.OnPageChangeListener mPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            switch (mVpContent.getCurrentItem()) {
                case PAGE_ONE:
                    mRbOrder.setChecked(true);
                    break;
                case PAGE_TWO:
                    mRbHome.setChecked(true);
                    break;
                case PAGE_THREE:
                    mRbMy.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private RadioGroup.OnCheckedChangeListener mOnCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_order:
                    mVpContent.setCurrentItem(PAGE_ONE);
                    setSignInOrOut(View.VISIBLE);
                    setTopTitle("预约");
                    break;
                case R.id.rb_home:
                    mVpContent.setCurrentItem(PAGE_TWO);
                    setTopTitle("历史");
                    setSignInOrOut(View.VISIBLE);
                    break;
                case R.id.rb_my:
                    mVpContent.setCurrentItem(PAGE_THREE);
                    setSignInOrOut(View.GONE);
                    setTopTitle("我的");
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 重置RadioButton的图片的大小
     *
     * @param radioButton
     */
    private void resetRadioButtonImage(RadioButton radioButton) {
        //定义底部标签图片大小和位置
        Drawable drawable_news = radioButton.getCompoundDrawables()[1];
        //当这个图片被绘制时，给他绑定一个矩形 ltrb规定这个矩形
        drawable_news.setBounds(0, 0, 50, 50);
        //设置图片在文字的哪个方向
        radioButton.setCompoundDrawables(null, drawable_news, null, null);


    }

    private void setSignInOrOut(int Visibility) {
        mLlSignIn.setVisibility(Visibility);
        mLlSignOut.setVisibility(Visibility);
        mLlSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, SigninActivity.class);
                startActivityForResult(intent, 1100);
//                mPresenter.SignInOrOut();
            }
        });
        mLlSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showLoadingDialog();
                mPresenter.getInuseSeat(String.valueOf(SpUtils.get(mContext, "username", "")));
//                showBottomAlertDialog("签到", "1233adsdd达到", "大声道大大多", "完成", "签到", "取消",null);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        requestCodeQRCodePermissions();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
    }

    @AfterPermissionGranted(REQUEST_CODE_QRCODE_PERMISSIONS)
    private void requestCodeQRCodePermissions() {
        String[] perms = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, perms)) {
            EasyPermissions.requestPermissions(this, "扫描二维码需要打开相机和闪光灯的权限", REQUEST_CODE_QRCODE_PERMISSIONS, perms);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // TODO: 2020/4/12 有问题，判断扫描二维码座位是否和当前使用中的座位相同
        if (requestCode == 1100 && resultCode == 1100) {
            assert data != null;
            mseatId = data.getIntExtra("seatId", -1);
            mPresenter.getInuseSeat(String.valueOf(SpUtils.get(mContext, "username", "")));
        }
    }

    @Override
    public void signInOrOutSuccess(String msg) {
        showToast(msg);
        dismissLoadingDialog();
    }

    @Override
    public void signInOrOutFail(String msg) {
        showToast(msg);
        dismissLoadingDialog();
    }

    @Override
    public void getInuseSeatSuccess(final OrderRecord record) {
        dismissLoadingDialog();
        String roomName = record.getBuildName() + "-" + record.getFloorName() + "-" + record.getResourceDisplayName();
        String time = DateUtil.millsecondsToStrDate(record.getStartTime(), DateUtil.ALL) + "-" + DateUtil.millsecondsToStrDate(record.getEndTime(), DateUtil.HOUR_MINUTE);
        if (record.getResourceId() == mseatId) {
            if (record.getItemStatus() == 11) {
                showBottomAlertDialog("签到", time, roomName, "完成", "签到", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.SignInOrOut(record.getOrderItemId(), 21, String.valueOf(SpUtils.get(mContext, "username", "")));
                        mseatId = -1;
                    }
                });
            }

        } else if (mseatId == -1) {
            if (record.getItemStatus() == 22 || record.getItemStatus() == 21) {  //已经是签到状态
                showBottomAlertDialog("签退", time, roomName, "完成", "签退", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.SignInOrOut(record.getOrderItemId(), 51, String.valueOf(SpUtils.get(mContext, "username", "")));
                    }
                });
            } else showToast("当前未预约或未签到");
        } else showToast("您当前操作的座位与您的预约座位不符");


    }

    @Override
    public void getInuseSeatFail(String msg) {
        dismissLoadingDialog();
        if (mseatId != -1) {
            showBottomDialog("您尚未预约此座位，需要进行预约吗？", "预约", "取消", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismissBottomDialog();
                    showLoadingDialog();
                    mVpContent.setCurrentItem(PAGE_ONE);
                    setSignInOrOut(View.VISIBLE);
                    setTopTitle("预约");
                    mPresenter.getSeatName(mseatId);
                    mseatId = -1;
                }
            }, new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dismissBottomDialog();
                    mseatId = -1;
                }
            });
        } else showToast(msg);
    }

    @Override
    public void getSeatNameSuccess(List<String> seats) {
        dismissLoadingDialog();
        showToast("请继续选择预约时间");
        String[] sp = seats.get(0).split("-");
        String venue = sp[0] + sp[1];
        String seat = seats.get(1) + "-" + seats.get(2);
        OrderFragment orderFragment = (OrderFragment) mFragmentList.get(0);

        orderFragment.setVenueAndSeat(venue, seat);
    }

    @Override
    public void getSeatNameFail(String msg) {
        dismissLoadingDialog();
        showToast(msg);
    }
}
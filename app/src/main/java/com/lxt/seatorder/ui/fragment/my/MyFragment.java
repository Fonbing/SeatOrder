package com.lxt.seatorder.ui.fragment.my;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.OrderUser;
import com.lxt.seatorder.bean.User;
import com.lxt.seatorder.mvp.MVPBaseFragment;
import com.lxt.seatorder.ui.activity.extend.ExtendActivity;
import com.lxt.seatorder.ui.activity.login.LoginActivity;
import com.lxt.seatorder.ui.activity.notice.NoticeActivity;
import com.lxt.seatorder.ui.activity.personinfo.PersonInfoActivity;
import com.lxt.seatorder.ui.activity.signinorout.SigninActivity;
import com.lxt.seatorder.ui.activity.userscore.UserScoreActivity;
import com.lxt.seatorder.utils.SpUtils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Lxt Jxfen on 2019-11-24.
 * email: 1771874056@qq.com
 */
public class MyFragment extends MVPBaseFragment<MyContract.View, MyPresenter> implements View.OnClickListener, MyContract.View {
    private static final String QQNUM = "1771874056";

    private TextView mTvInfo;
    private LinearLayout mLlIndex;
    private Button mBtnBack;
    private CircleImageView mCivAvatar;
    private TextView mTvUsername;
    private TextView mTvAccount;
    private ScrollView mSvIndex;

    private LinearLayout mLlClearCache;
    private LinearLayout mLlHelp;
    private LinearLayout mLlExit;
    private LinearLayout mLlPersonInfo;
    private LinearLayout mLlCoin;
    private LinearLayout mLlScanCode;
    private LinearLayout mLlShareFriend;
    private LinearLayout mLlFeedback;
    private LinearLayout mLlExtend;
    private User mUser;

    @Override
    protected void initView() {
        mTvInfo = findViewById(R.id.tv_info);
        mLlIndex = findViewById(R.id.ll_index);
        mCivAvatar = findViewById(R.id.civ_avatar);
        mTvUsername = findViewById(R.id.tv_username);
        mTvAccount = findViewById(R.id.tv_account);
        mSvIndex = findViewById(R.id.sv_index);

        mLlClearCache = findViewById(R.id.ll_clear_cache);
        mLlHelp = findViewById(R.id.ll__help);
        mLlExit = findViewById(R.id.ll_exit);
        mLlPersonInfo = (LinearLayout) findViewById(R.id.ll_person_info);
        mLlCoin = (LinearLayout) findViewById(R.id.ll_coin);
        mLlScanCode = (LinearLayout) findViewById(R.id.ll_scan_code);
//        mLlShareFriend = (LinearLayout) findViewById(R.id.ll_share_friend);
        mLlFeedback = (LinearLayout) findViewById(R.id.ll_feedback);
        mLlExtend = (LinearLayout) findViewById(R.id.ll_extend);


    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        if (SpUtils.isContains(mActivity, OrderUser.class.getSimpleName())) {
            OrderUser user = SpUtils.getBean(mActivity, OrderUser.class);
            mPresenter.getMyUserInfo(user.getUserId());
            mTvUsername.setText(user.getNickName());
            mTvAccount.setText(user.getUserId());
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        if (SpUtils.isContains(mActivity, User.class.getSimpleName())) {
            mUser = SpUtils.getBean(mActivity, User.class);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initAllMembersView(savedInstanceState);
    }

    @Override
    public int getLayoutResourceID() {
        return R.layout.fragment_my;
    }

    @Override
    public void initListener() {
        mLlPersonInfo.setOnClickListener(this);
        mLlExit.setOnClickListener(this);
        mLlFeedback.setOnClickListener(this);
        mLlScanCode.setOnClickListener(this);
        mLlCoin.setOnClickListener(this);
        mLlClearCache.setOnClickListener(this);
        mLlExtend.setOnClickListener(this);
        mLlHelp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_person_info:
                startActivity(PersonInfoActivity.class);
                break;
            case R.id.ll_scan_code:
                Intent intent = new Intent(getActivity(), SigninActivity.class);
                getActivity().startActivityForResult(intent, 1100);
                break;
            case R.id.ll_exit:
                showBottomDialog("您确定要退出登录吗？", "确定", "取消", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        SpUtils.remove(getContext(), "UserToken");
                        startActivity(LoginActivity.class);
                        mActivity.finish();
                    }
                }, null);
                break;
            case R.id.ll_extend:
                if (mUser != null && mUser.getBirthday() != null && !TextUtils.isEmpty(mUser.getGender()) && !TextUtils.isEmpty(mUser.getMail())) {
                    startActivity(ExtendActivity.class);
                } else {
                    showBottomDialog("需要完善个人资料才能进入，请您\n完善个人 资料", "确定", "取消", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dismissBottomDialog();
                            startActivity(PersonInfoActivity.class);
                        }
                    }, null);
                }
                break;
            case R.id.ll_coin:
                startActivity(UserScoreActivity.class);
                break;
            case R.id.ll_feedback:
                if (isQQInstall(getContext())) {
                    final String qqUrl = "mqqwpa://im/chat?chat_type=wpa&uin=" + QQNUM + "&version=1";
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(qqUrl)));
                } else {
                    showToast("请安装QQ客户端");
                }
                break;
            case R.id.ll__help:
                startActivity(NoticeActivity.class);
                break;
            case R.id.ll_clear_cache:
                showToast("清除成功");
                break;
            default:
                break;
        }
    }

    private boolean isQQInstall(Context context) {
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        if (pinfo != null) {
            for (int i = 0; i < pinfo.size(); i++) {
                String pn = pinfo.get(i).packageName;
                //通过遍历应用所有包名进行判断
                if (pn.equals("com.tencent.mobileqq")) {
                    return true;
                }
            }
        }
        return false;
    }


    @Override
    public void getMyUserInfoSucc(User user) {
        mUser = user;
        if (mUser != null) {
            user.setPassword((String) SpUtils.get(mActivity, "pwd", ""));
            SpUtils.putBean(mActivity, user);
        }
    }

    @Override
    public void getMyUserInfoFail(String msg) {
        mUser = null;

    }
}

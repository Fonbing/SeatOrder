package com.lxt.seatorder.ui.activity.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxt.seatorder.R;
import com.lxt.seatorder.bean.OrderUser;
import com.lxt.seatorder.bean.UserToken;
import com.lxt.seatorder.mvp.MVPBaseActivity;
import com.lxt.seatorder.ui.activity.MainActivity;
import com.lxt.seatorder.utils.DateUtil;
import com.lxt.seatorder.utils.SpUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends MVPBaseActivity<LoginContract.View, LoginPresenter> implements LoginContract.View, View.OnClickListener {

    private ImageView imageView;
    private TextView textView;
    private int count = 0;
    private ImageView mIvBackground;
    private TextView mTvNewDay;
    private TextView mTvJiayou;
    private EditText mEtUsername;
    private EditText mEtPassword;
    private Button mBtnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutResourceID() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mIvBackground = findViewById(R.id.iv_bacground);
        mTvNewDay = findViewById(R.id.tv_new_day);
        mTvJiayou = findViewById(R.id.tv_jiayou);
        mEtUsername = findViewById(R.id.et_username);
        mEtPassword = findViewById(R.id.et_password);
        mBtnLogin = findViewById(R.id.btn_login);
        if (Integer.parseInt(DateUtil.dateToString(new Date(), DateUtil.HOUR)) > 18)  //晚上6点切换图片
            mIvBackground.setImageDrawable(getResources().getDrawable(R.drawable.good_night_img, null));
    }

    @Override
    protected void initListener() {
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    protected void initData() {

        String username = (String) SpUtils.get(mContext, "username", "");
        String pwd = (String) SpUtils.get(mContext, "pwd", "");
        mEtUsername.setText(username);
        mEtPassword.setText(pwd);
    }

    @Override
    public void loginSuccess(UserToken userToken) {
        SpUtils.clear(mContext);
        SpUtils.put(mContext, "username", mEtUsername.getText().toString());
        SpUtils.put(mContext, "pwd", mEtPassword.getText().toString());
        SpUtils.putBean(mContext, userToken);
        Map<String, String> map = new HashMap<>();
        map.put("accessToken", userToken.getAccessToken());
        map.put("appId", userToken.getAppId());
        map.put("appSecret", userToken.getAppSecret());
        mPresenter.getUserInfo(map);

    }

    @Override
    public void loginFailed(String message) {
        dismissLoadingDialog();
        showToast(message);
    }

    @Override
    public void loginError() {
        dismissLoadingDialog();
        showErr();
    }

    @Override
    public void loginComplete() {
        dismissLoadingDialog();
    }

    @Override
    public void getUserInfoSuccess(OrderUser orderUser) {
        dismissLoadingDialog();
        SpUtils.putBean(mContext, orderUser);
        showToast("登录成功");
        finish();
        startActivity(MainActivity.class);
    }

    @Override
    public void getUserInfoSFail(String msg) {
        dismissLoadingDialog();
        showToast(msg);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                String username = mEtUsername.getText().toString().trim();
                String password = mEtPassword.getText().toString().trim();
                if (TextUtils.isEmpty(username))
                    showToast("用户名不可为空");
                else if (TextUtils.isEmpty(password))
                    showToast("密码不可为空");
                else if (!password.equals("123456"))
                    showToast("用户名或密码不正确");
                else {
                    showLoadingDialog();
//                    mPresenter.login(username, password);
                    loginTest();
                }
                break;
            default:
                break;
        }
    }

    private void loginTest() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI0NzAxMDAwYy0zYjZjLTRhMTAtOTM0Ny1jNGUzYWQ1ODIzNzEiLCJpYXQiOjE1ODk1OTg4MTEsInN1YiI6IntcInVpZFwiOlwiMjAxODEwNjIwMzM0XCIsXCJhcHBJZFwiOlwiMmM5MmI2NGYtNDVkZi00MDJiLTlmMGMtMTcyZTE1ODllZWE5XCIsXCJzZXNzaW9uSWRcIjpcIjczYTY0Yjc4LWI3M2ItNGE5YS1hYmU5LTA3YTIyY2ExM2U3ZFwiLFwidGltZVwiOjE1ODk1OTg4MTE4ODd9IiwiZXhwIjoxNTg5NjA2MDExfQ.cWprHIpgV2F_8n1kkcltlOZoOsDi3t9AHgjvhc_6bew";
        String appid = "2c92b64f-45df-402b-9f0c-172e1589eea9";
        String appS = "ab6b8467-8299-4d49-95fd-3cb2c169041f";
        UserToken userToken = new UserToken();
        userToken.setAccessToken(token);
        userToken.setAppId(appid);
        userToken.setAppSecret(appS);
        loginSuccess(userToken);
    }
}

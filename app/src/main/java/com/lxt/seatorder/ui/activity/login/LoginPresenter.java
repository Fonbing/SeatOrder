package com.lxt.seatorder.ui.activity.login;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.JsonObject;
import com.lxt.seatorder.MyApplication;
import com.lxt.seatorder.api.Callback;
import com.lxt.seatorder.api.ErrorHandler;
import com.lxt.seatorder.bean.OrderUser;
import com.lxt.seatorder.bean.UserToken;
import com.lxt.seatorder.mvp.BasePresenterImpl;
import com.lxt.seatorder.utils.AppConfig;
import com.lxt.seatorder.utils.JsonUtils;
import com.lxt.seatorder.utils.SpUtils;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Response;

/**
 * MVPPlugin
 * 邮箱 784787081@qq.com
 */

public class LoginPresenter extends BasePresenterImpl<LoginContract.View> implements LoginContract.Presenter {
    private final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrdpdYMaIJjCT9/2aeyr442SR930wuykmpQWh+8p+DbZ/BzYMYOMFjzinf8bPYgMjRTHM/LiJfK1FP+Id1FKlpeUYansdAz7tpl4FzqALnhGxaAw2CqPMVs8brep142LOOyVnrvUz3XrZ+DrfflWpixwV4825HMhGUDfnNL6iS6wIDAQAB";


    @SuppressLint("CheckResult")
    @Override
    public void login(String username, String password) {
        new LoginModel().getAccessToken(false, username, password, new Callback<UserToken>() {
            @Override
            public Response onSuccess(UserToken data) {
                mView.loginSuccess(data);
                return null;
            }

            @Override
            public void onFailure(String msg) {
                mView.loginFailed(msg);
            }

            @Override
            public void onError() {
                mView.loginError();
            }

            @Override
            public void onComplete() {
                mView.loginComplete();
            }
        });
    }

    @Override
    public void getUserInfo(Map<String, String> map) {

        mAPIWrapper.getUserInfo(map).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<JsonObject>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(JsonObject jsonObject) {
                        if (jsonObject.size() > 1) {
                            Log.i(AppConfig.TAG, "onNext: " + jsonObject.toString());
                            OrderUser orderUser = JsonUtils.json2Entity(jsonObject.toString(), OrderUser.class);
                            mView.getUserInfoSuccess(orderUser);
                        } else
                            mView.getUserInfoSFail("获取用户信息失败");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(AppConfig.TAG, "onError: " + ErrorHandler.errorMessage(e));
                        mView.getUserInfoSFail(ErrorHandler.errorMessage(e));
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}

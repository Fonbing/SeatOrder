package com.lxt.seatorder.ui.activity.login;

import android.annotation.SuppressLint;
import android.util.Log;

import com.google.gson.JsonObject;
import com.lxt.seatorder.MyApplication;
import com.lxt.seatorder.api.APIWrapper;
import com.lxt.seatorder.api.Callback;
import com.lxt.seatorder.api.ErrorHandler;
import com.lxt.seatorder.bean.UserToken;
import com.lxt.seatorder.utils.EncryptUtil;
import com.lxt.seatorder.utils.JsonUtils;
import com.lxt.seatorder.utils.SpUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

import static com.lxt.seatorder.utils.AppConfig.TAG;

/**
 * Created by Lxt Jxfen on 2019-12-13.
 * email: 1771874056@qq.com
 */
public class LoginModel implements LoginContract.Model {
    @SuppressLint("CheckResult")
    private final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCrdpdYMaIJjCT9/2aeyr442SR930wuykmpQWh+8p+DbZ/BzYMYOMFjzinf8bPYgMjRTHM/LiJfK1FP+Id1FKlpeUYansdAz7tpl4FzqALnhGxaAw2CqPMVs8brep142LOOyVnrvUz3XrZ+DrfflWpixwV4825HMhGUDfnNL6iS6wIDAQAB";

    @SuppressLint("CheckResult")
    @Override
    public UserToken getAccessToken(boolean isRefreshToken, String username, String password, final Callback<UserToken> callback) {

        String account = EncryptUtil.getRandomString(10) + "-" + username + "-" + password + "-" + (16 * new Date().getTime() - 2345) + "-" + (3 * new Date().getTime());
        Map<String, String> para = new HashMap<>();
        try {
            para.put("account", EncryptUtil.publicEncrypt(account, publicKey));
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "login: " + "加密失败");
        }
        para.put("passWord", EncryptUtil.getRandomString(10));
        para.put("verfyCode", "");
        para.put("redirectURI", "http://zzyy.sdyu.edu.cn:1081");

        if (isRefreshToken) {
            JsonObject jsonObject = null;
            try {
                jsonObject = APIWrapper.getInstance().syncLogin(para).execute().body();
                if (jsonObject.size() == 1) {
                    String code = jsonObject.get("redirectURL").getAsString().split("=")[1];  //getAsString()会去除字符串中的双引号
//                    Log.i(TAG, "apply: " + code);
                    UserToken userToken = APIWrapper.getInstance().scncGetAccessToken(code).execute().body();
                    SpUtils.putBean(MyApplication.applicationContext, userToken);
                    return userToken;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Observable<JsonObject> login = APIWrapper.getInstance().login(para);
            login.flatMap(new Function<JsonObject, ObservableSource<JsonObject>>() {
                @Override
                public ObservableSource<JsonObject> apply(JsonObject jsonObject) throws Exception {
                    //将网络请求一转换成请求二 发送第二次网络请求的事件
                    Observable<JsonObject> jsonObjectObservable = null;
                    Log.i(TAG, "apply: 第一次" + jsonObject.toString());
                    if (jsonObject.size() == 1) {
                        String code = jsonObject.get("redirectURL").getAsString().split("=")[1];  //getAsString()会去除字符串中的双引号
//                    Log.i(TAG, "apply: " + code);
                        jsonObjectObservable = APIWrapper.getInstance().getAccessToken(code);
                    }
                    return jsonObjectObservable;
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())//(初始被观察者)切换到IO线程 执行第一个网络请求
                    .subscribe(new Consumer<JsonObject>() {
                        @Override
                        public void accept(JsonObject jsonObject) throws Exception {                              //第二次网络请求成功
//                        Log.i(TAG, "accept: 第二次" + jsonObject.toString() + jsonObject.size());
                            if (jsonObject.size() == 5) {
                                UserToken userToken = JsonUtils.json2Entity(jsonObject.toString(), UserToken.class);
                                callback.onSuccess(userToken);
                            }
                        }
                    }, new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Exception {
                            //对Error事件做出回应
                            Log.i(TAG, "accept: getToken失败" + throwable);
                            String errMsg = ErrorHandler.errorMessage(throwable);
                            if (errMsg == null) {
                                callback.onError();
                            } else
                                callback.onFailure(errMsg);
                        }
                    });

            callback.onComplete();
        }
        return null;
    }
}

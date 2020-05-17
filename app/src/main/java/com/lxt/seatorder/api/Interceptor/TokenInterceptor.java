package com.lxt.seatorder.api.Interceptor;

import android.util.Log;

import com.lxt.seatorder.MyApplication;
import com.lxt.seatorder.bean.BaseBean;
import com.lxt.seatorder.bean.UserToken;
import com.lxt.seatorder.ui.activity.login.LoginModel;
import com.lxt.seatorder.utils.AppConfig;
import com.lxt.seatorder.utils.JsonUtils;
import com.lxt.seatorder.utils.SpUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Lxt Jxfen on 2019-12-13.
 * email: 1771874056@qq.com
 */
public class TokenInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        UserToken userToken = SpUtils.getBean(MyApplication.applicationContext, UserToken.class);
        Request request_old = chain.request()
                .newBuilder()
                .header("Authorization", userToken == null ? "" : userToken.getAccessToken())
                .build();
        Response response = chain.proceed(request_old);

        String content = response.body().string();   //此方法只能调用一次
        if (response.code() == 400) {
            if (isTokenExpired(content)) {
                String accessToken = getNewToken();
                //使用新的Token，创建新的请求
                Request newRequest = chain.request()
                        .newBuilder()
                        .header("Authorization", accessToken)
                        .build();
                //重新请求
                return chain.proceed(newRequest);
            }
        }

        MediaType mediaType = response.body().contentType();
        Log.i(AppConfig.TAG, content);
        return response.newBuilder()
                .body(ResponseBody.create(mediaType, content))
                .build();
    }

    private boolean isTokenExpired(String str) {
        Log.i(AppConfig.TAG, "isTokenExpired: " + str);
        BaseBean baseBean = JsonUtils.json2Entity(str, BaseBean.class);
        Log.i(AppConfig.TAG, "intercept: " + baseBean.getMsg());
        //2003：登录已失效 2002 token已过期
        if (baseBean.getCode() == 2002 || baseBean.getCode() == 2003) {
            return true;
        }
        return false;
    }

    private String getNewToken() throws IOException {
        // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
        String username = (String) SpUtils.get(MyApplication.applicationContext, "username", "");
        String pwd = (String) SpUtils.get(MyApplication.applicationContext, "pwd", "");
        UserToken userToken = new LoginModel().getAccessToken(true, username, pwd, null);
        return userToken.getAccessToken();
    }
}

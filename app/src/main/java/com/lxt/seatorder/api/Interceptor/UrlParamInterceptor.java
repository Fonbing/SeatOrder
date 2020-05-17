package com.lxt.seatorder.api.Interceptor;

import java.io.IOException;
import java.util.Date;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Lxt Jxfen on 2019-12-12.
 * email: 1771874056@qq.com
 */
public class UrlParamInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        //偷天换日
        Request oldRequest = chain.request();

        HttpUrl newUrl = oldRequest.url();
        if (oldRequest.method().equals("GET")) {
            //拿到拥有以前的request里的url的那些信息的builder
            HttpUrl.Builder builder = oldRequest
                    .url()
                    .newBuilder();

            //得到新的url（已经追加好了参数）,追加时间戳
            newUrl = builder.addQueryParameter("_", String.valueOf(new Date().getTime()))
                    .build();
        }
        //利用新的Url，构建新的request，并发送给服务器
        Request newRequest = oldRequest
                .newBuilder()
                .url(newUrl)
                .build();
        return chain.proceed(newRequest);
    }
}

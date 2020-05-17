package com.lxt.seatorder.api.Interceptor;

import com.lxt.seatorder.utils.AppConfig;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.lxt.seatorder.utils.AppConfig.AliyunIP;
import static com.lxt.seatorder.utils.AppConfig.DebugIP;
import static com.lxt.seatorder.utils.AppConfig.IntranetIP;
import static com.lxt.seatorder.utils.AppConfig.PublicIP;

/**
 * Created by Lxt Jxfen on 2019-12-12.
 * email: 1771874056@qq.com
 */
public class BaseUrlInterceptor implements Interceptor {
    //        实现动态切换baseurl
    @Override
    public Response intercept(Chain chain) throws IOException {

        //获取request
        Request request = chain.request();
        //从request中获取原有的HttpUrl实例oldHttpUrl
        HttpUrl oldHttpUrl = request.url();
        //获取request的创建者builder
        Request.Builder builder = request.newBuilder();
        //从request中获取headers，通过给定的键url_name
        List<String> headerValues = request.headers("url_name");
        HttpUrl newBaseUrl = oldHttpUrl;

        if (AppConfig.isTntranet) {        //如果是内网ip
            newBaseUrl = HttpUrl.parse(IntranetIP);
        } else newBaseUrl = HttpUrl.parse(PublicIP);

        int port = oldHttpUrl.port();
        if (headerValues != null && headerValues.size() > 0) {
            //如果有这个header，先将配置的header删除，因此header仅用作app和okhttp之间使用
            builder.removeHeader("url_name");
            //匹配获得新的BaseUrl
            String headerValue = headerValues.get(0);
            if ("aliyun".equals(headerValue)) {
                newBaseUrl = HttpUrl.parse(AliyunIP);
                port = 8080;
            } else if ("login".equals(headerValue)) {
                port = 80;
            } else if ("debug".equals(headerValue)) {
                newBaseUrl = HttpUrl.parse(DebugIP);
                port = 8080;
            }
        }
        // 重建新的HttpUrl，修改需要修改的url部分
        HttpUrl newFullUrl = oldHttpUrl
                .newBuilder()
                // 更换网络协议
                .scheme(newBaseUrl.scheme())
                // 更换主机名
                .host(newBaseUrl.host())
                // 更换端口
                .port(port)
                .build();
//        Log.i("Url", "intercept: " + newFullUrl.toString());
        return chain.proceed(builder.url(newFullUrl).build());
    }

}
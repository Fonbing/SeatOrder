package com.lxt.seatorder.api;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lxt.seatorder.api.Interceptor.BaseUrlInterceptor;
import com.lxt.seatorder.api.Interceptor.CacheIntercepter;
import com.lxt.seatorder.api.Interceptor.TokenInterceptor;
import com.lxt.seatorder.api.Interceptor.UrlParamInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.lxt.seatorder.utils.AppConfig.IntranetIP;

public class RetrofitHelper {


    private static Retrofit mRetrofit;
    private static RetrofitService mRetrofitService;
    private static Context mContext;

    public RetrofitHelper(Context context) {
        mContext = context;
    }

    public RetrofitHelper() {
    }

    private static Retrofit getRetrofit() {
        if (mRetrofit == null) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                @Override
                public void log(String message) {
                    //打印retrofit日志
                    Log.i("RetrofitLog", "retrofitBack = " + message);
                }
            });
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            File cacheFile = new File(mContext.getExternalCacheDir(), "HttpCache");//缓存地址
            Cache cache = new Cache(cacheFile, 1024 * 1024 * 50); //大小50Mb
            CacheIntercepter cacheIntercepter = new CacheIntercepter(mContext);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(new BaseUrlInterceptor())
                    .addInterceptor(new UrlParamInterceptor())
                    .addInterceptor(cacheIntercepter)
                    .addInterceptor(new TokenInterceptor())
                    .addNetworkInterceptor(cacheIntercepter)
                    .addInterceptor(loggingInterceptor)
                    .connectTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS).build();

            Gson gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .serializeNulls()
                    .create();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(IntranetIP)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return mRetrofit;
    }

    protected static RetrofitService getRetrofitService() {
        if (mRetrofitService == null) {
            mRetrofitService = getRetrofit().create(RetrofitService.class);
        }
        return mRetrofitService;
    }


}

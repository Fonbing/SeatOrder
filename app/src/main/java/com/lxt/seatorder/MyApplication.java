package com.lxt.seatorder;


import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.lxt.seatorder.utils.OrderTypeUtil;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * Created by Lxt Jxfen on 2019-11-24.
 * email: 1771874056@qq.com
 */
public class MyApplication extends Application {
    public static Context applicationContext;
    //声明AMapLocationClient类对象

    private static final String APP_ID = "wx41e3f14256fb39d7";
    // IWXAPI 是第三方app和微信通信的openApi接口
    public static IWXAPI WXAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        /*AppConfig.STARTTIME.set(Calendar.HOUR_OF_DAY, 6);
        AppConfig.STARTTIME.set(Calendar.MINUTE, 0);
        AppConfig.ENDTIME.set(Calendar.HOUR_OF_DAY, 22);
        AppConfig.ENDTIME.set(Calendar.MINUTE, 0);*/
        applicationContext = getApplicationContext();
        OrderTypeUtil.addStatus();

        System.setProperty("key", "value");

        regToWx();
        //        LitePal.initialize(this);

    }

    private void regToWx() {
        // 通过WXAPIFactory工厂，获取IWXAPI的实例
        WXAPI = WXAPIFactory.createWXAPI(this, APP_ID, true);
        // 将应用的appId注册到微信
        WXAPI.registerApp(APP_ID);
        //建议动态监听微信启动广播进行注册到微信
        registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 将该app注册到微信
                WXAPI.registerApp(APP_ID);
            }
        }, new IntentFilter(ConstantsAPI.ACTION_REFRESH_WXAPP));

    }

}

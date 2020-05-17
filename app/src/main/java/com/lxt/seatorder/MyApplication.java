package com.lxt.seatorder;


import android.app.Application;
import android.content.Context;

import com.lxt.seatorder.utils.OrderTypeUtil;

/**
 * Created by Lxt Jxfen on 2019-11-24.
 * email: 1771874056@qq.com
 */
public class MyApplication extends Application {
    public static Context applicationContext;
    //声明AMapLocationClient类对象

    @Override
    public void onCreate() {
        super.onCreate();
        /*AppConfig.STARTTIME.set(Calendar.HOUR_OF_DAY, 6);
        AppConfig.STARTTIME.set(Calendar.MINUTE, 0);
        AppConfig.ENDTIME.set(Calendar.HOUR_OF_DAY, 22);
        AppConfig.ENDTIME.set(Calendar.MINUTE, 0);*/
        applicationContext = getApplicationContext();
        OrderTypeUtil.addStatus();

        System.setProperty("key","value");



        //        LitePal.initialize(this);

    }

}

package com.lxt.seatorder.utils;

import android.view.View;

import com.lxt.seatorder.ui.listener.MyClickListener;

import java.lang.reflect.Field;

/**
 * Created by Lxt user on 2018/5/5.
 * good good study ，day day up
 */
public class ClickFilter {
    public static void setFilter(View view, long ms) {
        try {
            Field field = View.class.getDeclaredField("mListenerInfo");
            field.setAccessible(true);
            Class listInfoType = field.getType();
            Object listinfo = field.get(view);
            Field onclickField = listInfoType.getField("mOnClickListener");
            View.OnClickListener origin = (View.OnClickListener) onclickField.get(listinfo);
            onclickField.set(listinfo, new MyClickListener.ClickProxy(origin, ms));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

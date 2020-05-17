package com.lxt.seatorder.utils;

import android.content.Context;
import android.util.SparseArray;

import com.lxt.seatorder.R;

/**
 * Created by Lxt Jxfen on 2020/4/3.
 * email: 1771874056@qq.com
 */
public class OrderTypeUtil {
    private static final SparseArray<String> TYPEMAP = new SparseArray<>();
    private static final SparseArray<String> STATUSMAP = new SparseArray<>();

    public static void addType() {

    }

    public static void addStatus() {
        TYPEMAP.put(0, "待确认");
        TYPEMAP.put(11, "个人预约");
        TYPEMAP.put(12, "邻座");
        TYPEMAP.put(13, "约座");
        TYPEMAP.put(14, "续约");
        TYPEMAP.put(15, "换座");
        TYPEMAP.put(21, "个人签到");
        TYPEMAP.put(22, "自动签到");
        TYPEMAP.put(31, "暂离");
        TYPEMAP.put(41, "取消暂离");
        TYPEMAP.put(51, "个人签退");
        TYPEMAP.put(52, "自动签退");
        TYPEMAP.put(61, "个人取消");
        TYPEMAP.put(62, "系统取消");
        TYPEMAP.put(63, "管理员取消");
    }

    public static String getStatus(int code) {
        return TYPEMAP.get(code, "未知状态");
    }


    public static int getStatusColor(Context context, int code) {
        switch (code) {
            case 11:
                return context.getResources().getColor(R.color.colorTextTitleSelect);
            case 21:
            case 22:
                return context.getResources().getColor(R.color.highlight);
            default:
                return context.getResources().getColor(R.color.darkgray);
        }
    }
}

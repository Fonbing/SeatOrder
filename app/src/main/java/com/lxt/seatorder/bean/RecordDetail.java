package com.lxt.seatorder.bean;

/**
 * Created by Lxt Jxfen on 2020/4/6.
 * email: 1771874056@qq.com
 */
public class RecordDetail {
    private String context;//内容
    private String ftime;
    private String time;//时间

    public String getContext() {
        return context;
    }

    public RecordDetail setContext(String context) {
        this.context = context;
        return this;
    }

    public String getFtime() {
        return ftime;
    }

    public RecordDetail setFtime(String ftime) {
        this.ftime = ftime;
        return this;

    }

    public String getTime() {
        return time;
    }

    public RecordDetail setTime(String time) {
        this.time = time;
        return this;
    }


    @Override
    public String toString() {
        return "LogisticsData{" +
                "context='" + context + '\'' +
                ", ftime='" + ftime + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}

package com.lxt.seatorder.bean;

public class BaseBean {
    /**
     * count : 0
     * data :
     * errcode : -1
     * message : 密码不正确！
     */

    private int count;
    private int code;
    private String msg;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

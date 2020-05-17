package com.lxt.seatorder.api;

import com.google.gson.Gson;
import com.lxt.seatorder.bean.BaseBean;

import retrofit2.HttpException;

/**
 * Created by Lxt Jxfen on 2019-12-12.
 * email: 1771874056@qq.com
 */
public class ErrorHandler {
    private static String data = "{\"Success\": false,\"StatusCode\": 500,\"Message\": \"处理失败\", \"ErrorInfo\": {\"ErrorMessage\": \"网络请求错误\",\"ErrorCode\": \"404\" },\"Result\": null}";

    public static BaseBean handle(Throwable throwable) {
        if (throwable instanceof HttpException) {
            HttpException error = (HttpException) throwable;
            try {
                String string = error.response().errorBody().string();
                if (isJSONValid(string)) {
                    return new Gson().fromJson(string, BaseBean.class);
                } else {
                    BaseBean bb = new BaseBean();
                    bb.setMsg("网络请求错误");
                    return bb;
                }
            } catch (Exception e) {
                e.printStackTrace();
                BaseBean bb = new BaseBean();
                bb.setMsg(e.getMessage());
                return bb;
            }
        } else {
            throwable.printStackTrace();
            BaseBean bb = new BaseBean();
            bb.setMsg("非http异常");
            return bb;
        }
    }

    public static String errorMessage(Throwable e) {
        BaseBean errBody = ErrorHandler.handle(e);
        if (errBody != null) {
            return errBody.getMsg();
        }
        return "未知错误";
    }

    public final static boolean isJSONValid(String jsonInString) {
        try {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
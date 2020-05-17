package com.lxt.seatorder.api;


import android.content.Context;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.lxt.seatorder.bean.OrderRecord;
import com.lxt.seatorder.bean.RoomOrderInfo;
import com.lxt.seatorder.bean.ScheduleTime;
import com.lxt.seatorder.bean.User;
import com.lxt.seatorder.bean.UserToken;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;

public class APIWrapper extends RetrofitHelper implements RetrofitService {
    private static APIWrapper mAPIWrapper;
    private static RetrofitService mAPIService;

    public APIWrapper(Context context) {
        super(context);
    }

    public APIWrapper() {
        super();
    }

    public static APIWrapper getInstance(Context context) {
        if (mAPIWrapper == null) {
            mAPIWrapper = new APIWrapper(context);
            mAPIService = getRetrofitService();
        }
        return mAPIWrapper;
    }

    public static APIWrapper getInstance() {
        if (mAPIWrapper == null) {
            mAPIWrapper = new APIWrapper();
            mAPIService = getRetrofitService();
        }
        return mAPIWrapper;
    }

    @Override
    public Observable<JsonObject> login(Map<String, String> map) {
        return mAPIService.login(map);
    }

    @Override
    public Observable<JsonObject> getUserInfo(Map<String, String> map) {
        return mAPIService.getUserInfo(map);
    }

    @Override
    public Call<UserToken> scncGetAccessToken(String code) {
        return mAPIService.scncGetAccessToken(code);
    }

    @Override
    public Observable<JsonObject> getAccessToken(String code) {
        return mAPIService.getAccessToken(code);
    }

    @Override
    public Observable<JsonObject> getVenue(int parentDirId) {
        return mAPIService.getVenue(parentDirId);
    }

    @Override
    public Call<JsonObject> syncLogin(Map<String, String> map) {
        return mAPIService.syncLogin(map);
    }

    @Override
    public Observable<JsonArray> submitOrder(String authorization, int dirID, int resourceId, JsonArray payload) {
        return mAPIService.submitOrder(authorization, dirID, resourceId, payload);
    }

    @Override
    public Observable<JsonObject> getRoomInfo(int roomId) {
        return mAPIService.getRoomInfo(roomId);
    }

    @Override
    public Observable<List<RoomOrderInfo>> getRoomOrderInfo(int dirId, String userId, String sliceArrayList) {
        return mAPIService.getRoomOrderInfo(dirId, userId, sliceArrayList);
    }

    @Override
    public Observable<JsonObject> getOrderHistory(String userId, int page, int size, int type) {
        return mAPIService.getOrderHistory(userId, page, size, type);
    }

    @Override
    public Observable<JsonObject> getInuseSeat(String userId) {
        return mAPIService.getInuseSeat(userId);
    }

    @Override
    public Observable<JsonObject> updateOrderStatus(int OrderItemId, int status, String userId, int terminal) {
        return mAPIService.updateOrderStatus(OrderItemId, status, userId, terminal);
    }

    @Override
    public Observable<OrderRecord> getRecordDetail(int orderItemId) {
        return mAPIService.getRecordDetail(orderItemId);
    }

    @Override
    public Observable<List<String>> getSeatName(int seatId) {
        return mAPIService.getSeatName(seatId);
    }

    @Override
    public Observable<JsonObject> getScore(String userId, int page, int size) {
        return mAPIService.getScore(userId, page, size);
    }

    @Override
    public Observable<JsonObject> getMyUserinfo(String userId) {
        return mAPIService.getMyUserinfo(userId);
    }


    @Override
    public Observable<JsonObject> addMyUserInfo(User user) {
        return mAPIService.addMyUserInfo(user);
    }

    @Override
    public Observable<JsonObject> getScheduleTask(String userId, int type) {
        return mAPIService.getScheduleTask(userId, type);
    }

    @Override
    public Observable<JsonObject> addScheduleTask(ScheduleTime scheduleTime) {
        return mAPIService.addScheduleTask(scheduleTime);
    }

    @Override
    public Observable<JsonObject> updateScheduleStatus(String userId, int status, int type) {
        return mAPIService.updateScheduleStatus(userId, status, type);
    }

    @Override
    public Observable<JsonObject> getNoticeInfo() {
        return mAPIService.getNoticeInfo();
    }

}

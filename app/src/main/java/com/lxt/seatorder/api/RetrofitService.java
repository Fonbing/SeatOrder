package com.lxt.seatorder.api;

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
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lxt Jxfen on 2019-12-10.
 * email: 1771874056@qq.com
 */
public interface RetrofitService {


    @Headers("url_name:login")
    @POST("/oauth/v1/login")
    @FormUrlEncoded
    Call<JsonObject> syncLogin(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("/seatServer/v1/userAccount/accessToken")
    Call<UserToken> scncGetAccessToken(@Field("code") String code);

    @Headers("url_name:login")
    @POST("/oauth/v1/login")
    @FormUrlEncoded
    Observable<JsonObject> login(@FieldMap Map<String, String> map);


    @POST("/oauth/v1/user")
    @FormUrlEncoded
    Observable<JsonObject> getUserInfo(@FieldMap Map<String, String> map);

    @GET("/seatServer/v1/resource-dir/venue")
    Observable<JsonObject> getVenue(@Query("parentDirId") int parentDirId);


    @FormUrlEncoded
    @POST("/seatServer/v1/userAccount/accessToken")
    Observable<JsonObject> getAccessToken(@Field("code") String code);

    @POST("seatServer/v1/reservation/userReservation?terminal=1")
    @Headers("Content-Type:application/json")
    Observable<JsonArray> submitOrder(@Header("Authorization") String authorization, @Query("dirId") int dirID, @Query("resourceId") int resourceId, @Body JsonArray payload);


    @GET("/seatServer/v1/tempresource/{roomId}")
    Observable<JsonObject> getRoomInfo(@Path("roomId") int roomId);


    @GET("/seatServer/v1/resource/lockSeat")
    @Headers("Accept: application/json;charset=UTF-8")
    Observable<List<RoomOrderInfo>> getRoomOrderInfo(@Query("dirId") int dirId, @Query("userId") String userId, @Query("sliceArrayList") String sliceArrayList);


    @GET("/orderServer/v1/orderItem/user/{userId}")
    Observable<JsonObject> getOrderHistory(@Path("userId") String userId, @Query("page") int page, @Query("size") int size, @Query("type") int type);


    @GET("/orderServer/v1/orderItem/inuse/{userId}")
    Observable<JsonObject> getInuseSeat(@Path("userId") String userId);


    /**
     * 更新预约条目状态
     *
     * @param OrderItemId 预约条目id
     * @param status      状态码 如下
     *                    11 预约未签到  暂离状态
     *                    21 个人签到
     *                    51 个人签退
     *                    52 系统签退
     *                    61 个人取消
     *                    62 系统取消
     * @param userId      用户id
     * @return
     */
    @PATCH("/orderServer/v1/orderItem/")
    @Headers("Content-Type:application/json")
    Observable<JsonObject> updateOrderStatus(@Query("orderItemId") int OrderItemId, @Query("status") int status, @Query("userId") String userId, @Query("terminal") int terminal);

    @GET("/orderServer/v1.1/orderItem/")
    Observable<OrderRecord> getRecordDetail(@Query("orderItemId") int orderItemId);

    @GET("/seatServer/v1/resource-dir/seatName/{seatId}")
    Observable<List<String>> getSeatName(@Path("seatId") int seatId);

    @GET("/userServer/v1/score")
    Observable<JsonObject> getScore(@Query("userId") String userId, @Query("page") int page, @Query("size") int size);

    @Headers("url_name:debug")
    @GET("/app/getUserInfo")
    Observable<JsonObject> getMyUserinfo(@Query("userId") String userId);

    @Headers("url_name:debug")
    @POST("/app/addUserInfo")
    Observable<JsonObject> addMyUserInfo(@Body User user);

    @Headers("url_name:debug")
    @GET("/app/getScheduleTask")
    Observable<JsonObject> getScheduleTask(@Query("userId") String userId, @Query("type") int type);

    @Headers("url_name:debug")
    @POST("/app/addScheduleTask")
    Observable<JsonObject> addScheduleTask(@Body ScheduleTime scheduleTime);


    @Headers("url_name:debug")
    @POST("/app/updateScheduleStatus")
    @FormUrlEncoded
    Observable<JsonObject> updateScheduleStatus(@Field("userId") String userId, @Field("status") int status, @Field("type") int type);

    @Headers("url_name:debug")
    @GET("/app/getNoticeInfo")
    Observable<JsonObject> getNoticeInfo();

}

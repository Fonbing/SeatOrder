package com.lxt.seatorder.bean;

import java.util.Date;

/**
 * Created by Lxt Jxfen on 2020/4/25.
 * email: 1771874056@qq.com
 */
public class ScheduleTime {
    private String userId;
    private int type;
    private Date orderStartTime1;
    private Date orderStartTime2;
    private Date orderEndTime1;
    private Date orderEndTime2;
    private String seatName;
    private String roomSeatId;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getOrderStartTime1() {
        return orderStartTime1;
    }

    public void setOrderStartTime1(Date orderStartTime1) {
        this.orderStartTime1 = orderStartTime1;
    }

    public Date getOrderStartTime2() {
        return orderStartTime2;
    }

    public void setOrderStartTime2(Date orderStartTime2) {
        this.orderStartTime2 = orderStartTime2;
    }

    public Date getOrderEndTime1() {
        return orderEndTime1;
    }

    public void setOrderEndTime1(Date orderEndTime1) {
        this.orderEndTime1 = orderEndTime1;
    }

    public Date getOrderEndTime2() {
        return orderEndTime2;
    }

    public void setOrderEndTime2(Date orderEndTime2) {
        this.orderEndTime2 = orderEndTime2;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public String getRoomSeatId() {
        return roomSeatId;
    }

    public void setRoomSeatId(String roomSeatId) {
        this.roomSeatId = roomSeatId;
    }
}

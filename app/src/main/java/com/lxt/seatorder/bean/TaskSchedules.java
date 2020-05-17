package com.lxt.seatorder.bean;

import java.util.Date;

public class TaskSchedules {
    private Integer id;

    private String userId;

    private String password;

    private String realName;

    private Integer typeCode;

    private Integer orderItemId;

    private String seatName;

    private Date orderStartTime;
    private Date orderEndTime;

    private String runTime;

    private Integer status;

    private Date finishTime;

    private Date createTime;
    private Integer roomId;
    private Integer seatId;

    public Date getOrderStartTime() {
        return orderStartTime;
    }

    public void setOrderStartTime(Date orderStartTime) {
        this.orderStartTime = orderStartTime;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getSeatId() {
        return seatId;
    }

    public void setSeatId(Integer seatId) {
        this.seatId = seatId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Integer getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(Integer typeCode) {
        this.typeCode = typeCode;
    }

    public Integer getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Integer orderItemId) {
        this.orderItemId = orderItemId;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName == null ? null : seatName.trim();
    }

    public Date getOrderEndTime() {
        return orderEndTime;
    }

    public void setOrderEndTime(Date orderEndTime) {
        this.orderEndTime = orderEndTime;
    }

    public String getRunTime() {
        return runTime;
    }

    public void setRunTime(String runTime) {
        this.runTime = runTime == null ? null : runTime.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
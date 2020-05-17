package com.lxt.seatorder.bean;

import java.util.List;

/**
 * Created by Lxt Jxfen on 2019-12-12.
 * email: 1771874056@qq.com
 */
public class OrderInfo {

    /**
     * createTime : 1554279227757
     * createBy : 201810620306
     * id : 3207
     * orderItemId : 3207
     * orderId : 3167
     * orderType : 11
     * resourceClass : 1
     * resourceType : 1
     * resourceId : 9538
     * buildId : 2914
     * floorId : 2962
     * roomId : 2963
     * resourceName : ["利行楼-二层-B","008","B"]
     * orderBy : 201810620306
     * OrderUser : {"id":163882,"userId":"201810620306","portrait":null,"nickName":"丁培良","realName":"丁培良","mobile":null,"mail":null,"idCard":null,"age":null,"gender":null,"province":null,"city":null,"area":null,"sysNumber":"201810620306","userStatus":0,"totalScore":15,"isReceiveAttention":1}
     * startTime : 1554336000000
     * endTime : 1554357600000
     * itemStatus : 11
     * minSignInTime : 1554335400000
     * maxSignInTime : 1554336900000
     * signOutTime : 1554357600000
     * leaveEndTime : null
     * other : []
     * terminal : null
     * sysNumber : 201810620306
     * orderName : 丁培良
     * departmentName : 信息工程学院-计算机科学与技术(专升本)-2018级-计科专升本18（3）
     * buildName : 利行楼
     * floorName : 二层
     * roomName : B
     * resourceDisplayName : 008-B
     * resources : null
     * aheadOrder : true
     */

    private long createTime;
    private String createBy;
    private int id;
    private int orderItemId;
    private int orderId;
    private int orderType;
    private int resourceClass;
    private int resourceType;
    private int resourceId;
    private int buildId;
    private int floorId;
    private int roomId;
    private String orderBy;
    private OrderUser orderUser;
    private long startTime;
    private long endTime;
    private int itemStatus;
    private long minSignInTime;
    private long maxSignInTime;
    private long signOutTime;
    private Object leaveEndTime;
    private Object terminal;
    private String sysNumber;
    private String orderName;
    private String departmentName;
    private String buildName;
    private String floorName;
    private String roomName;
    private String resourceDisplayName;
    private Object resources;
    private boolean aheadOrder;
    private List<String> resourceName;
    private List<?> other;

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(int orderItemId) {
        this.orderItemId = orderItemId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getOrderType() {
        return orderType;
    }

    public void setOrderType(int orderType) {
        this.orderType = orderType;
    }

    public int getResourceClass() {
        return resourceClass;
    }

    public void setResourceClass(int resourceClass) {
        this.resourceClass = resourceClass;
    }

    public int getResourceType() {
        return resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public int getFloorId() {
        return floorId;
    }

    public void setFloorId(int floorId) {
        this.floorId = floorId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public OrderUser getOrderUser() {
        return orderUser;
    }

    public void setOrderUser(OrderUser orderUser) {
        this.orderUser = orderUser;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }

    public int getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(int itemStatus) {
        this.itemStatus = itemStatus;
    }

    public long getMinSignInTime() {
        return minSignInTime;
    }

    public void setMinSignInTime(long minSignInTime) {
        this.minSignInTime = minSignInTime;
    }

    public long getMaxSignInTime() {
        return maxSignInTime;
    }

    public void setMaxSignInTime(long maxSignInTime) {
        this.maxSignInTime = maxSignInTime;
    }

    public long getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(long signOutTime) {
        this.signOutTime = signOutTime;
    }

    public Object getLeaveEndTime() {
        return leaveEndTime;
    }

    public void setLeaveEndTime(Object leaveEndTime) {
        this.leaveEndTime = leaveEndTime;
    }

    public Object getTerminal() {
        return terminal;
    }

    public void setTerminal(Object terminal) {
        this.terminal = terminal;
    }

    public String getSysNumber() {
        return sysNumber;
    }

    public void setSysNumber(String sysNumber) {
        this.sysNumber = sysNumber;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }

    public String getFloorName() {
        return floorName;
    }

    public void setFloorName(String floorName) {
        this.floorName = floorName;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getResourceDisplayName() {
        return resourceDisplayName;
    }

    public void setResourceDisplayName(String resourceDisplayName) {
        this.resourceDisplayName = resourceDisplayName;
    }

    public Object getResources() {
        return resources;
    }

    public void setResources(Object resources) {
        this.resources = resources;
    }

    public boolean isAheadOrder() {
        return aheadOrder;
    }

    public void setAheadOrder(boolean aheadOrder) {
        this.aheadOrder = aheadOrder;
    }

    public List<String> getResourceName() {
        return resourceName;
    }

    public void setResourceName(List<String> resourceName) {
        this.resourceName = resourceName;
    }

    public List<?> getOther() {
        return other;
    }

    public void setOther(List<?> other) {
        this.other = other;
    }

    
}

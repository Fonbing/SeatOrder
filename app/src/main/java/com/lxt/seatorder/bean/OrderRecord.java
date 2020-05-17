package com.lxt.seatorder.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Lxt Jxfen on 2019-12-31.
 * email: 1771874056@qq.com
 */
public class OrderRecord {


    /**
     * createTime : 1577665361000
     * createBy : 201810620334
     * createUser : null
     * id : 174172
     * orderItemId : 174172
     * orderId : 174117
     * orderType : 11
     * resourceClass : 1
     * resourceType : 1
     * resourceId : 9540
     * resourceName : ["利行楼-二层-B","008","D"]
     * orderBy : 201810620334
     * orderUser : {"id":156629,"userId":"201810620334","portrait":null,"nickName":"蒋方兵","realName":"蒋方兵","mobile":null,"mail":null,"idCard":null,"age":null,"gender":null,"province":null,"city":null,"area":null,"sysNumber":"201810620334","userStatus":0,"totalScore":9,"isReceiveAttention":1,"roles":null,"isFriend":null,"userRole":[],"reservationNum":0,"operateType":null,"cardNumber":"198F5B12","cardType":"1","departmentId":12628,"departmentName":"信息工程学院","userName":null,"passWord":null,"registerTime":null,"fromApp":null,"wxUser":null,"cardPassword":null,"createTime":1552905196000,"createBy":null,"createUser":null}
     * startTime : 1577775600000
     * endTime : 1577797200000
     * itemStatus : 11
     * minSignInTime : 1577775000000
     * maxSignInTime : 1577776500000
     * signOutTime : 1577797200000
     * leaveEndTime : null
     * operatingTerminal : null
     * buildId : 2914
     * floorId : 2962
     * roomId : 2963
     * other : []
     * behaviorRecords : null
     * sbuild : null
     * floor : null
     * room : null
     * seatName : null
     * user : null
     * terminal : -1
     * sysNumber : 201810620334
     * orderName : 蒋方兵
     * departmentId : 12628
     * departmentName : 信息工程学院
     * buildName : 利行楼
     * floorName : 二层
     * roomName : B
     * resourceDisplayName : 008-D
     * resources : null
     * number : null
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
    private String orderBy;
    private OrderUser orderUser;
    private long startTime;
    private long endTime;
    private int itemStatus;
    private long minSignInTime;
    private long maxSignInTime;
    private long signOutTime;
    private int buildId;
    private int floorId;
    private int roomId;
    private int terminal;
    private String sysNumber;
    private String orderName;
    private int departmentId;
    private String departmentName;
    private String buildName;
    private String floorName;
    private String roomName;
    private String resourceDisplayName;
    private boolean aheadOrder;
    private List<String> resourceName;
    private List<BehaviorRecordsBean> behaviorRecords;

    /**
     * behaviorRecords : [{"id":496157,"behaviorRecordId":496157,"behaviorType":11,"behaviorMode":0,"orderItemId":184472,"operatingTerminal":1,"createTime":1585905450000,"createBy":"201810620334","createUser":null},{"id":496160,"behaviorRecordId":496160,"behaviorType":61,"behaviorMode":0,"orderItemId":184472,"operatingTerminal":1,"createTime":1585919463000,"createBy":"201810620334","createUser":null}]
     */


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


    public int getTerminal() {
        return terminal;
    }

    public void setTerminal(int terminal) {
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

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
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

    public List<BehaviorRecordsBean> getBehaviorRecords() {
        return behaviorRecords;
    }

    public void setBehaviorRecords(List<BehaviorRecordsBean> behaviorRecords) {
        this.behaviorRecords = behaviorRecords;
    }

    public static   class BehaviorRecordsBean {
        /**
         * id : 496157
         * behaviorRecordId : 496157
         * behaviorType : 11
         * behaviorMode : 0
         * orderItemId : 184472
         * operatingTerminal : 1
         * createTime : 1585905450000
         * createBy : 201810620334
         * createUser : null
         */

        @SerializedName("id")
        private int idX;
        private int behaviorRecordId;
        private int behaviorType;
        private int behaviorMode;
        @SerializedName("orderItemId")
        private int orderItemIdX;
        private int operatingTerminal;
        @SerializedName("createTime")
        private long createTimeX;
        @SerializedName("createBy")
        private String createByX;
        private Object createUser;

        public int getIdX() {
            return idX;
        }

        public void setIdX(int idX) {
            this.idX = idX;
        }

        public int getBehaviorRecordId() {
            return behaviorRecordId;
        }

        public void setBehaviorRecordId(int behaviorRecordId) {
            this.behaviorRecordId = behaviorRecordId;
        }

        public int getBehaviorType() {
            return behaviorType;
        }

        public void setBehaviorType(int behaviorType) {
            this.behaviorType = behaviorType;
        }

        public int getBehaviorMode() {
            return behaviorMode;
        }

        public void setBehaviorMode(int behaviorMode) {
            this.behaviorMode = behaviorMode;
        }

        public int getOrderItemIdX() {
            return orderItemIdX;
        }

        public void setOrderItemIdX(int orderItemIdX) {
            this.orderItemIdX = orderItemIdX;
        }

        public int getOperatingTerminal() {
            return operatingTerminal;
        }

        public void setOperatingTerminal(int operatingTerminal) {
            this.operatingTerminal = operatingTerminal;
        }

        public long getCreateTimeX() {
            return createTimeX;
        }

        public void setCreateTimeX(long createTimeX) {
            this.createTimeX = createTimeX;
        }

        public String getCreateByX() {
            return createByX;
        }

        public void setCreateByX(String createByX) {
            this.createByX = createByX;
        }

        public Object getCreateUser() {
            return createUser;
        }

        public void setCreateUser(Object createUser) {
            this.createUser = createUser;
        }
    }
}

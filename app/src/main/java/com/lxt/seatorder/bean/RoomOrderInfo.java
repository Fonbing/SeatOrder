package com.lxt.seatorder.bean;

import java.util.List;

/**
 * Created by Lxt Jxfen on 2019-12-14.
 * email: 1771874056@qq.com
 */
public class RoomOrderInfo extends BaseBean {

    /**
     * resourceId : 9030
     * resource : {"id":9030,"resourceId":9030,"resourceSysName":"B","resourceDisplayName":"006-B","resourceClass":1,"resourceType":1,"parentDirId":2706,"resouceStatus":1,"resourceJson":"{\"direction\":\"1\"}","seatStatus":22,"itemId":0,"show":null,"startTime":null,"endTime":null,"attrValues":null,"labels":null,"roomId":null,"resourceRecord":null,"resourceRecords":[],"dateTimeSlices":null,"orderStatus":22,"createTime":1554166890000,"createBy":"admin","createUser":null}
     * user : {"id":null,"userId":"201501510227","portrait":null,"nickName":null,"realName":null,"mobile":null,"mail":null,"idCard":null,"age":null,"gender":null,"province":null,"city":null,"area":null,"sysNumber":null,"userStatus":null,"totalScore":null,"isReceiveAttention":null,"roles":null,"isFriend":null,"userRole":null,"reservationNum":0,"operateType":null,"cardNumber":null,"cardType":null,"departmentId":null,"departmentName":null,"userName":null,"passWord":null,"registerTime":null,"fromApp":null,"wxUser":null,"cardPassword":null,"createTime":null,"createBy":null,"createUser":null}
     * friend : false
     */

    private int resourceId;
    private ResourceBean resource;
    private UserBean user;
    private boolean friend;

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceBean getResource() {
        return resource;
    }

    public void setResource(ResourceBean resource) {
        this.resource = resource;
    }

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }

    public static class ResourceBean {
        /**
         * id : 9030
         * resourceId : 9030
         * resourceSysName : B
         * resourceDisplayName : 006-B
         * resourceClass : 1
         * resourceType : 1
         * parentDirId : 2706
         * resouceStatus : 1
         * resourceJson : {"direction":"1"}
         * seatStatus : 22
         * itemId : 0
         * show : null
         * startTime : null
         * endTime : null
         * attrValues : null
         * labels : null
         * roomId : null
         * resourceRecord : null
         * resourceRecords : []
         * dateTimeSlices : null
         * orderStatus : 22
         * createTime : 1554166890000
         * createBy : admin
         * createUser : null
         */

        private int id;
        private int resourceId;
        private String resourceSysName;
        private String resourceDisplayName;
        private int resourceClass;
        private int resourceType;
        private int parentDirId;
        private int resouceStatus;
        private String resourceJson;
        private int seatStatus;
        private int itemId;

        private int orderStatus;
        private long createTime;
        private String createBy;
        private List<?> resourceRecords;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getResourceId() {
            return resourceId;
        }

        public void setResourceId(int resourceId) {
            this.resourceId = resourceId;
        }

        public String getResourceSysName() {
            return resourceSysName;
        }

        public void setResourceSysName(String resourceSysName) {
            this.resourceSysName = resourceSysName;
        }

        public String getResourceDisplayName() {
            return resourceDisplayName;
        }

        public void setResourceDisplayName(String resourceDisplayName) {
            this.resourceDisplayName = resourceDisplayName;
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

        public int getParentDirId() {
            return parentDirId;
        }

        public void setParentDirId(int parentDirId) {
            this.parentDirId = parentDirId;
        }

        public int getResouceStatus() {
            return resouceStatus;
        }

        public void setResouceStatus(int resouceStatus) {
            this.resouceStatus = resouceStatus;
        }

        public String getResourceJson() {
            return resourceJson;
        }

        public void setResourceJson(String resourceJson) {
            this.resourceJson = resourceJson;
        }

        public int getSeatStatus() {
            return seatStatus;
        }

        public void setSeatStatus(int seatStatus) {
            this.seatStatus = seatStatus;
        }

        public int getItemId() {
            return itemId;
        }

        public void setItemId(int itemId) {
            this.itemId = itemId;
        }

        public int getOrderStatus() {
            return orderStatus;
        }

        public void setOrderStatus(int orderStatus) {
            this.orderStatus = orderStatus;
        }

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

        public List<?> getResourceRecords() {
            return resourceRecords;
        }

        public void setResourceRecords(List<?> resourceRecords) {
            this.resourceRecords = resourceRecords;
        }
    }

    public static class UserBean {
        /**
         * id : null
         * userId : 201501510227
         * portrait : null
         * nickName : null
         * realName : null
         * mobile : null
         * mail : null
         * idCard : null
         * age : null
         * gender : null
         * province : null
         * city : null
         * area : null
         * sysNumber : null
         * userStatus : null
         * totalScore : null
         * isReceiveAttention : null
         * roles : null
         * isFriend : null
         * userRole : null
         * reservationNum : 0
         * operateType : null
         * cardNumber : null
         * cardType : null
         * departmentId : null
         * departmentName : null
         * userName : null
         * passWord : null
         * registerTime : null
         * fromApp : null
         * wxUser : null
         * cardPassword : null
         * createTime : null
         * createBy : null
         * createUser : null
         */

        private String userId;

        private int reservationNum;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getReservationNum() {
            return reservationNum;
        }

        public void setReservationNum(int reservationNum) {
            this.reservationNum = reservationNum;
        }
    }
}

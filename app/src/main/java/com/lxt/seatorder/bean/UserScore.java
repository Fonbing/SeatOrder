package com.lxt.seatorder.bean;

/**
 * Created by Lxt Jxfen on 2020/4/14.
 * email: 1771874056@qq.com
 */
public class UserScore extends BaseBean{

    /**
     * id : 69312
     * scoreRecordId : 69312
     * userId : 201810620334
     * type : 0
     * score : 3
     * surplusScore : 0
     * reason : 超时未签退，系统自动签退扣分
     * orderItemId : null
     * sysNumber : null
     * realName : null
     * user : null
     * createTime : 1586700068000
     * createBy : null
     * createUser : null
     */

    private int id;
    private int scoreRecordId;
    private String userId;
    private int type;
    private int score;
    private int surplusScore;
    private String reason;
    private Object orderItemId;
    private Object sysNumber;
    private Object realName;
    private Object user;
    private long createTime;
    private Object createBy;
    private Object createUser;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScoreRecordId() {
        return scoreRecordId;
    }

    public void setScoreRecordId(int scoreRecordId) {
        this.scoreRecordId = scoreRecordId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSurplusScore() {
        return surplusScore;
    }

    public void setSurplusScore(int surplusScore) {
        this.surplusScore = surplusScore;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Object orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Object getSysNumber() {
        return sysNumber;
    }

    public void setSysNumber(Object sysNumber) {
        this.sysNumber = sysNumber;
    }

    public Object getRealName() {
        return realName;
    }

    public void setRealName(Object realName) {
        this.realName = realName;
    }

    public Object getUser() {
        return user;
    }

    public void setUser(Object user) {
        this.user = user;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public Object getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Object createBy) {
        this.createBy = createBy;
    }

    public Object getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Object createUser) {
        this.createUser = createUser;
    }
}

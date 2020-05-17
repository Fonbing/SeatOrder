package com.lxt.seatorder.bean;

/**
 * Created by Lxt Jxfen on 2019-11-27.
 * email: 1771874056@qq.com
 */
public class AddressInfo extends BaseBean{
    private int id;
    private String coverPic;
    private String startTime;
    private String endTime;
    private String dirName;
    private String dirSysName;
    private int dirType;
    private int dirLevel;
    private int parentDirId;
    private int resourceDirId;
    private int seatLastNum;
    private int seatNum;
    private int seatReserveNum;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCoverPic() {
        return coverPic;
    }

    public void setCoverPic(String coverPic) {
        this.coverPic = coverPic;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }

    public String getDirSysName() {
        return dirSysName;
    }

    public void setDirSysName(String dirSysName) {
        this.dirSysName = dirSysName;
    }

    public int getDirType() {
        return dirType;
    }

    public void setDirType(int dirType) {
        this.dirType = dirType;
    }

    public int getDirLevel() {
        return dirLevel;
    }

    public void setDirLevel(int dirLevel) {
        this.dirLevel = dirLevel;
    }

    public int getParentDirId() {
        return parentDirId;
    }

    public void setParentDirId(int parentDirId) {
        this.parentDirId = parentDirId;
    }

    public int getResourceDirId() {
        return resourceDirId;
    }

    public void setResourceDirId(int resourceDirId) {
        this.resourceDirId = resourceDirId;
    }

    public int getSeatLastNum() {
        return seatLastNum;
    }

    public void setSeatLastNum(int seatLastNum) {
        this.seatLastNum = seatLastNum;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public int getSeatReserveNum() {
        return seatReserveNum;
    }

    public void setSeatReserveNum(int seatReserveNum) {
        this.seatReserveNum = seatReserveNum;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}

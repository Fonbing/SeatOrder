package com.lxt.seatorder.bean;

import java.util.List;

/**
 * Created by Lxt Jxfen on 2019-12-06.
 * email: 1771874056@qq.com
 */
public class RoomInfo extends BaseBean {
    private String dirName;
    private String dirSysName;
    private int dirType;
    private int id;
    private int parentDirId;
    private List<Seat> resources;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentDirId() {
        return parentDirId;
    }

    public void setParentDirId(int parentDirId) {
        this.parentDirId = parentDirId;
    }

    public List<Seat> getResources() {
        return resources;
    }

    public void setResources(List<Seat> resources) {
        this.resources = resources;
    }

    public static class Seat {
        private int id;
        private int parentDirId;
        private int resouceStatus;
        private int seatStatus;
        private String resourceDisplayName;
        private int resourceId;
        private String resourceSysName;
        private int resourceType;

        public int getSeatStatus() {
            return seatStatus;
        }

        public void setSeatStatus(int seatStatus) {
            this.seatStatus = seatStatus;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getResourceDisplayName() {
            return resourceDisplayName;
        }

        public void setResourceDisplayName(String resourceDisplayName) {
            this.resourceDisplayName = resourceDisplayName;
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

        public int getResourceType() {
            return resourceType;
        }

        public void setResourceType(int resourceType) {
            this.resourceType = resourceType;
        }
    }
}

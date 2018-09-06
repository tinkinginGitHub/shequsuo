package cn.anyoufang.entity;

import java.io.Serializable;

public class SpMemberAuthKey implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer region;

    private Integer room;

    private Integer uid;

    public Integer getRegion() {
        return region;
    }

    public void setRegion(Integer region) {
        this.region = region;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
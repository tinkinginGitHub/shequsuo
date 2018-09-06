package cn.anyoufang.entity;

import java.io.Serializable;

public class SpMemberRelation implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer uid;

    private Integer region;

    private Integer room;

    private Byte usertype;

    private Integer addtime;

    private Integer stime;

    private Integer etime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

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

    public Byte getUsertype() {
        return usertype;
    }

    public void setUsertype(Byte usertype) {
        this.usertype = usertype;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getStime() {
        return stime;
    }

    public void setStime(Integer stime) {
        this.stime = stime;
    }

    public Integer getEtime() {
        return etime;
    }

    public void setEtime(Integer etime) {
        this.etime = etime;
    }
}
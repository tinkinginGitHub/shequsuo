package cn.anyoufang.entity;

import java.io.Serializable;

public class SpActionRecords implements Serializable {

    private static final long serialVersionUID = -153804172290494649L;

    private Integer id;

    private String locksn;

    private String action;

    private Integer actiontime;

    private Integer memberid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocksn() {
        return locksn;
    }

    public void setLocksn(String locksn) {
        this.locksn = locksn == null ? null : locksn.trim();
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public Integer getActiontime() {
        return actiontime;
    }

    public void setActiontime(Integer actiontime) {
        this.actiontime = actiontime;
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }
}
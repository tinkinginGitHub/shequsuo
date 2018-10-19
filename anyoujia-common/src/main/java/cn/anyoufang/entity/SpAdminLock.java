package cn.anyoufang.entity;

public class SpAdminLock {
    private Integer id;

    private Integer adminid;

    private String locksn;

    private Integer createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }

    public String getLocksn() {
        return locksn;
    }

    public void setLocksn(String locksn) {
        this.locksn = locksn == null ? null : locksn.trim();
    }

    public Integer getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Integer createtime) {
        this.createtime = createtime;
    }
}
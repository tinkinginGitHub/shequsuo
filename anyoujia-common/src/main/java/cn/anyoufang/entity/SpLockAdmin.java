package cn.anyoufang.entity;

public class SpLockAdmin {
    private Integer id;

    private Integer adminid;

    private String locksn;

    private Integer createtime;

    private Boolean setedlockpwd;

    private Integer updatetime;

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

    public Boolean getSetedlockpwd() {
        return setedlockpwd;
    }

    public void setSetedlockpwd(Boolean setedlockpwd) {
        this.setedlockpwd = setedlockpwd;
    }

    public Integer getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
    }
}
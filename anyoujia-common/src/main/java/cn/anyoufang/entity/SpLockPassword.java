package cn.anyoufang.entity;

public class SpLockPassword {
    private Integer pwdid;

    private String locksn;

    private Integer memberid;

    private Integer ptype;

    private Integer addtime;

    public Integer getPwdid() {
        return pwdid;
    }

    public void setPwdid(Integer pwdid) {
        this.pwdid = pwdid;
    }

    public String getLocksn() {
        return locksn;
    }

    public void setLocksn(String locksn) {
        this.locksn = locksn == null ? null : locksn.trim();
    }

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public Integer getPtype() {
        return ptype;
    }

    public void setPtype(Integer ptype) {
        this.ptype = ptype;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }
}
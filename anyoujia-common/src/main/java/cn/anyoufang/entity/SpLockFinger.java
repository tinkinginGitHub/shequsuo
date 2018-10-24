package cn.anyoufang.entity;

public class SpLockFinger {
    private Integer id;

    private String locksn;

    private Integer memberid;

    private String fingerid;

    private String fingerdesc;

    private Integer ptype;

    private Integer addtime;

    private Boolean expired;

    private Integer deltime;

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

    public Integer getMemberid() {
        return memberid;
    }

    public void setMemberid(Integer memberid) {
        this.memberid = memberid;
    }

    public String getFingerid() {
        return fingerid;
    }

    public void setFingerid(String fingerid) {
        this.fingerid = fingerid == null ? null : fingerid.trim();
    }

    public String getFingerdesc() {
        return fingerdesc;
    }

    public void setFingerdesc(String fingerdesc) {
        this.fingerdesc = fingerdesc == null ? null : fingerdesc.trim();
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

    public Boolean getExpired() {
        return expired;
    }

    public void setExpired(Boolean expired) {
        this.expired = expired;
    }

    public Integer getDeltime() {
        return deltime;
    }

    public void setDeltime(Integer deltime) {
        this.deltime = deltime;
    }
}
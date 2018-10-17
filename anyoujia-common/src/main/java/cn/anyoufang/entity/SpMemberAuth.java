package cn.anyoufang.entity;

public class SpMemberAuth {
    private Integer id;

    private Integer relationid;

    private Integer locksn;

    private String phone;

    private Boolean lockpwdauth;

    private Boolean fingerpwdauth;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRelationid() {
        return relationid;
    }

    public void setRelationid(Integer relationid) {
        this.relationid = relationid;
    }

    public Integer getLocksn() {
        return locksn;
    }

    public void setLocksn(Integer locksn) {
        this.locksn = locksn;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Boolean getLockpwdauth() {
        return lockpwdauth;
    }

    public void setLockpwdauth(Boolean lockpwdauth) {
        this.lockpwdauth = lockpwdauth;
    }

    public Boolean getFingerpwdauth() {
        return fingerpwdauth;
    }

    public void setFingerpwdauth(Boolean fingerpwdauth) {
        this.fingerpwdauth = fingerpwdauth;
    }
}
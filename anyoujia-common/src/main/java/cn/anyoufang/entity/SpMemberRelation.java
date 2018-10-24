package cn.anyoufang.entity;

public class SpMemberRelation {
    private Integer id;

    private Integer parentid;

    private String locksn;

    private String phone;

    private String username;

    private String usertype;

    private String userrelation;

    private Integer addtime;

    private Integer starttime;

    private Integer endtime;

    private Boolean setedlockpwd;

    private Boolean lockpwdauth;

    private Boolean fingerpwdauth;

    private Integer updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    public String getLocksn() {
        return locksn;
    }

    public void setLocksn(String locksn) {
        this.locksn = locksn == null ? null : locksn.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype == null ? null : usertype.trim();
    }

    public String getUserrelation() {
        return userrelation;
    }

    public void setUserrelation(String userrelation) {
        this.userrelation = userrelation == null ? null : userrelation.trim();
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getStarttime() {
        return starttime;
    }

    public void setStarttime(Integer starttime) {
        this.starttime = starttime;
    }

    public Integer getEndtime() {
        return endtime;
    }

    public void setEndtime(Integer endtime) {
        this.endtime = endtime;
    }

    public Boolean getSetedlockpwd() {
        return setedlockpwd;
    }

    public void setSetedlockpwd(Boolean setedlockpwd) {
        this.setedlockpwd = setedlockpwd;
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

    public Integer getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Integer updatetime) {
        this.updatetime = updatetime;
    }
}
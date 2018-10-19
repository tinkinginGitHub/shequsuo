package cn.anyoufang.entity;

public class SpMember {
    private Integer uid;

    private String phone;

    private String password;

    private String bname;

    private String name;

    private String email;

    private String avatar;

    private Boolean gender;

    private Integer wx;

    private String say;

    private String securitypwd;

    private String securityquestion;

    private String securityanswer;

    private Integer registtime;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname == null ? null : bname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public Integer getWx() {
        return wx;
    }

    public void setWx(Integer wx) {
        this.wx = wx;
    }

    public String getSay() {
        return say;
    }

    public void setSay(String say) {
        this.say = say == null ? null : say.trim();
    }

    public String getSecuritypwd() {
        return securitypwd;
    }

    public void setSecuritypwd(String securitypwd) {
        this.securitypwd = securitypwd == null ? null : securitypwd.trim();
    }

    public String getSecurityquestion() {
        return securityquestion;
    }

    public void setSecurityquestion(String securityquestion) {
        this.securityquestion = securityquestion == null ? null : securityquestion.trim();
    }

    public String getSecurityanswer() {
        return securityanswer;
    }

    public void setSecurityanswer(String securityanswer) {
        this.securityanswer = securityanswer == null ? null : securityanswer.trim();
    }

    public Integer getRegisttime() {
        return registtime;
    }

    public void setRegisttime(Integer registtime) {
        this.registtime = registtime;
    }
}
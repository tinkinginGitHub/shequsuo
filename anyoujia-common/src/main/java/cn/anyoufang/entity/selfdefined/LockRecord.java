package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;

/**
 * 门锁记录返回实体类
 * @author daiping
 */
public class LockRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nickname;
    private int opentime;
    private int pwdType;
    private int isAlarm;
    private int gender;
    private int userType;
    private String headurl;
    private String relation;
    private int status;
    private int pwdRecordId;

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getPwdRecordId() {
        return pwdRecordId;
    }

    public void setPwdRecordId(int pwdRecordId) {
        this.pwdRecordId = pwdRecordId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getOpentime() {
        return opentime;
    }

    public void setOpentime(int opentime) {
        this.opentime = opentime;
    }

    public int getPwdType() {
        return pwdType;
    }

    public void setPwdType(int pwdType) {
        this.pwdType = pwdType;
    }

    public int getIsAlarm() {
        return isAlarm;
    }

    public void setIsAlarm(int isAlarm) {
        this.isAlarm = isAlarm;
    }
}

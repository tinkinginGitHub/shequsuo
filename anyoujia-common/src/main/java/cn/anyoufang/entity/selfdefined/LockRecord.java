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

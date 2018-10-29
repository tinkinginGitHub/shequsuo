package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;

/**
 * 设置记录返回实体类
 * @author daiping
 */
public class SetRecord implements Serializable {

    private static final long serialVersionUID = 1L;


    private String nickname;
    private int time;
    private String action;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}

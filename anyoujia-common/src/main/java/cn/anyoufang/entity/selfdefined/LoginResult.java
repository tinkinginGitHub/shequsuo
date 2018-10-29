package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;

/**
 * 自定义响应登录结果
 * @author daiping
 */
public class LoginResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String phone;

    private String bname;

    private String avatar;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private String token;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

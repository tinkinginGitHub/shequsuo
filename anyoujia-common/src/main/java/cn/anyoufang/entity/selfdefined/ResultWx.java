package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;

/**
 * @author daiping
 */
public class ResultWx implements Serializable {

    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

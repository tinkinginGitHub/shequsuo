package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;

/**
 * @author daiping
 */
public class Lock extends LockInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean isAdmin;
    private boolean lockpwdAuth;
    private boolean lockfingerAuth;
    private String locksn;
    /**
     * 产品型号
     */
    private String model;
    /**
     * 产品产地
     */
    private String origin;
    /**
     * 产品颜色
     */
    private String color;
    /**
     * 生产日期
     */
    private int producttime;

    /**
     * 锁地址
     */

    private String address;

    /**
     * 二维码
     */
    private String vcode;

    public String getVcode() {
        return vcode;
    }

    public void setVcode(String vcode) {
        this.vcode = vcode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLocksn() {
        return locksn;
    }

    public void setLocksn(String locksn) {
        this.locksn = locksn;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isLockpwdAuth() {
        return lockpwdAuth;
    }

    public void setLockpwdAuth(boolean lockpwdAuth) {
        this.lockpwdAuth = lockpwdAuth;
    }

    public boolean isLockfingerAuth() {
        return lockfingerAuth;
    }

    public void setLockfingerAuth(boolean lockfingerAuth) {
        this.lockfingerAuth = lockfingerAuth;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getProducttime() {
        return producttime;
    }

    public void setProducttime(int producttime) {
        this.producttime = producttime;
    }
}

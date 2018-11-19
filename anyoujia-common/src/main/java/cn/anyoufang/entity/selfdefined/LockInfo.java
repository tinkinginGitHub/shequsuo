package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;

/**
 *
 * 单个门锁详情实体类
 * @author daiping
 */
public class LockInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private int lockCreatetime;
    private int power1;
    private int power2;
    private int online;
    private int lockStatus;

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
    private String code2;

    /**
     * 产品码
     */
    private  String prokey;


    public String getProkey() {
        return prokey;
    }

    public void setProkey(String prokey) {
        this.prokey = prokey;
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public int getLockCreatetime() {
        return lockCreatetime;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLockCreatetime(int lockCreatetime) {
        this.lockCreatetime = lockCreatetime;
    }

    public int getPower1() {
        return power1;
    }

    public void setPower1(int power1) {
        this.power1 = power1;
    }

    public int getPower2() {
        return power2;
    }

    public void setPower2(int power2) {
        this.power2 = power2;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }

    public int getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(int lockStatus) {
        this.lockStatus = lockStatus;
    }
}

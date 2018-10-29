package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;

/**
 * @author daiping
 */
public class LockCombineInfo implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private Integer producttime;
    /**
     * 锁地址
     */
    private String address;
    /**
     * 锁地址
     */
    private String cname;
    /**
     * 锁序列号
     */
    private String sn;

    /**
     * 二维码
     */
    private String code2;



    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
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

    public Integer getProducttime() {
        return producttime;
    }

    public void setProducttime(Integer producttime) {
        this.producttime = producttime;
    }
}

package cn.anyoufang.entity;

import java.io.Serializable;

/**
 * @author wantianwu
 * 搜索房源请求参数bean
 */
public class RestParam implements Serializable {
    //区域或小区
    private String areas;

    //合/整租
    private String rentalMode;

    //位置
    private String addr;

    //价格
    private String price;

    //综合筛选
    private String comprehensive;

    //纬度
    private double latitude;

    //经度
    private double longitude;

    public String getAreas() {
        return areas;
    }

    public void setAreas(String areas) {
        this.areas = areas;
    }

    public String getRentalMode() {
        return rentalMode;
    }

    public void setRentalMode(String rentalMode) {
        this.rentalMode = rentalMode;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getComprehensive() {
        return comprehensive;
    }

    public void setComprehensive(String comprehensive) {
        this.comprehensive = comprehensive;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}

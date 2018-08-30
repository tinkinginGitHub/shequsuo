package cn.anyoufang.entity;

import java.io.Serializable;

public class RoomParam implements Serializable {
    private String status;

    private String productNum;

    private String phone;

    private String viewingBegin;

    private String viewingEnd;

    private String productName;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getViewingBegin() {
        return viewingBegin;
    }

    public void setViewingBegin(String viewingBegin) {
        this.viewingBegin = viewingBegin;
    }

    public String getViewingEnd() {
        return viewingEnd;
    }

    public void setViewingEnd(String viewingEnd) {
        this.viewingEnd = viewingEnd;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

}

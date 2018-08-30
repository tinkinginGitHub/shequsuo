package cn.anyoufang.entity;


import java.io.Serializable;

public class RoomParamTerrace implements Serializable {
    private String status;

    private String productNum;

    private String user;

    private String phone;

    private String viewingBegin;

    private String viewingEnd;

    private String productName;

    private String supplier;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
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

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }
}

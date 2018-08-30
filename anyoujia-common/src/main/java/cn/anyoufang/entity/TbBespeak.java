package cn.anyoufang.entity;

import java.util.Date;

public class TbBespeak {
    private String id;

    private String housingId;

    private String renterId;

    private String accRenterId;

    private Date created;

    private Date updated;

    private Byte status;

    private String phone;

    private String basicInfo;

    private Date viewingTime;

    private String name;

    private String gender;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getHousingId() {
        return housingId;
    }

    public void setHousingId(String housingId) {
        this.housingId = housingId == null ? null : housingId.trim();
    }

    public String getRenterId() {
        return renterId;
    }

    public void setRenterId(String renterId) {
        this.renterId = renterId == null ? null : renterId.trim();
    }

    public String getAccRenterId() {
        return accRenterId;
    }

    public void setAccRenterId(String accRenterId) {
        this.accRenterId = accRenterId == null ? null : accRenterId.trim();
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getBasicInfo() {
        return basicInfo;
    }

    public void setBasicInfo(String basicInfo) {
        this.basicInfo = basicInfo == null ? null : basicInfo.trim();
    }

    public Date getViewingTime() {
        return viewingTime;
    }

    public void setViewingTime(Date viewingTime) {
        this.viewingTime = viewingTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }
}
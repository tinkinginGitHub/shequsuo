package cn.anyoufang.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

public class TbHousing implements Serializable {
    private String id;

    private String areaId;

    private String apartmentId;

    private Byte voucher;

    private String special;

    private Byte status;

    private Date created;

    private Date updated;

    private String picInfo;

    private String location;

    private String picUrl;

    private String facility;

    private String rent;

    private String bookId;

    private Integer money;

    private Date expectedTime;

    private Byte superior;

    private String houseType;

    private String hallRoom;

    private String rooms;

    private String paymentId;

    private Double acreage;

    private String subwayLine;

    private Double latitude;

    private Double longitude;

    private String storey;

    private String orientations;

    private String housekeeperid;

    private String paymentType;

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(String apartmentId) {
        this.apartmentId = apartmentId == null ? null : apartmentId.trim();
    }

    public Byte getVoucher() {
        return voucher;
    }

    public void setVoucher(Byte voucher) {
        this.voucher = voucher;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special == null ? null : special.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
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

    public String getPicInfo() {
        return picInfo;
    }

    public void setPicInfo(String picInfo) {
        this.picInfo = picInfo == null ? null : picInfo.trim();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility == null ? null : facility.trim();
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent == null ? null : rent.trim();
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId == null ? null : bookId.trim();
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public Date getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Date expectedTime) {
        this.expectedTime = expectedTime;
    }

    public Byte getSuperior() {
        return superior;
    }

    public void setSuperior(Byte superior) {
        this.superior = superior;
    }

    public String getHouseType() {
        return houseType;
    }

    public void setHouseType(String houseType) {
        this.houseType = houseType == null ? null : houseType.trim();
    }

    public String getHallRoom() {
        return hallRoom;
    }

    public void setHallRoom(String hallRoom) {
        this.hallRoom = hallRoom == null ? null : hallRoom.trim();
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms == null ? null : rooms.trim();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId == null ? null : paymentId.trim();
    }

    public Double getAcreage() {
        return acreage;
    }

    public void setAcreage(Double acreage) {
        this.acreage = acreage;
    }

    public String getSubwayLine() {
        return subwayLine;
    }

    public void setSubwayLine(String subwayLine) {
        this.subwayLine = subwayLine == null ? null : subwayLine.trim();
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getStorey() {
        return storey;
    }

    public void setStorey(String storey) {
        this.storey = storey == null ? null : storey.trim();
    }

    public String getOrientations() {
        return orientations;
    }

    public void setOrientations(String orientations) {
        this.orientations = orientations == null ? null : orientations.trim();
    }

    public String getHousekeeperid() {
        return housekeeperid;
    }

    public void setHousekeeperid(String housekeeperid) {
        this.housekeeperid = housekeeperid == null ? null : housekeeperid.trim();
    }
}
package cn.anyoufang.entity;

import java.util.Date;

public class TbAreas {
    private String id;

    private String type;

    private String year;

    private Double greenRatio;

    private Double propertyFee;

    private Double plotRatio;

    private String areaProperty;

    private Date created;

    private Date updated;

    private String name;

    private String status;

    private String pic;

    private String geoAddr;

    private String bisAddr;

    private Double latitude;

    private Double longitude;

    private String householdNum;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public Double getGreenRatio() {
        return greenRatio;
    }

    public void setGreenRatio(Double greenRatio) {
        this.greenRatio = greenRatio;
    }

    public Double getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(Double propertyFee) {
        this.propertyFee = propertyFee;
    }

    public Double getPlotRatio() {
        return plotRatio;
    }

    public void setPlotRatio(Double plotRatio) {
        this.plotRatio = plotRatio;
    }

    public String getAreaProperty() {
        return areaProperty;
    }

    public void setAreaProperty(String areaProperty) {
        this.areaProperty = areaProperty == null ? null : areaProperty.trim();
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getGeoAddr() {
        return geoAddr;
    }

    public void setGeoAddr(String geoAddr) {
        this.geoAddr = geoAddr == null ? null : geoAddr.trim();
    }

    public String getBisAddr() {
        return bisAddr;
    }

    public void setBisAddr(String bisAddr) {
        this.bisAddr = bisAddr == null ? null : bisAddr.trim();
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

    public String getHouseholdNum() {
        return householdNum;
    }

    public void setHouseholdNum(String householdNum) {
        this.householdNum = householdNum == null ? null : householdNum.trim();
    }
}
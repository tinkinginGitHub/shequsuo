package cn.anyoufang.entity;

import java.io.Serializable;
import java.util.Date;

public class TbCollect implements Serializable {
    private String collectId;

    private String housingId;

    private String renterId;

    private Date created;

    private Byte footmark;

    public TbCollect() {
        super();
    }

    public TbCollect(String collectId, String housingId, String renterId,
            Date created, Byte footmark) {
        super();
        this.collectId = collectId;
        this.housingId = housingId;
        this.renterId = renterId;
        this.created = created;
        this.footmark = footmark;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId == null ? null : collectId.trim();
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Byte getFootmark() {
        return footmark;
    }

    public void setFootmark(Byte footmark) {
        this.footmark = footmark;
    }
}
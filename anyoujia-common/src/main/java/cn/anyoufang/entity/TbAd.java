package cn.anyoufang.entity;

import java.io.Serializable;
import java.util.Date;

public class TbAd  implements Serializable {
    private String id;

    private Byte adSortOrder;

    private String adStatus;

    private String adPicHref;

    private String adPicLink;

    private String adType;

    private Date adCreated;

    private Date adUpdated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Byte getAdSortOrder() {
        return adSortOrder;
    }

    public void setAdSortOrder(Byte adSortOrder) {
        this.adSortOrder = adSortOrder;
    }

    public String getAdStatus() {
        return adStatus;
    }

    public void setAdStatus(String adStatus) {
        this.adStatus = adStatus == null ? null : adStatus.trim();
    }

    public String getAdPicHref() {
        return adPicHref;
    }

    public void setAdPicHref(String adPicHref) {
        this.adPicHref = adPicHref == null ? null : adPicHref.trim();
    }

    public String getAdPicLink() {
        return adPicLink;
    }

    public void setAdPicLink(String adPicLink) {
        this.adPicLink = adPicLink == null ? null : adPicLink.trim();
    }

    public String getAdType() {
        return adType;
    }

    public void setAdType(String adType) {
        this.adType = adType == null ? null : adType.trim();
    }

    public Date getAdCreated() {
        return adCreated;
    }

    public void setAdCreated(Date adCreated) {
        this.adCreated = adCreated;
    }

    public Date getAdUpdated() {
        return adUpdated;
    }

    public void setAdUpdated(Date adUpdated) {
        this.adUpdated = adUpdated;
    }
}
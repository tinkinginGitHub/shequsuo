package cn.anyoufang.entity;

import java.io.Serializable;

public class TbPermission  implements Serializable {
    private String id;

    private String audit;

    private String business;

    private String devloper;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getAudit() {
        return audit;
    }

    public void setAudit(String audit) {
        this.audit = audit == null ? null : audit.trim();
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business == null ? null : business.trim();
    }

    public String getDevloper() {
        return devloper;
    }

    public void setDevloper(String devloper) {
        this.devloper = devloper == null ? null : devloper.trim();
    }
}
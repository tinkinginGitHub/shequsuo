package cn.anyoufang.entity;

public class SpMemberAuth extends SpMemberAuthKey{

    private static final long serialVersionUID = 1L;
    private Integer devid;

    private String item;

    private Boolean val;

    public Integer getDevid() {
        return devid;
    }

    public void setDevid(Integer devid) {
        this.devid = devid;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item == null ? null : item.trim();
    }

    public Boolean getVal() {
        return val;
    }

    public void setVal(Boolean val) {
        this.val = val;
    }
}
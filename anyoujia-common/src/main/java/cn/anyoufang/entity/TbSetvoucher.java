package cn.anyoufang.entity;

import java.io.Serializable;

public class TbSetvoucher implements Serializable {
    private String id;

    private String introduce;

    private Integer valid;

    private Integer deductibleMoney;

    private Integer deductibleDays;

    private Integer amount;

    private String voucherName;

    private String picUrl;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce == null ? null : introduce.trim();
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Integer getDeductibleMoney() {
        return deductibleMoney;
    }

    public void setDeductibleMoney(Integer deductibleMoney) {
        this.deductibleMoney = deductibleMoney;
    }

    public Integer getDeductibleDays() {
        return deductibleDays;
    }

    public void setDeductibleDays(Integer deductibleDays) {
        this.deductibleDays = deductibleDays;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName == null ? null : voucherName.trim();
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl == null ? null : picUrl.trim();
    }
}
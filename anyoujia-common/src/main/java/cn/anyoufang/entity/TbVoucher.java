package cn.anyoufang.entity;

import java.io.Serializable;
import java.util.Date;

public class TbVoucher  implements Serializable {
    private String id;

    private Integer amount;

    private Integer deductibleMoney;

    private Integer deductibleDays;

    private Date created;

    private String payNumber;

    private String voucherstate;

    private Integer timeAvailable;

    private Date updated;

    private String pic;

    private String text;

    private String serialNum;

    private Date buyTime;

    private Date activeTime;

    private Date usedtime;

    private String userid;

    private String housingid;

    private Integer totalmoney;

    private String voucherName;

    private Date refund;

    private String type;

    private String voucherCode;

    private Integer actualAmount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber == null ? null : payNumber.trim();
    }

    public String getVoucherstate() {
        return voucherstate;
    }

    public void setVoucherstate(String voucherstate) {
        this.voucherstate = voucherstate == null ? null : voucherstate.trim();
    }

    public Integer getTimeAvailable() {
        return timeAvailable;
    }

    public void setTimeAvailable(Integer timeAvailable) {
        this.timeAvailable = timeAvailable;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum == null ? null : serialNum.trim();
    }

    public Date getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(Date buyTime) {
        this.buyTime = buyTime;
    }

    public Date getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    public Date getUsedtime() {
        return usedtime;
    }

    public void setUsedtime(Date usedtime) {
        this.usedtime = usedtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getHousingid() {
        return housingid;
    }

    public void setHousingid(String housingid) {
        this.housingid = housingid == null ? null : housingid.trim();
    }

    public Integer getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(Integer totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getVoucherName() {
        return voucherName;
    }

    public void setVoucherName(String voucherName) {
        this.voucherName = voucherName == null ? null : voucherName.trim();
    }

    public Date getRefund() {
        return refund;
    }

    public void setRefund(Date refund) {
        this.refund = refund;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode == null ? null : voucherCode.trim();
    }

    public Integer getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(Integer actualAmount) {
        this.actualAmount = actualAmount;
    }
}
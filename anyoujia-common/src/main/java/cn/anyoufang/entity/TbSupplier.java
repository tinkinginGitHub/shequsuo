package cn.anyoufang.entity;

import java.io.Serializable;

public class TbSupplier  implements Serializable{
    private String id;

    private String name;

    private Byte status;

    private String areaId;

    private String juridical;

    private String licences;

    private String housekeeperId;

    private String cardholder;

    private String creditId;

    private String supplierType;

    private String logo;

    private String phone;

    private Integer bail;

    private String bank;

    private String account;

    private String pwd;

    private String role;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId == null ? null : areaId.trim();
    }

    public String getJuridical() {
        return juridical;
    }

    public void setJuridical(String juridical) {
        this.juridical = juridical == null ? null : juridical.trim();
    }

    public String getLicences() {
        return licences;
    }

    public void setLicences(String licences) {
        this.licences = licences == null ? null : licences.trim();
    }

    public String getHousekeeperId() {
        return housekeeperId;
    }

    public void setHousekeeperId(String housekeeperId) {
        this.housekeeperId = housekeeperId == null ? null : housekeeperId.trim();
    }

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder == null ? null : cardholder.trim();
    }

    public String getCreditId() {
        return creditId;
    }

    public void setCreditId(String creditId) {
        this.creditId = creditId == null ? null : creditId.trim();
    }

    public String getSupplierType() {
        return supplierType;
    }

    public void setSupplierType(String supplierType) {
        this.supplierType = supplierType == null ? null : supplierType.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Integer getBail() {
        return bail;
    }

    public void setBail(Integer bail) {
        this.bail = bail;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank == null ? null : bank.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }
}
package cn.anyoufang.entity;

import java.io.Serializable;

public class EnterpriseInformation implements Serializable {
    private static final long serialVersionUID = 1L;

    private String cardholder;

    private String logo;

    private String juridical;

    private String phone;

    private int bail;

    public String getCardholder() {
        return cardholder;
    }

    public void setCardholder(String cardholder) {
        this.cardholder = cardholder;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getJuridical() {
        return juridical;
    }

    public void setJuridical(String juridical) {
        this.juridical = juridical;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getBail() {
        return bail;
    }

    public void setBail(int bail) {
        this.bail = bail;
    }

}

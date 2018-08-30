package cn.anyoufang.entity;

import java.io.Serializable;

public class TbPayment implements Serializable {
    private String id;

    private Byte monthlyPayment;

    private Byte quarterlyPayment;

    private Byte halfYearPayment;

    private Byte annualPayment;

    private Integer monthlyMoney;

    private Integer monthlyPledge;

    private Integer quarterlyMoney;

    private Integer quarterlyPledge;

    private Integer halfYearMoney;

    private Integer halfYearPledge;

    private Integer annualMoney;

    private Integer annualPledge;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Byte getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(Byte monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public Byte getQuarterlyPayment() {
        return quarterlyPayment;
    }

    public void setQuarterlyPayment(Byte quarterlyPayment) {
        this.quarterlyPayment = quarterlyPayment;
    }

    public Byte getHalfYearPayment() {
        return halfYearPayment;
    }

    public void setHalfYearPayment(Byte halfYearPayment) {
        this.halfYearPayment = halfYearPayment;
    }

    public Byte getAnnualPayment() {
        return annualPayment;
    }

    public void setAnnualPayment(Byte annualPayment) {
        this.annualPayment = annualPayment;
    }

    public Integer getMonthlyMoney() {
        return monthlyMoney;
    }

    public void setMonthlyMoney(Integer monthlyMoney) {
        this.monthlyMoney = monthlyMoney;
    }

    public Integer getMonthlyPledge() {
        return monthlyPledge;
    }

    public void setMonthlyPledge(Integer monthlyPledge) {
        this.monthlyPledge = monthlyPledge;
    }

    public Integer getQuarterlyMoney() {
        return quarterlyMoney;
    }

    public void setQuarterlyMoney(Integer quarterlyMoney) {
        this.quarterlyMoney = quarterlyMoney;
    }

    public Integer getQuarterlyPledge() {
        return quarterlyPledge;
    }

    public void setQuarterlyPledge(Integer quarterlyPledge) {
        this.quarterlyPledge = quarterlyPledge;
    }

    public Integer getHalfYearMoney() {
        return halfYearMoney;
    }

    public void setHalfYearMoney(Integer halfYearMoney) {
        this.halfYearMoney = halfYearMoney;
    }

    public Integer getHalfYearPledge() {
        return halfYearPledge;
    }

    public void setHalfYearPledge(Integer halfYearPledge) {
        this.halfYearPledge = halfYearPledge;
    }

    public Integer getAnnualMoney() {
        return annualMoney;
    }

    public void setAnnualMoney(Integer annualMoney) {
        this.annualMoney = annualMoney;
    }

    public Integer getAnnualPledge() {
        return annualPledge;
    }

    public void setAnnualPledge(Integer annualPledge) {
        this.annualPledge = annualPledge;
    }
}
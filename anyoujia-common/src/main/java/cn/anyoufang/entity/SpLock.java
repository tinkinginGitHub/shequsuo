package cn.anyoufang.entity;

public class SpLock {
    private String sn;

    private String code1;

    private String code2;

    private Boolean active;

    private String ver;

    private Integer addtime;

    private Integer activetime;

    private String card;

    private String lbs;

    private Integer ping;

    private Integer upcyc;

    private Integer overtime;

    private Integer tcp;

    private Byte retry;

    private String ip1;

    private String ip2;

    private Integer port1;

    private Integer port2;

    private String apn;

    private String dialuser;

    private String dialpass;

    private Integer adminid;

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn == null ? null : sn.trim();
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1 == null ? null : code1.trim();
    }

    public String getCode2() {
        return code2;
    }

    public void setCode2(String code2) {
        this.code2 = code2 == null ? null : code2.trim();
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver == null ? null : ver.trim();
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Integer getActivetime() {
        return activetime;
    }

    public void setActivetime(Integer activetime) {
        this.activetime = activetime;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card == null ? null : card.trim();
    }

    public String getLbs() {
        return lbs;
    }

    public void setLbs(String lbs) {
        this.lbs = lbs == null ? null : lbs.trim();
    }

    public Integer getPing() {
        return ping;
    }

    public void setPing(Integer ping) {
        this.ping = ping;
    }

    public Integer getUpcyc() {
        return upcyc;
    }

    public void setUpcyc(Integer upcyc) {
        this.upcyc = upcyc;
    }

    public Integer getOvertime() {
        return overtime;
    }

    public void setOvertime(Integer overtime) {
        this.overtime = overtime;
    }

    public Integer getTcp() {
        return tcp;
    }

    public void setTcp(Integer tcp) {
        this.tcp = tcp;
    }

    public Byte getRetry() {
        return retry;
    }

    public void setRetry(Byte retry) {
        this.retry = retry;
    }

    public String getIp1() {
        return ip1;
    }

    public void setIp1(String ip1) {
        this.ip1 = ip1 == null ? null : ip1.trim();
    }

    public String getIp2() {
        return ip2;
    }

    public void setIp2(String ip2) {
        this.ip2 = ip2 == null ? null : ip2.trim();
    }

    public Integer getPort1() {
        return port1;
    }

    public void setPort1(Integer port1) {
        this.port1 = port1;
    }

    public Integer getPort2() {
        return port2;
    }

    public void setPort2(Integer port2) {
        this.port2 = port2;
    }

    public String getApn() {
        return apn;
    }

    public void setApn(String apn) {
        this.apn = apn == null ? null : apn.trim();
    }

    public String getDialuser() {
        return dialuser;
    }

    public void setDialuser(String dialuser) {
        this.dialuser = dialuser == null ? null : dialuser.trim();
    }

    public String getDialpass() {
        return dialpass;
    }

    public void setDialpass(String dialpass) {
        this.dialpass = dialpass == null ? null : dialpass.trim();
    }

    public Integer getAdminid() {
        return adminid;
    }

    public void setAdminid(Integer adminid) {
        this.adminid = adminid;
    }
}
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

    private Integer community;

    private Integer pDate;

    private String color;

    private String model;

    private Integer sechar1;

    private Integer sechar2;

    private String nos;

    private String proKey;

    private String checker;

    private String origin;

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

    public Integer getCommunity() {
        return community;
    }

    public void setCommunity(Integer community) {
        this.community = community;
    }

    public Integer getpDate() {
        return pDate;
    }

    public void setpDate(Integer pDate) {
        this.pDate = pDate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color == null ? null : color.trim();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model == null ? null : model.trim();
    }

    public Integer getSechar1() {
        return sechar1;
    }

    public void setSechar1(Integer sechar1) {
        this.sechar1 = sechar1;
    }

    public Integer getSechar2() {
        return sechar2;
    }

    public void setSechar2(Integer sechar2) {
        this.sechar2 = sechar2;
    }

    public String getNos() {
        return nos;
    }

    public void setNos(String nos) {
        this.nos = nos == null ? null : nos.trim();
    }

    public String getProKey() {
        return proKey;
    }

    public void setProKey(String proKey) {
        this.proKey = proKey == null ? null : proKey.trim();
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker == null ? null : checker.trim();
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin == null ? null : origin.trim();
    }
}
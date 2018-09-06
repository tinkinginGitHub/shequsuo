package cn.anyoufang.entity;

import java.io.Serializable;

public class SpMemberWx implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer wx;

    private String nickname;

    private String img;

    private String openid;

    private Integer addtime;

    private Boolean subscribe;

    private Integer uid;

    public Integer getWx() {
        return wx;
    }

    public void setWx(Integer wx) {
        this.wx = wx;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Boolean getSubscribe() {
        return subscribe;
    }

    public void setSubscribe(Boolean subscribe) {
        this.subscribe = subscribe;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }
}
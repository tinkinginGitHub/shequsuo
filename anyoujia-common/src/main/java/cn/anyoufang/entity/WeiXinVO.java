package cn.anyoufang.entity;

import java.io.Serializable;

public class WeiXinVO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 微信昵称
     */
    private String nickname;
    /**
     * 城市
     */
    private String city;
    /**
     * 省份
     */
    private String province;
    /**
     * 国家
     */
    private String country;
    /**
     * 头像
     */
    private String headimgurl;

    private String openid;
    /**
     * 性别  1 男  2 女  0 未知
     */
    private Integer sex;

    private int wxid;

    public int getWxid() {
        return wxid;
    }

    public void setWxid(int wxid) {
        this.wxid = wxid;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public WeiXinVO(String nickname, String city, String province,
                    String country, String headimgurl, String openid, Integer sex) {
        super();
        this.nickname = nickname;
        this.city = city;
        this.province = province;
        this.country = country;
        this.headimgurl = headimgurl;
        this.openid = openid;
        this.sex = sex;
    }

    public WeiXinVO() {
        super();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    @Override
    public String toString() {
        return "WeiXinVO{" +
                "nickname='" + nickname + '\'' +
                ", city='" + city + '\'' +
                ", province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", headimgurl='" + headimgurl + '\'' +
                ", openid='" + openid + '\'' +
                ", sex=" + sex +
                ", wxid=" + wxid +
                '}';
    }
}

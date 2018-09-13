package cn.anyoufang.enums;

/**
 * @author daiping
 */
public enum WxConstant {

    /**
     * jsapi
     */
    JS_API_TICKET("jsapiTicket"),

    /**
     * 缓存access token的key
     */
    ACCESS_TOKEN("accessToken1"),

    /**
     * 安优家智慧社区公众号
     */
    APP_ID("wxaa510935818c342a"),


    /**
     * 安优家智慧社区公众号
     */
    APP_SECRET("6b58ad8650e4f2b0e91bd9f039f0c968");

    private String value;

    private WxConstant(String value) {
        this.value =value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "WxConstant{" +
                "value='" + value + '\'' +
                '}';
    }
}

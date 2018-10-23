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
     * 安优家智慧社区小程序
     */
    APP_ID("wxd6c32604de641660"),


    /**
     * 安优家智慧社区小程序
     */
    APP_SECRET("6122f996b1e99597cae0c7bb03fc43b2");

    private String value;

     WxConstant(String value) {
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

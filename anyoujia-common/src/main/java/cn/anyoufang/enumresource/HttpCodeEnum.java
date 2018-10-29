package cn.anyoufang.enumresource;

/**
 * 常用http响应码封装
 * @author daiping
 */
public enum HttpCodeEnum {

    TWO_HUNDRED(200,"成功"),
    TWO_HUNDRED1(201,"暂无数据"),
    FOUR_HUNDRED(400,"请求失败"),
    FOUR_HUNDRED1(401,"登录超时"),
    FIVE_HUNDRED(500,"系统错误"),
    FOUR_HUNDRED4(404,"资源未找到");

    private int code;
    private String value;

     HttpCodeEnum(int code,String value) {
        this.code = code;
        this.value = value;
    }
    public int getCode() {
         return code;
    }
    public String getValue() {
         return value;
    }

}

package cn.anyoufang.enumresource;

import cn.anyoufang.utils.EnumMessage;

/**
 * @Author:
 * @Email:
 * @Description: 默认密码
 * @Date: 2017/9/2 21:27
 */
public enum DefaultEnum implements EnumMessage {
    PARENT_ACCOUNT("chenyi","默认上级账号"),
    PASSWORD("8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92","默认会员密码");
    private final String code;
    private final String value;
    private DefaultEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    @Override
    public String getCode() { return code;}
    @Override
    public String getValue() { return value; }
}
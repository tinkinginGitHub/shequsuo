package cn.anyoufang.enumresource;

import cn.anyoufang.utils.EnumMessage;

/**
 * 指纹和密码权限枚举
 * @author daiping
 */
public enum PtypeAuthEnum implements EnumMessage {

    FINGER("finger","指纹权限"),
    PWD("pwd","密码权限");

    private String code;
    private String value;

    PtypeAuthEnum(String code,String value) {
        this.code = code;
        this.value = value;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getValue() {
        return value;
    }
}

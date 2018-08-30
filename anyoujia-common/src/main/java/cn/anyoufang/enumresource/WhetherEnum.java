package cn.anyoufang.enumresource;

import cn.anyoufang.utils.EnumMessage;

/**
 *
 * 2017/7/20.
 */
public enum WhetherEnum implements EnumMessage {
    YES("1","是"),
    NO("0","否");
    private final String code;
    private final String value;
    private WhetherEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    @Override
    public String getCode() { return code;}
    @Override
    public String getValue() { return value; }
}

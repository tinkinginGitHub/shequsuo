package cn.anyoufang.enumresource;

import cn.anyoufang.utils.EnumMessage;

/**
 * 锁激活状态
 */
public enum StateEnum implements EnumMessage {

    ACTIVED("1","锁已经激活"),
    NONACTIVE("0","锁未激活");

    private final String code;
    private final String value;

    StateEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
    @Override
    public String getCode() { return code;}
    @Override
    public String getValue() { return value; }
}

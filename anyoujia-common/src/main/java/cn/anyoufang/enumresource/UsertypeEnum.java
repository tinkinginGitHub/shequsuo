package cn.anyoufang.enumresource;

import cn.anyoufang.utils.EnumMessage;

/**
 *
 * 用户类型
 * @author daiping
 */
public enum UsertypeEnum implements EnumMessage {

    ONE("1","家人"),
    TWO("2","老人儿童"),
    THREE("3","租户");
    //1,2,3
    private final String code;
    //用户类型1家人，2老人儿童，3.租户
    private final String value;

     UsertypeEnum(String code, String value) {
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

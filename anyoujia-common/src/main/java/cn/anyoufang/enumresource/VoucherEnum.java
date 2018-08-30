package cn.anyoufang.enumresource;

import cn.anyoufang.utils.EnumMessage;

public enum VoucherEnum implements EnumMessage {
    ZERO("0","已购买"),
    ONE("1","已激活"),
    TWO("2","已使用"),
    THREE("3","已退款"),
    FOUR("4","已过期");
    private final String code;
    private final String value;

    private VoucherEnum(String code, String value) {
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

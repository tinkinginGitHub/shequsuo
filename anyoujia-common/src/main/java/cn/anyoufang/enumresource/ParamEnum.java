package cn.anyoufang.enumresource;

import cn.anyoufang.utils.EnumMessage;

/**
 * 数字参数枚举
 * @author daiping
 */
public enum ParamEnum implements EnumMessage {

    MINUS_ONE("-1","非必须请求参数默认值"),
    //ptype,密码类型,1: 永久 2：一次 3：限时
    ONE("1","永久"),
    TWO("2","一次"),
    THREE("3","限时");
    private String value;
    private String code;
    ParamEnum(String code,String value){
        this.code = code;
        this.value = value;
    }
    @Override
    public String getCode(){
        return code;
    }
    @Override
    public String getValue() {
        return value;
    }
}

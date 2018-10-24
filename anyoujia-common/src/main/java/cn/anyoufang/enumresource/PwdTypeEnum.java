package cn.anyoufang.enumresource;

/**
 * @author daiping
 */
public enum PwdTypeEnum {


    ONE(1,"永久"),
    TWO(2,"一次"),
    THREE(3,"限时");

    private int code;
    private String value;

    PwdTypeEnum(int code,String value) {
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

package cn.anyoufang.enumresource;

/**
 * 指纹和密码权限枚举
 * @author daiping
 */
public enum PtypeAuthEnum {

    FINGER(0,"指纹权限"),
    PWD(1,"密码权限");

    private int code;
    private String value;

    PtypeAuthEnum(int code,String value) {
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

package cn.anyoufang.entity;

import java.io.Serializable;
import java.util.Map;

/**
 * @author daiping
 */
public class InitParam implements Serializable {

    private String mod;
    private String fun;
    private String sign;

    private Map<String,String> data;

    public String getMod() {
        return mod;
    }

    public void setMod(String mod) {
        this.mod = mod;
    }

    public String getFun() {
        return fun;
    }

    public void setFun(String fun) {
        this.fun = fun;
    }

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}

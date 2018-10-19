package cn.anyoufang.entity.selfdefined;

import java.io.Serializable;
import java.util.Map;

/**
 * 数据转换类
 * @author daiping
 */
public class Data implements Serializable {

    private Map<String,String>  data;

    public Map<String, String> getData() {
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }
}

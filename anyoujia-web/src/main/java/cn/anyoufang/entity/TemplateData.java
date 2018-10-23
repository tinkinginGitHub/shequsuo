package cn.anyoufang.entity;

import java.io.Serializable;

/**
 * 小程序模板消息详细参数封装
 * @author daiping
 */
public class TemplateData implements Serializable {


        private String key;
        private String value;
        private String color;

        public TemplateData(String value, String color) {
            this.value = value;
            this.color = color;
        }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

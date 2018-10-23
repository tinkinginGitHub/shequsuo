package cn.anyoufang.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 小程序模板消息请求参数封装类WxMssVo
 * @author daiping
 */
public class WxMssVo implements Serializable {

        private String touser;
        private String template_id;
        private String page;
        private String form_id;
        private String access_token;
        private String request_url;
        private List<TemplateData> params = new ArrayList<>();


    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getForm_id() {
        return form_id;
    }

    public void setForm_id(String form_id) {
        this.form_id = form_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRequest_url() {
        return request_url;
    }

    public void setRequest_url(String request_url) {
        this.request_url = request_url;
    }

    public List<TemplateData> getParams() {
        return params;
    }

    public void setParams(List<TemplateData> params) {
        this.params = params;
    }
}

package cn.anyoufang.api;

import cn.anyoufang.enums.WxConstant;
import com.alibaba.fastjson.JSONArray;
import com.github.sd4324530.fastweixin.api.TemplateMsgAPI;
import com.github.sd4324530.fastweixin.api.config.ApiConfig;
import com.github.sd4324530.fastweixin.api.entity.Industry;
import com.github.sd4324530.fastweixin.api.entity.TemplateMsg;
import com.github.sd4324530.fastweixin.api.entity.TemplateParam;
import com.github.sd4324530.fastweixin.api.enums.ResultType;
import com.github.sd4324530.fastweixin.api.response.AddTemplateResponse;
import com.github.sd4324530.fastweixin.api.response.BaseResponse;
import com.github.sd4324530.fastweixin.api.response.PrivateTemplate;
import com.github.sd4324530.fastweixin.api.response.SendTemplateResponse;
import com.github.sd4324530.fastweixin.util.BeanUtil;
import com.github.sd4324530.fastweixin.util.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author daiping
 */
public class LockTemplateMsgApi extends TemplateMsgAPI {

    private static final Logger LOG = LoggerFactory.getLogger(LockTemplateMsgApi.class);
    public LockTemplateMsgApi(ApiConfig config) {
        super(config);
    }

    /**
     * 设置行业
     *
     * @param industry 行业参数
     * @return 操作结果
     */
    @Override
    public ResultType setIndustry(Industry industry) {
        LOG.debug("设置行业......");
        BeanUtil.requireNonNull(industry, "行业对象为空");
        String url = BASE_API_URL + "cgi-bin/template/api_set_industry?access_token=#";
        BaseResponse response = executePost(url, industry.toJsonString());
        return ResultType.get(response.getErrcode());
    }

    /**
     * 添加模版
     *
     * @param shortTemplateId 模版短id
     * @return 操作结果
     */
    @Override
    public AddTemplateResponse addTemplate(String shortTemplateId) {
        LOG.debug("添加模版......");
        BeanUtil.requireNonNull(shortTemplateId, "短模版id必填");
        String url = BASE_API_URL + "cgi-bin/template/api_add_template?access_token=#";
        Map<String, String> params = new HashMap<String, String>();
        params.put("template_id_short", shortTemplateId);
        BaseResponse r = executePost(url, JSONUtil.toJson(params));
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        AddTemplateResponse result = JSONUtil.toBean(resultJson, AddTemplateResponse.class);
        return result;
    }

    /**
     * 发送模版消息
     *
     * @param msg 消息
     * @return 发送结果
     */
    @Override
    public SendTemplateResponse send(TemplateMsg msg) {
        LOG.debug("发送模版消息......");
        BeanUtil.requireNonNull(msg.getTouser(), "openid is null");
        BeanUtil.requireNonNull(msg.getTemplateId(), "template_id is null");
        BeanUtil.requireNonNull(msg.getData(), "data is null");
        String url = BASE_API_URL + "cgi-bin/message/template/send?access_token=#";
        BaseResponse r = executePost(url, msg.toJsonString());
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        SendTemplateResponse result = JSONUtil.toBean(resultJson, SendTemplateResponse.class);
        return result;
    }

    /**
     * 获取已添加至帐号下所有模板列表
     *
     * @return 所有模板
     */
    @Override
    public PrivateTemplate[] getAllPrivateTemplate() {
        LOG.debug("获取已添加至帐号下所有模板列表......");
        String url = BASE_API_URL + "cgi-bin/template/get_all_private_template?access_token=#";
        BaseResponse r = executeGet(url);
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        PrivateTemplate[] templates = JSONArray.toJavaObject((JSONArray) JSONUtil.getJSONFromString(resultJson).get("template_list"), PrivateTemplate[].class);
        return templates;
    }

    /**
     * 删除模板
     *
     * @param templateId 模板ID
     * @return 删除结果
     */
    @Override
    public BaseResponse delTemplate(String templateId) {
        LOG.debug("删除模板......");
        BeanUtil.requireNonNull(templateId, "templateId is null");
        String url = BASE_API_URL + "cgi-bin/template/del_private_template?access_token=#";
        Map<String, String> map = new HashMap<String, String>();
        map.put("template_id", templateId);
        BaseResponse r = executePost(url, JSONUtil.toJson(map));
        String resultJson = isSuccess(r.getErrcode()) ? r.getErrmsg() : r.toJsonString();
        return JSONUtil.toBean(resultJson, BaseResponse.class);
    }
   //模板消息提醒和通知，分别是通知在手机锁屏状态也能马上看到，提醒只有打开微信才能看到
    public static void main(String[] args) {
        LockTemplateMsgApi templateMsgApi = new LockTemplateMsgApi(new ApiConfig(WxConstant.APP_ID.getValue(),WxConstant.APP_SECRET.getValue(),true));
//        AddTemplateResponse addTemplateResponse =  templateMsgApi.addTemplate("OPENTM200689236");
//        System.out.println(addTemplateResponse.getTemplateId());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        TemplateMsg msg = new TemplateMsg();
        msg.setTouser("oFVmg0QZHV1TTLcFNGf5T8c3Ozxc");
        msg.setTemplateId("5FHjhYpjctIuvZvpXtrK950uylk1MIUNA4J8S97SQUg");
        Map<String,TemplateParam> map = new HashMap<>();
        map.put("first",new TemplateParam("安优家智能007号","#173177"));
        map.put("keyword1",new TemplateParam("开门","#173177"));
        map.put("keyword2",new TemplateParam(time,"#173177"));
        map.put("remark",new TemplateParam("备注: 如非本人操作请及时处理","#173177"));
        msg.setData(map);
        templateMsgApi.send(msg);

    }
}

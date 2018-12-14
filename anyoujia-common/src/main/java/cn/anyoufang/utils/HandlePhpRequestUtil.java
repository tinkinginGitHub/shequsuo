package cn.anyoufang.utils;

import cn.anyoufang.entity.selfdefined.InitParam;
import net.sf.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author daiping
 */
public class HandlePhpRequestUtil {

    private static final String salt = "575gh5rr556Dfhr67Ohrt8";

    /**
     * 工具方法负责发送请求
     */
    public static String doPhpRequest(InitParam initParam,String url) throws Exception {
        String ss = JsonUtils.objectToJson(initParam);
        return SimulateGetAndPostUtil.sendPost(url, genarateParamForPhpRequest(ss));
    }

    /**
     * 工具方法负责生成签名
     */
    public static String genarateSign(String mod, String fun, String jsonData) {
        StringBuilder sb = new StringBuilder();
        String result = sb.append(mod).append(fun).append(jsonData).append(salt)
                .toString().replaceAll("\t|\n|\r", "");
        return Md5Utils.md5(result, "utf-8");
    }

    /**
     * 工具方法负责生成请求参数
     */
    public static String genarateParamForPhpRequest(String s) throws Exception {
        return "sp=" + AesCBC.getInstance().encrypt(s).
                replaceAll("\r|\n", "").trim().replaceAll("\\+", "%2B");
    }

    /**
     * 解析PHP鉴权中心响应结果
     */
    public static Map<String, Object> parseResponse(String response) {
        Map<String, Object> map = JsonUtils.jsonToMap(response);
        Integer status = (Integer) map.get("status");
        Object object = map.get("data");
        JSONObject json;
        Map<String, Object> data = new HashMap<>();
        if (object != null) {
            if (object instanceof String) {
                String msg = (String) object;
                data.put("msg", msg);
            }else if(object instanceof Integer){
                Integer msg = (Integer) object;
                data.put("msg",msg);
            }else if(object instanceof  JSONObject) {
                json = (JSONObject)object;
                data.putAll(JsonUtils.jsonToMap(json));
            }else {
                data.put("msg",object);
            }
        }
        data.put("status", status);
        return data;
    }
}

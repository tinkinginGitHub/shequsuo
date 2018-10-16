package cn.anyoufang.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 淘淘商城自定义响应结构
 */
public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    public static String objectToJson(Object data) {
    	try {
			String string = MAPPER.writeValueAsString(data);
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
            T t = MAPPER.readValue(jsonData, beanType);
            return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }

    /**
     * 将json数据转换成map键值对
     * @param jsonStr
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> jsonToMap(String jsonStr) {
        Map<String, Object> map = new HashMap();
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        //定义迭代器
        Iterator<String> keys = jsonObject.keys();
        String key = null;
        Object value = null;
        while (keys.hasNext()) {
            key = keys.next();
            value = jsonObject.get(key);
            map.put(key, value);
        }
        return map;
    }

    public static Map<String, Object> jsonToMap(JSONObject jsonObject) {

        Map<String, Object> map = new HashMap();
        //定义迭代器
        Iterator<String> keys = jsonObject.keys();
        String key = null;
        Object value = null;
        while (keys.hasNext()) {
            key = keys.next();
            value = jsonObject.get(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * 把JSON字符串格式转化为查询Param对象
     * @return 
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonStrToParam(String objectStr, Class<T> beanType) {
        JSONObject jsonObject = JSONObject.fromObject(objectStr);
        T t = (T) JSONObject.toBean(jsonObject, beanType);
        return t;
    }

    public static boolean isJSONValid(String jsonInString ) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(jsonInString);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Map<String,Object> parseSession(String session) throws Exception{
        String result= AesCBC.getInstance().decrypt(session);
        return JsonUtils.jsonToMap(result);
    }

    public static void main(String[] args) throws Exception {
        parseSession("3242342");
    }
}

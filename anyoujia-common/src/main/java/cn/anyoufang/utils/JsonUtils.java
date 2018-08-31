package cn.anyoufang.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        Map<String, Object> map = new HashMap<String, Object>();
        JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        Iterator<String> keys = jsonObject.keys();//定义迭代器
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
}
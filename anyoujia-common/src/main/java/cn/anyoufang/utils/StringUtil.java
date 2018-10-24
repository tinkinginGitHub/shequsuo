package cn.anyoufang.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class StringUtil {

	
	/**
	 * @author chenyi
	 * @Description 首字母转小写
	 * @param
	 * @date 2017-2-16 下午3:40:32
	 */
	public static String toLowerCaseFirstChar(String s){
		if(s==null || "".equals(s)) {
            return null;
        }
		return s.replaceFirst(s.substring(0, 1),s.substring(0, 1).toLowerCase()) ;
	}
	
	
	/**
	 * @author chenyi
	 * @Description 首字母转大写
	 * @param
	 * @date 2017-2-16 下午3:40:42
	 */
    public static String toUpCaseFirstChar(String s) {
    	if(s==null || "".equals(s)) {
            return null;
        }
		return s.replaceFirst(s.substring(0, 1),s.substring(0, 1).toUpperCase()) ;
    }
    
    public static String fromDateH(Date date) {
        DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format1.format(date);
    }
	
    /**
	 * @author chenyi
	 * @Description 验证字符串是否为空
	 * @param
	 * @date 2017-2-16 下午3:40:52
	 */
    public static boolean isEmpty(String s){
    	if(s!=null && !"".equals(s.trim())){
    		return false;
    	}
    	return true;
    }

    /**
     * 验证字符串不为空
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s){
        if(s!=null && !"".equals(s.trim())){
            return true;
        }
        return false;
    }
    
    public static boolean isNullOrEmpty(Object obj) {  
        if (obj == null) {
            return true;
        }
  
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length() == 0;
        }
  
        if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        }
  
        if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        }
  
        if (obj instanceof Object[]) {  
            Object[] object = (Object[]) obj;  
            if (object.length == 0) {  
                return true;  
            }  
            boolean empty = true;  
            for (int i = 0; i < object.length; i++) {  
                if (!isNullOrEmpty(object[i])) {  
                    empty = false;  
                    break;  
                }  
            }  
            return empty;  
        }  
        return false;  
    }

    public static String combineToken(String phone,String id) {
        String firstFour = phone.substring(0,4);
        String last = phone.substring(4,phone.length()-1);
        String last1 = phone.substring(phone.length()-2);
        StringBuilder  sb = new StringBuilder(id);
        String token = sb.append("=").append(firstFour).append("=").append(last).append(switchString(last1)).toString();
        return token;

    }
    private static String switchString(String s) {
        String en;
        switch (s){
            case "0":
                en= "A";
                break;
            case "1":
                en = "B";
                break;
            case "2":
                en= "C";
                break;
            case "3":
                en= "D";
                break;
            case "4":
                en= "E";
                break;
            case "5":
                en= "F";
                break;
            case "6":
                en= "G";
                break;
            case "7":
                en= "H";
                break;
            case "8":
                en= "I";
                break;
            default:
                en= "J";
                break;

        }
        return en;
    }

    public static String decrateTheLast(String s) {
        String en;
        switch (s){
            case "A":
                en= "0";
                break;
            case "B":
                en = "1";
                break;
            case "C":
                en= "2";
                break;
            case "D":
                en= "3";
                break;
            case "E":
                en= "4";
                break;
            case "F":
                en= "5";
                break;
            case "G":
                en= "6";
                break;
            case "H":
                en= "7";
                break;
            case "I":
                en= "8";
                break;
            default:
                en= "9";
                break;

        }
        return en;
    }

    public static void main(String[] args) {
//        String token = combineToken("18580558719","fsafasdfasdfas");
//        System.out.println(token);
//        System.out.println(decrateToken(token));
       // System.out.println(stringParamisEmpty(null,"1","1","12"));

    }

    /**
     * 定义一个参数个数不确定的方法
     *  固定参数，输入的参数只能是一个字符串
     * @param things  输入参数类型为 字符串，但是个数不确定，可以是一个或多个
     * @return
     */
    public static boolean stringParamisEmpty(String ...things){
        //对输入的不确定个数参数进行非空判断
        for (String t : things) {
             if(StringUtil.isEmpty(t)){
                 return true;
             }
        }
        return false;
    }


    public static String[] decrateToken(String token) {

        String[] datas = token.split("=");
        String s = decrateTheLast(datas[datas.length-1]);
        StringBuilder sb = new StringBuilder();
        String ss = datas[2];
        String phone = sb.append(datas[1]).append(ss.substring(0,ss.length()-1)).append(s).toString();
        String[] data = {datas[0],phone};
        return data;
    }
    
    /**
 	 * @author chenyi
 	 * @Description 不足位数，左侧补0
 	 * @param
 	 * @date 2017-3-9 上午8:44:07
 	 */
    public static String flushLeft(int length, String content){  
    	char c = '0';
        String str = "";     
        String cs = "";     
        if (content.length() > length){     
             str = content;     
        }else{    
             for (int i = 0; i < length - content.length(); i++){     
                 cs = cs + c;     
             }  
           }  
        str = cs+content;      
        return str;      
    }    
    
    /**
	 * @author chenyi
	 * @Description  左侧补足十位
	 * @param
	 * @date 2017年7月10日 上午11:20:44 
	 */    
    public static String flushTenLeft(String content){
    	return flushLeft(10, content);
    }
    
    /**
	 * @author chenyi
	 * @Description  价格补位 首位1+0000
	 * @param
	 * @date 2017年7月11日 上午11:13:50 
	 */
    public static String flushPrice(String content){
    	return "1"+flushLeft(10, content);
    }
    
    
    /**
	 * @author chenyi
	 * @Description list<string> 转成串，用逗号隔开
	 * @param
	 * @date 2017-3-10 上午10:39:34
	 */
    public static String listToStr(List<String> sList){  
    	String str = "";
    	if(sList!=null && sList.size()>0){
    		for(int i=0;i<sList.size();i++){
    			str+=sList.get(i)+",";
    		}
    		if(str.endsWith(",")){
    			str = str.substring(0,str.length()-1);
    		}
    	}
        return str;      
    }   
    
    /**
	 * @author chenyi
	 * @Description 时间格式去掉最后的.0
	 * @param
	 * @date 2017-5-2 下午4:02:07
	 */
    public static String getStandDate(String date){
    	if(date!=null && date.indexOf(".0")>-1) {
            date = date.replace(".0", "");
        }
    	return date;
    }
    
	public static boolean isBlank(CharSequence cs){
		int len = 0;
		if(cs == null || (len = cs.length()) == 0){
			return true;
		}
		for (int i = 0; i < len; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
	}

	public static String[] sortStrings(String[] params) {
        Arrays.sort(params);
        return params;
    }


}

package cn.anyoufang.xss;

import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.RRException;
import cn.anyoufang.utils.SimulateGetAndPostUtil;
import org.apache.commons.lang.StringUtils;

/**
 * SQL过滤
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2017-04-01 16:16
 */
public class SQLFilter {

    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInject(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                throw new RRException("包含非法字符");
            }
        }

        return str;
    }
    /**
     * SQL注入过滤
     * @param str  待验证的字符串
     */
    public static String sqlInjectFilter(String str){
        if(StringUtils.isBlank(str)){
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for(String keyword : keywords){
            if(str.indexOf(keyword) != -1){
                str = StringUtils.replace(str,keyword,"");
            }
        }

        return str;
    }

    public static void main(String[] args) {
        int pwdtype = 3;
        int usertype = 1;
        String locksn = "555444";
        String lockSalt = "ctlockv.0.1";
        long timestamp = System.currentTimeMillis()/1000;
        StringBuilder sb = new StringBuilder();
        String param=sb.append(locksn).append(pwdtype).append(timestamp).append(usertype).append(lockSalt).toString();
        String sign = Md5Utils.md5(param,"UTF-8");
        String baseurl = "method=get.lock.userlist&locksn="+locksn+"&temptime="+timestamp+"&sign="+sign + "&usertype="+usertype+"&pwdtype="+pwdtype;
        String res =  SimulateGetAndPostUtil.sendPost("http://114.215.71.205:81/sp/index.html",baseurl);
       // JSONArray jsonArray = JSONArray.fromObject(res);
        System.out.println(res);


    }

}

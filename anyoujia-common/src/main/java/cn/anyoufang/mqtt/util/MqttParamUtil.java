package cn.anyoufang.mqtt.util;

import cn.anyoufang.utils.Md5Utils;

/**
 * mqtt请求参数封装
 * @author daiping
 * @Date 2018/10/22
 */
public class MqttParamUtil {

    /**
     * mqtt 服务端地址
     */
    public static final String HOST = "tcp://m2m.anyoujia.com:1883";

    /**
     * 获取MQTT主题
     * @param id
     * @return
     */
    public static String getTopic(String id) {

       return  "/ctlock/app/"+id+"/get";
    }

    /**
     * clientId中
     * js为web，wx为小程序，ad为Android，is为iOS,gw为锁网关
     * @param from
     * @return
     */
    public static String getClientId(String from) {

        return "mqtt"+from+"_"+cn.anyoufang.utils.UUID.getUUID().substring(2,8);
    }

    /**
     * username必须以ctlock_ 为固定前缀。
     * app端用app中缀，锁网关为gw中缀。app端用用户id为后
       缀，锁网关以厂商id为后缀。
     * @param id
     * @param from
     * @return
     */
    public static String getUsername(String id,String from) {
        return "ctlock_"+from+"_"+id;
    }

    /**
     * password的md5() 取32位小写
     * @param clientid
     * @param userName
     * @return
     */
    public static String getPassword(String clientid,String userName) {

        return Md5Utils.md5(clientid+"|"+userName+"|"+"ct@2018#mqtt$key","utf-8");
    }


}

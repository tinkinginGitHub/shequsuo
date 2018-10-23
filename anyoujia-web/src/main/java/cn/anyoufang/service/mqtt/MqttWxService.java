package cn.anyoufang.service.mqtt;

/**
 * @author daiping
 */
public interface MqttWxService {

    String subMessage(String userid);
    String pubMessage();
}

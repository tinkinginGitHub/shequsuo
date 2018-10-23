package cn.anyoufang.service.impl.mqttImpl;

import cn.anyoufang.mqtt.util.MqttParamUtil;
import cn.anyoufang.service.mqtt.MqttWxService;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author daiping
 */
@Service
public class MqttWxServiceImpl implements MqttWxService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MqttWxServiceImpl.class);


    @Override
    public String subMessage(String userid) {
       String clientId =  MqttParamUtil.getClientId("wx");
       String username =  MqttParamUtil.getUsername(userid,"app");
       String password = MqttParamUtil.getPassword(clientId,username);
       String topic = MqttParamUtil.getTopic(userid);
        try {

            MqttClient client = new MqttClient(MqttParamUtil.HOST,clientId, new MemoryPersistence());
            // MQTT的连接设置
            MqttConnectOptions options = new MqttConnectOptions();
            // 设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
            options.setCleanSession(true);
            // 设置连接的用户名
            options.setUserName(username);
            // 设置连接的密码
            options.setPassword(password.toCharArray());
            // 设置超时时间 单位为秒
            options.setConnectionTimeout(10);
            // 设置会话心跳时间 单位为秒 服务器会每隔1.5*20秒的时间向客户端发送个消息判断客户端是否在线，但这个方法并没有重连的机制
            options.setKeepAliveInterval(20);
            // 设置回调函数
            client.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    if(LOGGER.isInfoEnabled()) {
                        LOGGER.info("connectionLost");
                    }
                }
                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    String content = new String(message.getPayload());
                    System.out.println(content);

                }
                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    if(LOGGER.isInfoEnabled()) {
                        LOGGER.info("deliveryComplete---------"+ token.isComplete());
                    }
                }

            });
            client.connect(options);
            //订阅消息
            client.subscribe(topic, 1);
        } catch (Exception e) {
            if(LOGGER.isInfoEnabled()) {
                LOGGER.info(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public String pubMessage() {
        return null;
    }
}

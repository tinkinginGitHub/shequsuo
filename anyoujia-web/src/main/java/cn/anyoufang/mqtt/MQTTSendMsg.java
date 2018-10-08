package cn.anyoufang.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.io.IOException;
import java.util.Date;

import static org.eclipse.paho.client.mqttv3.MqttConnectOptions.MQTT_VERSION_3_1_1;

/**
 * MQTT客户端发送消息
 * @author daiping
 */
public class MQTTSendMsg {
    public static void main(String[] args) throws IOException {
        /**
         * 设置当前用户私有的 MQTT 的接入点。例如此处示意使用 XXX，实际使用请替换用户自己的接入点。接入点的获取方法是，在控制台创建 MQTT 实例，每个实例都会分配一个接入点域名。
         */
        final String broker ="mqtt://m2m.anyoujia.com:1883";
        /**
         * 设置阿里云的 AccessKey，用于鉴权
         */
        final String acessKey ="XXXXXX";
        /**
         * 设置阿里云的 SecretKey，用于鉴权
         */
        final String secretKey ="XXXXXXX";
        /**
         * 发消息使用的一级 Topic，需要先在 MQ 控制台里创建
         */
        final String topic ="XXXX";
        /**
         * MQTT 的 ClientID，一般由两部分组成，GroupID@@@DeviceID
         * 其中 GroupID 在 MQ 控制台里创建
         * DeviceID 由应用方设置，可能是设备编号等，需要唯一，否则服务端拒绝重复的 ClientID 连接
         */
        final String clientId ="GID_XXX@@@ClientID_XXXX";
        String sign;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            final MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            final MqttConnectOptions connOpts = new MqttConnectOptions();
            System.out.println("Connecting to broker: " + broker);
            /**
             * 计算签名，将签名作为 MQTT 的 password。
             * 签名的计算方法，参考工具类 MacSignature，第一个参数是 ClientID 的前半部分，即 GroupID
             * 第二个参数阿里云的 SecretKey
             */
            sign = MacSignature.macSignature(clientId.split("@@@")[0], secretKey);
            connOpts.setUserName(acessKey);
            connOpts.setServerURIs(new String[] { broker });
            connOpts.setPassword(sign.toCharArray());
            connOpts.setCleanSession(true);
            connOpts.setKeepAliveInterval(90);
            connOpts.setAutomaticReconnect(true);
            connOpts.setMqttVersion(MQTT_VERSION_3_1_1);
            sampleClient.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean reconnect, String serverURI) {
                    System.out.println("connect success");
                    //连接成功，需要上传客户端所有的订阅关系
                }
                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("mqtt connection lost");
                }
                @Override
                public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
                    System.out.println("messageArrived:" + topic + "------" + new String(mqttMessage.getPayload()));
                }
                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("deliveryComplete:" + iMqttDeliveryToken.getMessageId());
                }
            });
            sampleClient.connect(connOpts);
            for (int i = 0; i < 10; i++) {
                try {
                    String scontent = new Date()+"MQTT Test body" + i;
                    //此处消息体只需要传入 byte 数组即可，对于其他类型的消息，请自行完成二进制数据的转换
                    final MqttMessage message = new MqttMessage(scontent.getBytes());
                    message.setQos(0);
                    System.out.println(i+" pushed at "+new Date()+" "+ scontent);
                    /**
                     *消息发送到某个主题 Topic，所有订阅这个 Topic 的设备都能收到这个消息。
                     * 遵循 MQTT 的发布订阅规范，Topic 也可以是多级 Topic。此处设置了发送到二级 Topic
                     */
                    sampleClient.publish(topic+"/notice/", message);
                    /**
                     * 如果发送 P2P 消息，二级 Topic 必须是“p2p”，三级 Topic 是目标的 ClientID
                     * 此处设置的三级 Topic 需要是接收方的 ClientID
                     */
                    String p2pTopic =topic+"/p2p/GID_mqttdelay3@@@DEVICEID_001";
                    sampleClient.publish(p2pTopic,message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception me) {
            me.printStackTrace();
        }
    }
}

package cn.anyoufang.mqtt;

import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

/**MQTT 客户端接收消息
 * @author daiping
 */
public class MQTTRecvMsg {
    public static void main(String[] args) {

        //clientId:
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
        final String clientId ="GID_XXXX@@@ClientID_XXXXXX";
        String sign;
        MemoryPersistence persistence = new MemoryPersistence();
        try {
            final MqttClient sampleClient = new MqttClient(broker, clientId, persistence);
            final MqttConnectOptions connOpts = new MqttConnectOptions();
            System.out.println("Connecting to broker: " + broker);
            /**
             * 计算签名，将签名作为 MQTT 的 password
             * 签名的计算方法，参考工具类 MacSignature，第一个参数是 ClientID 的前半部分，即 GroupID
             * 第二个参数阿里云的 SecretKey
             */
            sign = MacSignature.macSignature(clientId.split("@@@")[0], secretKey);
            /**
             * 设置订阅方订阅的 Topic 集合，此处遵循 MQTT 的订阅规则，可以是一级 Topic，二级 Topic，P2P 消息请订阅/p2p
             */
            final String[] topicFilters=new String[]{topic+"/notice/",topic+"/p2p"};
            final int[]qos={0,0};
            connOpts.setUserName(acessKey);
            connOpts.setServerURIs(new String[] { broker });
            connOpts.setPassword(sign.toCharArray());
            connOpts.setCleanSession(true);
            connOpts.setKeepAliveInterval(90);
            connOpts.setAutomaticReconnect(true);
            sampleClient.setCallback(new MqttCallbackExtended() {
                @Override
                public void connectComplete(boolean reconnect, String serverURI){
                    System.out.println("connect success");
                    //连接成功，需要上传客户端所有的订阅关系
                    try {
                        sampleClient.subscribe(topicFilters,qos);
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
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
            //客户端每次上线都必须上传自己所有涉及的订阅关系，否则可能会导致消息接收延迟
            sampleClient.connect(connOpts);
            //每个客户端最多允许存在30个订阅关系，超出限制可能会丢弃导致收不到部分消息
            sampleClient.subscribe(topicFilters,qos);
            Thread.sleep(Integer.MAX_VALUE);
        } catch (Exception me) {
            me.printStackTrace();
        }
    }
}
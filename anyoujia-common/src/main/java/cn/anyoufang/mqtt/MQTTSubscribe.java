package cn.anyoufang.mqtt;

import cn.anyoufang.utils.Md5Utils;
import org.eclipse.paho.client.mqttv3.*;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author daiping
 */
public class MQTTSubscribe implements MqttCallback {

    public static final String HOST = "tcp://m2m.anyoujia.com:1883";

    public static final String TOPIC = "/ctlock/app/147258/get";
    private static final String clientid = "mqttwx_"+cn.anyoufang.utils.UUID.getUUID().substring(2,8);
    private MqttClient client;
    private MqttConnectOptions options;
    private String userName = "ctlock_app_147258";;
    private String passWord = Md5Utils.md5(clientid+"|"+userName+"|"+"ct@2018#mqtt$key","utf-8");
    private ScheduledExecutorService scheduler;

    public void startReconnect() {
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if (!client.isConnected()) {
                    try {
                        client.connect(options);
                        System.out.println("重连成功");
                    } catch (MqttSecurityException e) {
                        e.printStackTrace();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0 * 1000, 10 * 1000, TimeUnit.MILLISECONDS);
    }

    private void start() {
        try {
            client = new MqttClient(HOST, clientid, new MemoryPersistence());
            options = new MqttConnectOptions();
            options.setCleanSession(false);
            options.setUserName(userName);
            options.setPassword(passWord.toCharArray());
            options.setConnectionTimeout(10);
            options.setKeepAliveInterval(20);
            client.setCallback(this);
//            MqttTopic topic = client.getTopic(TOPIC);
//            options.setWill(topic, "close".getBytes(), 2, true);
            client.connect(options);
            int[] Qos = {1};
            String[] topic1 = {TOPIC};
            client.subscribe(topic1, Qos);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        startReconnect();
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Message arrived on topic:" + topic);
        System.out.println("Message arrived on QoS:" + message.getQos());
        System.out.println("Message arrived on content:" + new String(message.getPayload()));

    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        try {
            System.out.println("deliveryComplete---------" + token.isComplete());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MqttException {
        MQTTSubscribe client = new MQTTSubscribe();
        client.start();
    }

}

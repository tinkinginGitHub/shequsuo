package cn.anyoufang.netty;

import cn.anyoufang.util.EncryptionUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author daiping
 */
public class Client {
    private Socket socket = null;
    private OutputStream os = null;
    private InputStream is = null;

    public static void main(String[] args) {
        new Client().new SocketThread().start();
    }

    /**
     * 发送心跳包
     */
    public void sendHeartbeat() {
        try {
            String data =  EncryptionUtil.encrypt("{\"ping\": \"1\"}","u798y79H87BUii7g");
            String heartbeat = "{\"mod\":\"Common\",\"fun\":\"ping\",\"data\":"+data+"}";
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            Thread.sleep(10 * 1000);
                            os.write(heartbeat.getBytes());
                            os.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class SocketThread extends Thread {

        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
           // sendHeartbeat();
            String data =  EncryptionUtil.encrypt("{\"ping\": \"1\"}","u798y79H87BUii7g");
            String heartbeat = "{\"mod\":\"Common\",\"fun\":\"ping\",\"data\":"+data+"}";
            while (true) {
                try {
                    if (socket == null || socket.isClosed()) {
                        // 连接socket
                        socket = new Socket("47.106.227.81", 9501);
                        os = socket.getOutputStream();
                        os.write(heartbeat.getBytes());
                        os.flush();
                    }
                   // Thread.sleep(100);
                    is = socket.getInputStream();
                    int size = is.available();
                    if (size <= 0) {
                        // 如果超过60秒没有收到服务器发回来的信息，说明socket连接可能已经被远程服务器关闭
                        if ((System.currentTimeMillis() - startTime) > 6 * 10 * 1000) {
                            socket.close(); // 这时候可以关闭socket连接
                            startTime = System.currentTimeMillis();
                        }
                        continue;
                    } else {
                        startTime = System.currentTimeMillis();
                    }
                    byte[] resp = new byte[size];
                    is.read(resp);
                    String response = new String(resp, "utf-8");
                    System.out.println(response);
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        socket.close();
                        is.close();
                        os.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        }
    }
}

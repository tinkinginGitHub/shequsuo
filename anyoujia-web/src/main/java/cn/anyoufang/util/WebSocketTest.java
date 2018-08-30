//package cn.anyoufang.util;
//
//import cn.anyoufang.entity.Message;
//import cn.anyoufang.utils.JsonUtils;
//import org.apache.log4j.Logger;
//
//import javax.websocket.*;
//import javax.websocket.server.ServerEndpoint;
//import java.io.IOException;
//import java.util.LinkedList;
//import java.util.Map;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.CopyOnWriteArraySet;
//
//
///**
// * 本来主要用于智能单品操控排队...
// * 注解是一个类层次的注解，它的功能主要是将目前的类定义成一个websocket服务器端,
// * 注解的值将被用于监听用户连接的终端访问URL地址,客户端可以通过这个URL来连接到WebSocket服务器端
// * @ServerEndpoint
// * @author hedaiping
// */
//@ServerEndpoint(value="/websocket")
//public class WebSocketTest {
//	private static Logger logger = Logger.getLogger(WebSocketTest.class);
//    //线程安全的静态变量，表示在线连接数
//    private static volatile int  onlineCount = 0;
//
//    //用来存放每个客户端对应的WebSocketTest对象，适用于同时与多个客户端通信
//    public static CopyOnWriteArraySet<WebSocketTest> webSocketSet = new CopyOnWriteArraySet<WebSocketTest>();
//    //若要实现服务端与指定客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
//    public static ConcurrentHashMap<Session,Object> webSocketMap = new ConcurrentHashMap<Session,Object>();
//
//    public static LinkedList<String> num = new LinkedList<>();
//    public static ConcurrentHashMap<Session,String> sessionMap = new ConcurrentHashMap<Session,String>();
//    public static ConcurrentHashMap<Session,String> houseMap = new ConcurrentHashMap<>();//houseid及对应的session
//    public static ConcurrentHashMap<String, LinkedList<String>> houseOrderMap = new ConcurrentHashMap<>();//houseid以及对应的排队链表
//    public static ConcurrentHashMap<String,Integer> onlines = new ConcurrentHashMap<>();
//
//    //与某个客户端的连接会话，通过它实现定向推送(只推送给某个用户)
//    private Session session;
//
//
//    /**
//     * 连接建立成功调用的方法
//     *
//     * @param session 可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
//     */
//    @OnOpen
//    public void onOpen(Session session){
//        this.session = session;
//        webSocketSet.add(this);//加入set中
//        this.session.getId();
//        webSocketMap.put(session,this); //加入map中
//        addOnlineCount();    //在线数加1
//    }
//
//    /**
//     * 连接关闭调用的方法
//     */
//    @OnClose
//    public void onClose(Session closeSession) {
//        webSocketSet.remove(this); //从set中删除
//        closeSession.getId();
//        webSocketMap.remove(closeSession); //从map中删除
//        String id =  sessionMap.get(closeSession);
//        num.remove(id);
//        sessionMap.remove(closeSession);
//       String houseid =  houseMap.get(closeSession);
//       LinkedList<String> ids = houseOrderMap.get(houseid);
//       ids.remove(id);
//        sub(houseid);
//       subOnlineCount();//在线数减1
//        sendMessage();
//    }
//    public synchronized void sub(String houseid) {
//        int onl = onlines.get(houseid);
//        onl--;
//        onlines.put(houseid,onl);
//    }
//    public void sendMessage() {
//        //--------------群发消息(多用于聊天室场景)
//        for (Session session : webSocketMap.keySet()) {
//            WebSocketTest item = (WebSocketTest) webSocketMap.get(session);
//            try {
//                Message me = new Message();
//                String s = sessionMap.get(session);
//                String houseid = houseMap.get(session);
//                LinkedList<String> orders = houseOrderMap.get(houseid);
//                int online = onlines.get(houseid);
//                me.setId(orders.getFirst());
//                me.setOrder(orders.indexOf(s) == -1 ? 0:orders.indexOf(s));
//                me.setOnline(online);
//                String msg = JsonUtils.objectToJson(me);
//                item.sendMessage(session, msg);
//            } catch(IOException e){
//                logger.info(e.getMessage());
//            }
//        }
//
//    }
//
//    /**
//     * 收到客户端消息后调用的方法
//     *
//     * @param message 客户端发送过来的消息
//     * @throws Exception
//     */
//    @OnMessage
//    public void onMessage(String message,Session mySession) throws Exception {
//    	logger.info("来自客户端的消息:" + message);
//    	Map map = JsonUtils.jsonToMap(message);
//    	String id = String.valueOf(map.get("enter"));
//    	String houseid = String.valueOf(map.get("houseid"));
//    	sessionMap.put(mySession,id);
//    	houseMap.put(mySession,houseid);
//    	if(houseOrderMap.containsKey(houseid)) {
//            LinkedList<String> nums = houseOrderMap.get(houseid);
//            if(nums.contains(id)) {
//                nums.remove(id);
//            }
//            if("admin".equals(id)) {
//                nums.addFirst(id);
//            }else {
//                nums.addLast(id);
//            }
//            houseOrderMap.put(houseid,nums);
//            onlines.put(houseid,nums.size());
//
//        }else {
//    	    LinkedList<String> newNum = new LinkedList<>();
//            if("admin".equals(id)) {
//                newNum.addFirst(id);
//            }else {
//                newNum.addLast(id);
//            }
//    	    houseOrderMap.put(houseid,newNum);
//    	    onlines.put(houseid,newNum.size());
//        }
//    	if(!"admin".equals(id)) {
//            num.addLast(id);
//        }
//        //推送给每个客户端
//        sendMessage();
//    }
//
//    /**
//     * 发生错误时调用
//     *
//     * @param session
//     * @param error
//     */
//    @OnError
//    public void onError(Session session, Throwable error) {
//        logger.info("发生错误: "+error.getMessage());
//    }
//
//
//    //给所有客户端发送信息
//    public void sendAllMessage(String message) throws IOException {
//    			this.session.getBasicRemote().sendText(message);
//    }
//
//    //定向发送信息
//    public void sendMessage(Session mySession,String message) throws IOException {
//    	synchronized(this) {try {
//			if(mySession.isOpen()){//该session如果已被删除，则不执行发送请求，防止报错
//				//this.session.getBasicRemote().sendText(message);
//				mySession.getBasicRemote().sendText(message);
//			}
//		} catch (IOException e) {
//			logger.info(e.getMessage());
//		}
//		}
//    }
//
//    public static synchronized int getOnlineCount() {
//        return onlineCount;
//    }
//
//    public static synchronized void addOnlineCount() {
//        WebSocketTest.onlineCount++;
//    }
//
//    public static synchronized void subOnlineCount() {
//        WebSocketTest.onlineCount--;
//    }
//}
//

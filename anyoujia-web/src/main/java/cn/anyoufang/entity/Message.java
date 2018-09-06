package cn.anyoufang.entity;

/**
 * websocket消息实体
 * @author daiping
 */
public class Message {

    private String id;
    private int order;
    private int online;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getOnline() {
        return online;
    }

    public void setOnline(int online) {
        this.online = online;
    }
}

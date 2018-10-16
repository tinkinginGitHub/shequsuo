package cn.anyoufang.shiro.listener;

import cn.anyoufang.utils.RedisUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * @author daiping
 */
public class MyShiroSessionListener implements SessionListener {
    @Override
    public void onStart(Session session) {

    }

    @Override
    public void onStop(Session session) {
        RedisUtils.del(session.getId().toString());
    }

    @Override
    public void onExpiration(Session session) {
        RedisUtils.del(session.getId().toString());
    }
}

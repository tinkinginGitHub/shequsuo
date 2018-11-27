import cn.anyoufang.utils.RedisMsgPubSubListener;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.regex.Pattern;

/**
 * @author daiping
 */
public class TestPubSub {


    public static void main(String[] args) {
        String s = "<content>";
        String ss = "ss";
        Pattern compile = Pattern.compile("<");
        System.out.println(s.matches(compile.pattern()));
    }
    @Test
    public void pubsubjava() {
        // TODO Auto-generated method stub
        Jedis jr = null;
        try {
            jr = new Jedis("127.0.0.1", 6379, 0);// redis服务地址和端口号
            jr.auth("wx950709");
            RedisMsgPubSubListener sp = new RedisMsgPubSubListener();
            // jr客户端配置监听两个channel
            sp.subscribe("news.share", "news.blog");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(jr!=null){
                jr.disconnect();
            }
        }
    }
}

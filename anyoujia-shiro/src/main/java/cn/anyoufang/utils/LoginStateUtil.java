package cn.anyoufang.utils;

import cn.anyoufang.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

import static cn.anyoufang.utils.StringUtil.parseSession;

/**
 * @author daiping
 */
public class LoginStateUtil {

    private static final Logger logger = LoggerFactory.getLogger(LoginStateUtil.class);


    public static void doUpdateLoginState(HttpServletRequest request, LoginService loginService) {
        String token = request.getHeader("token");
        Map<String,Object> result;
        try {
            result = parseSession(token);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info(e.getMessage());
            }
            return;
        }
        if(result == null) {
            return;
        }
        String username = (String) result.get("username");
        String overtime1 = String.valueOf(result.get("overtime"));
        long endtime1 = new Long(overtime1) * 1000;
        long now1 = System.currentTimeMillis();
        long check = endtime1 - now1;
        if(check <= 0) {
            Map<String, Object> data;
            try {
                data = loginService.updateLogin(username, token);
            } catch (Exception e) {
                if (logger.isInfoEnabled()) {
                    logger.info(e.getMessage());
                }
                return;
            }
            Integer status = (Integer) data.get("status");
            String overtime = String.valueOf(data.get("overtime"));
            if (status == 200) {
                Integer endtime = new Integer(overtime);
                int now = (int) (System.currentTimeMillis()/1000);
                int expire = endtime - now;
                String phone =  (String) result.get("username");
                RedisUtils.setex(Md5Utils.md5(phone,"utf-8"),"1" ,expire);
            }
        }
    }
}

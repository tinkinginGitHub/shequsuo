package cn.anyoufang.controller;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.utils.AesCBC;
import cn.anyoufang.utils.JsonUtils;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.RedisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * user相关Controller公共组件
 * 
 * @author
 * @email
 * @date 2018年08月31日
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SpMember getUser(HttpServletRequest request,LoginService loginService) {
		String token = request.getHeader("token");
        Map<String,Object> result = null;
        try {
            result = parseSession(token);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info(e.getMessage());
            }
            return null;
        }
        if(result != null) {
            String username = (String) result.get("username");
            SpMember user = loginService.getUserByAccount(username);
            if(user != null) {
                //已经登录，并更新登陆时间
                updateLogin(request,loginService);
                return user;
            }
            return null;
        }
        return null;
	}

	protected  boolean isLogin(String token) {
       Map<String,Object> data = parseSession(token);
      if(data != null) {
          String username = (String) data.get("username");
          if (RedisUtils.get(Md5Utils.md5(username,"utf-8")) != null) {
              return true;
          }
      }
		return false;
	}

	protected void updateLogin(HttpServletRequest  request, LoginService loginService) {
		String token = request.getHeader("token");
        Map<String,Object> result = null;
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
            Map<String, Object> data = null;
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
	protected Map<String,Object> parseSession(String session){
        String result;
        try {
            result = AesCBC.getInstance().decrypt(session);
        } catch (Exception e) {
            if (logger.isInfoEnabled()) {
                logger.info(e.getMessage());
            }
            return null;
        }
        try {
		    JsonUtils.isJSONValid(result);
        }catch (Exception e) {
		    return null;
        }
		return JsonUtils.jsonToMap(result);
	}

    /**
     * springmvc 异常处理
     */
//    @ExceptionHandler(Exception.class)
//    public AnyoujiaResult handleException(Exception e) {
//        if(logger.isInfoEnabled()) {
//            logger.info(e.getMessage());
//        }
//        return AnyoujiaResult.build(400,"系统异常");
//    }

}

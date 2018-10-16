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
 * Controller公共组件
 * 
 * @author
 * @email
 * @date 2018年08月31日
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SpMember getUser(HttpServletRequest request,LoginService loginService) throws Exception {
		String token = request.getHeader("token");
		Map<String,Object> result =  parseSession(token);
		if(result != null) {
            String username = (String) result.get("username");
            return loginService.getUserByAccount(username);
        }
        return null;
	}

	protected  boolean isLogin(String token) throws Exception {
       Map<String,Object> data = parseSession(token);
      if(data != null) {
          String username = (String) data.get("username");
          if (RedisUtils.get(Md5Utils.md5(username,"utf-8")) != null) {
              return true;
          }
      }
		return false;
	}

	protected void updateLogin(HttpServletRequest  request, LoginService loginService) throws Exception {
		String token = request.getHeader("token");
		Map<String,Object> result =  parseSession(token);
		if(result == null) {
		    return;
        }
		String username = (String) result.get("username");
        String overtime1 = String.valueOf(result.get("overtime"));
        long endtime1 = new Long(overtime1) * 1000;
        long now1 = System.currentTimeMillis();
        long check = endtime1 - now1;
        if(check <= 0) {
            Map<String, Object> data = loginService.updateLogin(username, token);
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
	protected Map<String,Object> parseSession(String session) throws Exception{
		String result= AesCBC.getInstance().decrypt(session);
		try {
		    JsonUtils.isJSONValid(result);
        }catch (Exception e) {
		    return null;
        }
		return JsonUtils.jsonToMap(result);
	}


}

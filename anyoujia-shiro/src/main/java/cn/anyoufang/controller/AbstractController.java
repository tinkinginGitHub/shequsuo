package cn.anyoufang.controller;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.enumresource.HttpCodeEnum;
import cn.anyoufang.enumresource.UsertypeEnum;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.utils.LoginStateUtil;
import cn.anyoufang.utils.Md5Utils;
import cn.anyoufang.utils.RedisUtils;
import cn.anyoufang.utils.StringUtil;
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

	protected  static final int TWO_H = HttpCodeEnum.TWO_HUNDRED.getCode();
    protected  static final int TWO_H1 = HttpCodeEnum.TWO_HUNDRED1.getCode();
	protected  static final int FOUR_H = HttpCodeEnum.FOUR_HUNDRED.getCode();
	protected  static final int FOUR_H_1 = HttpCodeEnum.FOUR_HUNDRED1.getCode();
	protected  static final int FIVE_H = HttpCodeEnum.FIVE_HUNDRED.getCode();
	protected  static final int FOUR_H_4 = HttpCodeEnum.FOUR_HUNDRED4.getCode();


	protected  static final String FAMILY = UsertypeEnum.ONE.getCode();
    protected  static final String OLDCHILD = UsertypeEnum.TWO.getCode();
    protected  static final String RENTER = UsertypeEnum.THREE.getCode();
	
	protected SpMember getUser(HttpServletRequest request,LoginService loginService) {
		String token = request.getHeader("token");
		//token失效
		if(token == null) {
		    return null;
        }
        //判断是否登录并更新登录状态
        if(isLogin(token)) {
            //已经登录，并更新登陆时间
            updateLogin(request,loginService);
        }else {
            return null;
        }
        Map<String,Object> result;
        //解析token，解析失败直接返回
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
        LoginStateUtil.doUpdateLoginState(request,loginService);
	}

	protected Map<String,Object> parseSession(String session){
       return StringUtil.parseSession(session);
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

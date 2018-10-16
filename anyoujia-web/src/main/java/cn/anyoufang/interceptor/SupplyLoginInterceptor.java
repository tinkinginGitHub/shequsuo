package cn.anyoufang.interceptor;

import cn.anyoufang.annotation.AccessToken;
import cn.anyoufang.utils.RedisUtils;
import cn.anyoufang.utils.StringUtil;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class SupplyLoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod hm = (HandlerMethod) handler;
            Method method = hm.getMethod();
            // 如果有AccessToken,需要鉴权
            if (method.isAnnotationPresent(AccessToken.class)) {
                // 校验逻辑d
               String token =  request.getHeader("token");
                if ( token != null) {
                    RedisUtils.setExpire(parseToken(token),6000);
                    return true;
                }else {
                    response.setStatus(401);
                    return false;
                }

            }
            return  true;
        }
        return false;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
    private String parseToken(String token) {
        String[] datas = StringUtil.decrateToken(token);
        return  datas[0];
    }
}


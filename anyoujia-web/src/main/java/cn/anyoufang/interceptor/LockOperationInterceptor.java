package cn.anyoufang.interceptor;

import cn.anyoufang.entity.SpMember;
import cn.anyoufang.entity.SpMemberRelation;
import cn.anyoufang.log.annotation.LockOperateLog;
import cn.anyoufang.service.CommonService;
import cn.anyoufang.service.LoginService;
import cn.anyoufang.utils.DateUtil;
import cn.anyoufang.utils.LoginStateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Map;

import static cn.anyoufang.utils.StringUtil.parseSession;

public class LockOperationInterceptor extends HandlerInterceptorAdapter {

    private static final Logger logger = LoggerFactory.getLogger(LockOperationInterceptor.class);
    @Autowired
    private CommonService commonService;

    @Autowired
    private LoginService loginService;


    private String getParamString(Map<String, String[]> map) {
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,String[]> e:map.entrySet()){
            sb.append(e.getKey()).append("=");
            String[] value = e.getValue();
            if(value != null && value.length == 1){
                sb.append(value[0]).append("\t");
            }else{
                sb.append(Arrays.toString(value)).append("\t");
            }
        }
        return sb.toString();
    }

    private SpMember getUser(HttpServletRequest request) {
        String token = request.getHeader("token");
        Map<String,Object> result = parseSession(token);
        if(result != null) {
            String username = (String) result.get("username");
            SpMember user = loginService.getUserByAccount(username);
            if(user != null) {
                return user;
            }
        }
        return null;
    }
    private void updateLogin(HttpServletRequest  request, LoginService loginService) {
        LoginStateUtil.doUpdateLoginState(request,loginService);
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod methodHandler = (HandlerMethod) handler;
            LockOperateLog operateLog = methodHandler.getMethod().getAnnotation(LockOperateLog.class);
            if (operateLog != null) {
                String userid = request.getParameter("userid");
                Integer relationid = null;
                String delname = "";
                if (userid != null) {
                    relationid = Integer.valueOf(userid);
                }

                if (relationid != null) {
                    SpMemberRelation relation = loginService.getRelationMember(relationid);
                    String username1 = relation.getUsername();
                    StringBuilder sb = new StringBuilder(username1);
                    delname = sb.toString();
                }
                request.setAttribute(String.valueOf(relationid), delname);
            }
        }
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

        if (handler instanceof HandlerMethod) {
            HandlerMethod methodHandler = (HandlerMethod) handler;
            LockOperateLog operateLog = methodHandler.getMethod().getAnnotation(LockOperateLog.class);
            if(operateLog != null) {
                String action = operateLog.operateTypeDesc();
                String locksn = request.getParameter("locksn");
                String fingerDesc = request.getParameter("fingerdesc");
                String username = request.getParameter("username");
                if(username != null) {
                    StringBuilder sb = new StringBuilder(action);
                    action = sb.append(username).toString();
                }

                String userid = request.getParameter("userid");
                Integer relationid = null;
                if(userid != null) {
                    relationid = Integer.valueOf(userid);
                }
                if(relationid != null) {
                    StringBuilder sb = new StringBuilder(action);
                    String delname = (String) request.getAttribute(String.valueOf(relationid));
                    action =  sb.append(delname).toString();
                }

                if(fingerDesc != null) {
                    StringBuilder sb = new StringBuilder("添加");
                    action = sb.append(fingerDesc).append("指纹").toString();
                }

                SpMember user = getUser(request);
                if(user != null) {
                    int when = DateUtil.generateTenTime();
                    commonService.saveActionRecords(locksn,user.getUid(),action,when);
                }

            }
        }
    }

}


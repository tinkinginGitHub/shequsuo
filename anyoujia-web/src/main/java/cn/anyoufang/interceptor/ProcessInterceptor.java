package cn.anyoufang.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ProcessInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest,
                             HttpServletResponse httpServletResponse,
                             Object o) throws Exception {
        String originHeader = httpServletRequest.getHeader("Origin");
        String refer = httpServletRequest.getHeader("Referer");
        if ("http://192.168.0.133:8081".equals(originHeader)) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin",
                    "http://192.168.0.133:8081");
            httpServletResponse.setHeader("Access-Control-Allow-Headers",
                    "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
            httpServletResponse.setHeader("Access-Control-Allow-Methods",
                    "PUT,POST,GET,DELETE,OPTIONS");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials",
                    "true");
            httpServletResponse.setHeader("Access-Control-Expose-Headers",
                    "Authorization,Cache-Control");
            httpServletResponse.setHeader("X-Powered-By", "Jetty");
        } else if ("http://192.168.0.115:8080".equals(originHeader)) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin",
                    "http://192.168.0.115:8080");
            httpServletResponse.setHeader("Access-Control-Allow-Headers",
                    "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
            httpServletResponse.setHeader("Access-Control-Allow-Methods",
                    "PUT,POST,GET,DELETE,OPTIONS");
            httpServletResponse.setHeader("Access-Control-Expose-Headers",
                    "Authorization,Cache-Control");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials",
                    "true");
            httpServletResponse.setHeader("X-Powered-By", "Jetty");
        } else if ("http://192.168.0.115:8081".equals(originHeader)) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin",
                "http://192.168.0.115:8081");
            httpServletResponse.setHeader("Access-Control-Allow-Headers",
                "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
            httpServletResponse.setHeader("Access-Control-Allow-Methods",
                "PUT,POST,GET,DELETE,OPTIONS");
            httpServletResponse.setHeader("Access-Control-Expose-Headers",
                "Authorization,Cache-Control");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials",
                "true");
            httpServletResponse.setHeader("X-Powered-By", "Jetty");
        } else if ("http://192.168.31.196:8012".equals(originHeader)) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin",
                    "http://192.168.31.196:8012");
            httpServletResponse.setHeader("Access-Control-Allow-Headers",
                    "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
            httpServletResponse.setHeader("Access-Control-Allow-Methods",
                    "PUT,POST,GET,DELETE,OPTIONS");
            httpServletResponse.setHeader("Access-Control-Expose-Headers",
                    "Authorization,Cache-Control");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials",
                    "true");
            httpServletResponse.setHeader("X-Powered-By", "Jetty");
        }else if("http://192.168.31.196:8080".equals(originHeader)) {
            httpServletResponse.setHeader("Access-Control-Allow-Origin",
                    "http://192.168.31.196:8080");
            httpServletResponse.setHeader("Access-Control-Allow-Headers",
                    "Content-Type,Content-Length, Authorization, Accept,X-Requested-With");
            httpServletResponse.setHeader("Access-Control-Allow-Methods",
                    "PUT,POST,GET,DELETE,OPTIONS");
            httpServletResponse.setHeader("Access-Control-Expose-Headers",
                    "Authorization,Cache-Control");
            httpServletResponse.setHeader("Access-Control-Allow-Credentials",
                    "true");
            httpServletResponse.setHeader("X-Powered-By", "Jetty");
        }

        String method = httpServletRequest.getMethod();

        if (method.equals("OPTIONS")) {
            httpServletResponse.setStatus(200);
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse, Object o,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse, Object o,
                                Exception e) throws Exception {

    }
}
package com.light.springboot.intercepter;

import com.light.springboot.config.WebMvcConfig;
//import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器，这里主要是添加登录拦截
 * @author 李振振
 * @version 1.0
 * @date 2019/8/23 17:22
 */
@Configuration
public class BaseIntercepter implements HandlerInterceptor {
//    private Logger logger = Logger.getLogger(WebMvcConfig.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session =request.getSession(true);
        //session持续时间
        int maxInactiveInteval = session.getMaxInactiveInterval();
        //session创建时间
        long createTime = session.getCreationTime();
        //从session获取上次链接时间
        Long operateTime = (Long)session.getAttribute("operateTime");
        //session最新链接时间
        long lastAccessedTime = session.getLastAccessedTime();
        if (operateTime ==null){
            // 初始化
            session.setAttribute("operateTime",lastAccessedTime);
            return true;
        }else{
            // 两次请求间隔，若超过，则说明延时了，需登录
            int intervalTime = (int)((lastAccessedTime - operateTime)/1000);

            if (intervalTime>20){
//                logger.info("--请求超时，请重新登录--");
                // ajax请求
                if(request.getHeader("x-requested-with")!=null){
                    //设置response头，前台依此来判断是否session过期
                    response.setHeader("sessionstatus", "timeout");
                }else{
                    // 非ajax请求
                    // 重定向
                    response.sendRedirect("/index.jsp");
                }
            }
            //更新operateTime
            session.setAttribute("operateTime",lastAccessedTime);
            return true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

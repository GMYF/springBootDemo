package com.light.springboot.intercepter;

import com.light.springboot.config.WebMvcConfig;
//import org.apache.log4j.Logger;
import com.light.springboot.util.log.LogUtil;
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
            if (intervalTime>10){
                LogUtil.info("--请求超时，请重新登录--");
                // ajax请求
                // 异步请求下的重定向
                response.addHeader("FLAG", "-1");
                response.setHeader("SESSIONSTATUS", "TIMEOUT");
                response.setHeader("CONTEXTPATH", php_Address);//重定向目标地址
                response.setStatus(1000);
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

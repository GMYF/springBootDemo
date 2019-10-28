package com.light.springboot.intercepter;

import com.light.springboot.config.WebMvcConfig;
//import org.apache.log4j.Logger;
import com.light.springboot.domain.user.UserToken;
import com.light.springboot.exception.CustomException;
import com.light.springboot.service.UserService;
import com.light.springboot.util.email.EmailUtil;
import com.light.springboot.util.info.CodeMsg;
import com.light.springboot.util.json.JsonUtil;
import com.light.springboot.util.log.LogUtil;
import com.light.springboot.util.string.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 拦截器，这里主要是添加登录拦截
 *
 * @author 李振振
 * @version 1.0
 * @date 2019/8/23 17:22
 */
@Configuration
public class AuthIntercepter implements HandlerInterceptor {
    @Resource
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        HttpSession session = request.getSession(true);
        //session持续时间
        int maxInactiveInteval = session.getMaxInactiveInterval();
        //session创建时间
        long createTime = session.getCreationTime();
        //从session获取上次链接时间
        Long operateTime = (Long) session.getAttribute("operateTime");
        //session最新链接时间
        long lastAccessedTime = session.getLastAccessedTime();
        // 获取token ,通过token拿到用户信息
        String token = StringUtils.safe2String(session.getAttribute("token"));
        UserToken userToken = userService.getUserByToken(token);
        if (userToken == null) {
            LogUtil.info("--用户不存在，请注册--");
            // ajax请求
            // 跳转注册页面
            response.addHeader("FLAG", "2");
            //重定向目标地址
            response.setHeader("CONTEXTPATH", "register");
            response.setStatus(2000);
            throw new CustomException(CodeMsg.USER_ERROR);
        }
        if (operateTime == null) {
            // 初始化
            session.setAttribute("operateTime", lastAccessedTime);
            return true;
        } else {
            // 两次请求间隔，若超过，则说明延时了，需登录
            int intervalTime = (int) ((lastAccessedTime - operateTime) / 1000);
            if (intervalTime > 60 * 30) {
                LogUtil.info("--请求超时，请重新登录--");
                // ajax请求
                // 异步请求下的重定向
                response.addHeader("FLAG", "-1");
                response.setHeader("SESSIONSTATUS", "TIMEOUT");
                //重定向目标地址
                response.setHeader("CONTEXTPATH", "dashboard");
                response.setHeader("Access-Control-Expose-Headers", "SESSIONSTATUS, CONTEXTPATH,FLAG");
                response.setStatus(1000);
                JsonUtil.renderJson(1000, "请求超时，请重新登录", response);
                //更新operateTime
                session.setAttribute("operateTime", lastAccessedTime);
                return false;
            }
            return  true;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
        if ("404".equals(response.getStatus())){
            //
        }else if("500".equals(response.getStatus())){

        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {
//        response.setHeader("Access-Control-Allow-Credentials", "true");
//        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type, X-Requested-With, token");
//        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, OPTIONS, POST, PUT, DELETE");
//        response.setHeader("Access-Control-Allow-Origin", "*");
//        response.setHeader("Access-Control-Max-Age", "3600");
        LogUtil.info("异常拦截处理完毕！！");
        System.out.println("异常拦截处理完毕！！");
        if (e != null) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            List<String> receiverList = new ArrayList<>();
            receiverList.add("985247092@qq.com");
            EmailUtil emailUtil = EmailUtil.getInstance("15207170458@163.com", "haolz9014", receiverList, null, "系统报错：\r\n错误信息为：" + sw.toString(), "SpringBootDemo");
            // 发件邮箱开启了授权码验证，所以这里的密码就是授权码（haolz9014）,原密码为"apurelove9014"
            emailUtil.sendEmail(emailUtil);
        }
    }
}

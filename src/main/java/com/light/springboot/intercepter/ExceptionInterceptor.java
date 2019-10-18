package com.light.springboot.intercepter;

import com.light.springboot.util.email.EmailUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 异常处理，发送邮件等等
 * @author 李振振
 * @version 1.0
 * @date 2019/9/20 14:45
 */
@Configuration
public class ExceptionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
             
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw,true));
        List<String> receiverList = new ArrayList<>();
        receiverList.add("985247092@qq.com");
        EmailUtil emailUtil = EmailUtil.getInstance("15207170458@163.com","haolz9014",receiverList,null,"系统报错：\r\n错误信息为："+sw.toString(),"SpringBootDemo");
        // 发件邮箱开启了授权码验证，所以这里的密码就是授权码（haolz9014）,原密码为"apurelove9014"
        emailUtil.sendEmail(emailUtil);
    }
}

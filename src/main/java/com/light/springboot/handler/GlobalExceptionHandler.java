package com.light.springboot.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.light.springboot.domain.email.Email;
import com.light.springboot.domain.email.EmailConfig;
import com.light.springboot.exception.CustomException;
import com.light.springboot.service.common.EmailService;
import com.light.springboot.util.info.CodeMsg;
import com.light.springboot.util.json.JsonUtil;
import com.light.springboot.util.log.LogUtil;
import com.light.springboot.util.response.ResponseResult;
import com.light.springboot.util.response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/10/25 17:35
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @Autowired
    private EmailService emailService;

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e, HttpServletRequest request) {
        if (e instanceof JSONException) {
            // json 格式异常
            LogUtil.info(String.format("请求报错: url[%s]; msg[%s]", request.getRequestURL(), e.getMessage()));
            ResponseResult<String> res = new ResponseResult<>();
            res.setMessage(e.getMessage());
            res.setCode(ResponseStatus.REQUEST_FAIL.getCode());
            return res;
        }else if(e instanceof BindException) {
            // 绑定异常
            BindException ex = (BindException)e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return ResponseResult.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        }else if (e instanceof CustomException) {
            // 自定义异常
            CustomException customException = (CustomException) e;
            return ResponseResult.error(customException.getCodeMsg());
        } else {
            // 默认异常， 系统异常
            EmailConfig emailConfig = emailService.getEmailConfig();
            List<String> receiverList = new ArrayList<>();
            receiverList.add("985247092@qq.com");
            Email email = new Email();
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
            email.setContent("系统异常,错误信息为-------->"+sw.toString());
            email.setSubject("springBootDemo系统报错");
            email.setReceiver(JSON.toJSONString(receiverList));
            email.setEmailConfig(emailConfig);
            email.setCreateTime(new Date(Calendar.getInstance().getTimeInMillis()));
            emailService.saveEmailMsg(email);
            return ResponseResult.error(CodeMsg.SERVER_ERROR);
        }
    }
}

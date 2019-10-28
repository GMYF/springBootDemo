package com.light.springboot.handler;

import com.alibaba.fastjson.JSONException;
import com.light.springboot.exception.CustomException;
import com.light.springboot.util.info.CodeMsg;
import com.light.springboot.util.log.LogUtil;
import com.light.springboot.util.response.ResponseResult;
import com.light.springboot.util.response.ResponseStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/10/25 17:35
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseResult exceptionHandler(Exception e, HttpServletRequest request) {
        if (e instanceof JSONException) {
            // json 格式异常
            LogUtil.info(String.format("请求报错: url[%s]; msg[%s]", request.getRequestURL(), e.getMessage()));
            ResponseResult<String> res = new ResponseResult<>();
            res.setMessage(e.getMessage());
            res.setStatus(ResponseStatus.REQUEST_FAIL.getStatus());
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
            return ResponseResult.error(CodeMsg.SERVER_ERROR);
        }
    }
}

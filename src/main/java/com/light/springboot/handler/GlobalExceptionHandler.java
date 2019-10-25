package com.light.springboot.handler;

import com.alibaba.fastjson.JSONException;
import com.light.springboot.exception.CustomException;
import com.light.springboot.util.log.LogUtil;
import com.light.springboot.util.response.ResponseResult;
import com.light.springboot.util.response.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/10/25 17:35
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(JSONException.class)
    public ResponseResult<String> jsonException(JSONException e, HttpServletRequest  request) {
        LogUtil.info(String.format("请求报错: url[%s]; msg[%s]",request.getRequestURL(),e.getMessage()));
        ResponseResult<String> res = new ResponseResult<>();
        res.setMessage(e.getMessage());
        res.setStatus(ResponseStatus.REQUEST_FAIL.getStatus());
        return res;
    }

    @ExceptionHandler(CustomException.class)
    public ResponseResult<String> customException(CustomException e,HttpServletRequest request){
        ResponseResult<String> res = new ResponseResult<>();
        res.setMessage(e.getMessage());
        res.setUrl(request.getRequestURL().toString());
        res.setStatus(ResponseStatus.REQUEST_FAIL.getStatus());
        return res;
    }
}

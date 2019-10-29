package com.light.springboot.util.json;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.light.springboot.util.response.ResponseResult;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/9/16 下午10:20
 */
public class JsonUtil {
    public static void renderJson(int code, String msg, HttpServletResponse response){
        ResponseResult responseResult = new ResponseResult();
        responseResult.setCode(code);
        responseResult.setMessage(msg);
        responseContent(response, JSON.toJSONString(responseResult));
    }

    public static void responseContent(HttpServletResponse response,String content){
        try {
            response.reset();
            response.setContentType("application/json");
            response.setHeader("Cache-Control", "no-store");
            response.setCharacterEncoding("UTF-8");
            PrintWriter pw = response.getWriter();
            pw.write(content);
            pw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

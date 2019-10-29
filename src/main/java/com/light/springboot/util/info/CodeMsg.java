package com.light.springboot.util.info;

import lombok.Getter;
import lombok.Setter;

/**
 * 错误信息类
 * @author 李振振
 * @version 1.0
 * @date 2019/10/28 16:46
 */
public class CodeMsg {
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String msg;
    @Getter
    @Setter
    private String url;

    //通用的错误码
    public static CodeMsg SUCCESS = new CodeMsg(0, "success");
    public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
    public static CodeMsg BIND_ERROR = new CodeMsg(500101, "参数校验异常：%s");
    //登录模块 5002XX
    public static CodeMsg SESSION_ERROR = new CodeMsg(500210, "Session不存在或者已经失效");
    public static CodeMsg PASSWORD_EMPTY = new CodeMsg(500211, "登录密码不能为空");
    public static CodeMsg STUDENTID_EMPTY = new CodeMsg(500212, "学号不能为空");
    public static CodeMsg STUDENTID_ERROR = new CodeMsg(500213, "学号格式错误");
    public static CodeMsg STUDENTIDE_NOT_EXIST = new CodeMsg(500214, "学号不存在");
    public static CodeMsg PASSWORD_ERROR = new CodeMsg(500215, "密码错误");
    public static CodeMsg USER_NO_ID = new CodeMsg(500216, "用户id不存在");
    public static CodeMsg LOGIN_ERROR = new CodeMsg(500217, "token失效,请先登录!");



    public CodeMsg fillArgs(Object... args) {
        int code = this.code;
        String message = String.format(this.msg, args);
        return new CodeMsg(code, message);
    }


    private CodeMsg(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    private CodeMsg(int code,String msg,String url) {
        this.code = code;
        this.msg = msg;
        this.url = url;
    }

    @Override
    public String toString() {
        return "CodeMsg{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}

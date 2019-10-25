package com.light.springboot.util.response;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/8/10 15:23
 */
public enum ResponseStatus {
    /**
     * 成功
     */
    SUCCESS("成功",1),
    /**
     * 失败
     */
    FAIL("失败",2),

    REQUEST_FAIL("请求失败",100),
    PARAMETER_FAIL("请求参数有误",200);
    private String message;
    private int status;
    ResponseStatus(String message,int status){
        this.message = message;
        this.status =status;
    }

    public String getMessage() {
        return message;
    }

    public int getStatus() {
        return status;
    }
}

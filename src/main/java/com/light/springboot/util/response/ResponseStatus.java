package com.light.springboot.util.response;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/8/10 15:23
 */
public enum ResponseStatus {
    SUCCESS("成功",1),
    FAIL("失败",2);
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

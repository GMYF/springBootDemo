package com.light.springboot.util.response;

/**
 * 返回对象封装
 * @author 李振振
 * @version 1.0
 * @date 2019/8/10 15:20
 */
public class ResponseResult {
    private int status;
    private String message;
    private Object data;

    public ResponseResult(){
        this.status  = ResponseStatus.SUCCESS.getStatus();
        this.message = ResponseStatus.SUCCESS.getMessage();
    }

    public ResponseResult(int status,String message,Object data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(Object data){
        this.status  = ResponseStatus.SUCCESS.getStatus();
        this.message = ResponseStatus.SUCCESS.getMessage();
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

package com.light.springboot.util.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 返回对象封装
 * @author 李振振
 * @version 1.0
 * @date 2019/8/10 15:20
 */
public class ResponseResult<T> implements Serializable {
    /**
     * 状态
     */
    @Getter
    @Setter
    private int status;
    /**
     * 消息
     */
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String url;
    /**
     * 返回对象
     */
    @Getter
    @Setter
    private T data;

    public ResponseResult(){
        this.status  = ResponseStatus.SUCCESS.getStatus();
        this.message = ResponseStatus.SUCCESS.getMessage();
    }

    public ResponseResult(int status,String message,T data){
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(T data){
        this.status  = ResponseStatus.SUCCESS.getStatus();
        this.message = ResponseStatus.SUCCESS.getMessage();
        this.data = data;
    }

}

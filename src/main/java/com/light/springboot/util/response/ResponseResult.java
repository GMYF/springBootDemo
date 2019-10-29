package com.light.springboot.util.response;

import com.light.springboot.util.info.CodeMsg;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 返回对象封装
 *
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
    private int code;
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

    public ResponseResult() {
        this.code = ResponseStatus.SUCCESS.getCode();
        this.message = ResponseStatus.SUCCESS.getMessage();
    }

    public ResponseResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseResult(T data) {
        this.code = ResponseStatus.SUCCESS.getCode();
        this.message = ResponseStatus.SUCCESS.getMessage();
        this.data = data;
    }

    private ResponseResult(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getCode();
        this.message = cm.getMsg();
        this.url = cm.getUrl();
    }

    /**
     * 请求成功时调用
     *
     * @param data
     * @return
     */
    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<T>(data);
    }

    public static <T> ResponseResult<T> error(CodeMsg cm) {
        return new ResponseResult<T>(cm);
    }

}

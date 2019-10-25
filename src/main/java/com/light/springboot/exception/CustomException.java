package com.light.springboot.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/10/25 17:55
 */
public class CustomException extends RuntimeException {
    @Getter
    @Setter
    private int status;
    @Getter
    @Setter
    private String msg;

    public CustomException(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}

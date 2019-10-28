package com.light.springboot.exception;

import com.light.springboot.util.info.CodeMsg;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/10/25 17:55
 */
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private CodeMsg codeMsg;

    public CustomException(CodeMsg codeMsg) {
        super(codeMsg.toString());
        this.codeMsg = codeMsg;
    }
}

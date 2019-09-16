package com.light.springboot.domain.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/9/11 下午10:45
 */
@Data
public class UserToken {
    @Getter
    @Setter
    private int id;
    @Getter
    @Setter
    private int userId;
    @Getter
    @Setter
    private String token;
    @Getter
    @Setter
    private java.util.Date createDate;
}

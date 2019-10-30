package com.light.springboot.domain.email;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/10/29 17:11
 */
public class Email {
    @Setter
    @Getter
    private int id;
    @Setter
    @Getter
    private String receiver;
    @Setter
    @Getter
    private String copyReceiver;
    @Setter
    @Getter
    private String subject;
    @Setter
    @Getter
    private String content;
    @Setter
    @Getter
    private Date createTime;
    @Setter
    @Getter
    private int status;
    @Setter
    @Getter
    private EmailConfig emailConfig;
}

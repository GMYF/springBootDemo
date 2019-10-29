package com.light.springboot.domain.email;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/10/29 17:11
 */
public class Email {
    @Setter
    @Getter
    private  String host ;
    @Setter
    @Getter
    private List<String> receiver;
    @Setter
    @Getter
    private  List<String> copyReceiver;
    @Setter
    @Getter
    private  String sender;
    @Setter
    @Getter
    private  String passWord;
    @Setter
    @Getter
    private  String subject;
    @Setter
    @Getter
    private  int port;
    @Setter
    @Getter
    private  String content;
    @Setter
    @Getter
    private  String protocol;
}

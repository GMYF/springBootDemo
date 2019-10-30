package com.light.springboot.util.task;

import com.alibaba.fastjson.JSON;
import com.light.springboot.domain.email.Email;
import com.light.springboot.service.common.EmailService;
import com.light.springboot.util.email.EmailUtil;
import com.light.springboot.util.log.LogUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/10/29 16:53
 */
@Component
@Slf4j
public class ScheduleTask {
    @Autowired
    private EmailService emailService;
    /**
     * Async 表示在异步任务池中
     * 定义每5秒执行该任务
     */
    @Async
    @Scheduled(fixedRate = 5000)
    public void sendEmailScheduled(){
        LogUtil.info("邮件定时发送开始");
        List<Email> sendList =   emailService.syncMsg();
        sendList.forEach(email -> {
            EmailUtil emailUtil = EmailUtil.getInstance(email.getEmailConfig().getSender(), email.getEmailConfig().getPassWord(),
                    (List<String>)JSON.parseObject(email.getReceiver(),List.class),
                    email.getCopyReceiver()==null?null:(List<String>)JSON.parseObject(email.getCopyReceiver(),List.class), email.getContent(), email.getSubject());
            // 发件邮箱开启了授权码验证，所以这里的密码就是授权码（haolz9014）,原密码为"apurelove9014"
            try {
                emailUtil.sendEmail(emailUtil);
                email.setStatus(1);
                emailService.updateMsgStatus(email);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        });
    }
}

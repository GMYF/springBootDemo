package com.light.springboot.service.common;

import com.light.springboot.dao.EmailMapper;
import com.light.springboot.domain.email.Email;
import com.light.springboot.domain.email.EmailConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 邮件发送,写入数据库
 *
 * @author 李振振
 * @version 1.0
 * @date 2019/10/29 17:07
 */
@Service
public class EmailService {
    @Autowired
    private EmailMapper emailDao;

    public void saveEmailMsg(Email email) {
        emailDao.saveMsg(email);
    }

    public EmailConfig getEmailConfig() {
        return emailDao.getEmailConfig(0);
    }

    public List<Email> syncMsg() {
        return emailDao.syncMsg();
    }

    public int updateMsgStatus(Email email) {
        return emailDao.updateMsgStatus(email);
    }
}

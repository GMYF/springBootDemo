package com.light.springboot.dao;

import com.light.springboot.domain.email.Email;
import com.light.springboot.domain.email.EmailConfig;
import com.light.springboot.util.email.EmailUtil;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李振振
 * @version 1.0
 * @date 2019/10/29 17:19
 */
@Repository
public interface EmailDao {
    /**
     * 保存邮件消息
     */
    void saveMsg(Email email);

    /**
     * 获取邮件配置信息
     *
     * @param level
     * @return
     */
    EmailConfig getEmailConfig(@Param(value = "level") Integer level);

    /**
     * 获取待执行的消息
     * @return
     */
    List<Email> syncMsg();

    int updateMsgStatus(Email email);
}

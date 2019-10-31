package com.light.springboot.util.email;

import com.sun.mail.smtp.SMTPMessage;
import com.sun.mail.smtp.SMTPTransport;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.apache.commons.mail.DefaultAuthenticator;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.Serializable;
import java.util.*;

/**
 * @author lzz
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public final class EmailUtil implements Serializable {

    private String host;
    private List<String> receiver;
    private List<String> copyReceiver;
    private String sender;
    private String passWord;
    private String subject;
    private int port;
    private String content;
    private String protocol;

    private EmailUtil(String host,String sender, String passWord, List<String> receiver, List<String> copyReceiver, String content, String subject) {
        this.content = content;
        this.receiver = receiver;
        this.sender = sender;
        this.passWord = passWord;
        this.subject = subject;
        this.host = host;
    }


    public static EmailUtil getInstance(String host, String sender, String passWord, List<String> receiver, List<String> copyReceiver, String content, String subject) {
        return new EmailUtil(host, sender, passWord, receiver, copyReceiver, content, subject);
    }

    public static void main(String[] args) {
        try {
            List<String> receiverList = new ArrayList<>();
            receiverList.add("985247092@qq.com");
            EmailUtil emailUtil = EmailUtil.getInstance("","15207170458@163.com", "haolz9014", receiverList, null, "测试我的邮件发送", "文件标题");
            // 发件邮箱开启了授权码验证，所以这里的密码就是授权码（haolz9014）,原密码为"apurelove9014"
            emailUtil.sendEmail(emailUtil);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public Message getMessage(EmailUtil emailUtil, Session session) throws MessagingException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(emailUtil.sender));
        // 发送人,可设置多个
        InternetAddress[] addresses = new InternetAddress[emailUtil.receiver.size()];
        for (int i = 0; i < emailUtil.receiver.size(); i++) {
            addresses[i] = new InternetAddress(emailUtil.receiver.get(i));
        }
        message.setRecipients(MimeMessage.RecipientType.TO, addresses);
        // 抄送人
        if (emailUtil.copyReceiver != null && emailUtil.copyReceiver.size() > 0) {
            InternetAddress[] copyAddresses = new InternetAddress[emailUtil.copyReceiver.size()];
            for (int i = 0; i < emailUtil.copyReceiver.size(); i++) {
                copyAddresses[i] = new InternetAddress(emailUtil.copyReceiver.get(i));
            }
            message.setRecipients(MimeMessage.RecipientType.CC, copyAddresses);
        }
        // 密送人
//        message.setRecipient(MimeMessage.RecipientType.BCC,new InternetAddress(emailAddress));
        message.setSubject(subject);
        message.setContent(content, "text/html;charset=UTF-8");
        // 设置发送时间 ,立刻发送
        message.setSentDate(new Date());
        return message;
    }

    public Session getSession(EmailUtil emailUtil) {
        Properties properties = new Properties();
        // 设置认证方式
        properties.setProperty("mail.smtp.auth", "true");
        // 设置协议方式
        properties.setProperty("mail.transport.protocol", "smtp");
        // 设置发件人的邮箱服务器地址
        properties.setProperty("mail.smtp.host", emailUtil.getHost());

        //  创建 session对象，包含了
        Session session = Session.getInstance(properties, new DefaultAuthenticator(emailUtil.sender, emailUtil.passWord));
        session.setDebug(false);
        return session;
    }

    public void sendEmail(EmailUtil emailUtil) throws MessagingException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Session session = getSession(emailUtil);
                    Message message = getMessage(emailUtil, session);
                    Transport transport = session.getTransport();
                    transport.connect(emailUtil.sender, emailUtil.passWord);
                    transport.sendMessage(message, message.getAllRecipients());
                    // 关闭链接
                    transport.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}

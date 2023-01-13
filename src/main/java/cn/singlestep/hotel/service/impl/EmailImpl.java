package cn.singlestep.hotel.service.impl;

import cn.singlestep.hotel.pojo.entity.MailRequest;
import cn.singlestep.hotel.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/13 13:31
 */
@Service
public class EmailImpl implements EmailService {
    @Resource
    JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sendMailer;
    private static final Logger logger = LoggerFactory.getLogger(EmailImpl.class);

    public void checkMail(MailRequest mailRequest) {
        Assert.notNull(mailRequest,"邮件请求不能为空");
        Assert.notNull(mailRequest.getSendTo(), "邮件收件人不能为空");
        Assert.notNull(mailRequest.getSubject(), "邮件主题不能为空");
        Assert.notNull(mailRequest.getContent(), "邮件收件人不能为空");
    }

    @Override
    public void sendSimpleMail(MailRequest mailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        checkMail(mailRequest);
        //邮件发件人
        message.setFrom(sendMailer);
        //邮件收件人 1或多个
        message.setTo(mailRequest.getSendTo());
        //邮件主题
        message.setSubject(mailRequest.getSubject());
        //邮件内容
        message.setText(mailRequest.getContent());
        //邮件发送时间
        message.setSentDate(new Date());

        javaMailSender.send(message);
        logger.info("发送邮件成功:{}->{}",sendMailer,mailRequest.getSendTo());
    }
}

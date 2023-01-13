package cn.singlestep.hotel.service;

import cn.singlestep.hotel.pojo.entity.MailRequest;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/13 13:26
 */
public interface EmailService {
    /**
     * 发送简单文本邮件
     * @param mailRequest 邮件实体类
     */
    void sendSimpleMail(MailRequest mailRequest);
}

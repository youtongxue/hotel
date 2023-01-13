package cn.singlestep.hotel.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/13 13:27
 */
@Data
public class MailRequest implements Serializable {
    private String sendTo; // 接收人
    private String subject; // 邮件主题
    private String content; // 邮件内容
}

package cn.singlestep.hotel.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/10 15:51
 */
@Data
public class NewAdmin implements Serializable {
    private Long adminId;
    private String adminName; // 管理员名
    private String password; // 密码
    private String superName; // 创建管理员名
    private String superPassword; //超级管理员密码
    private Date createDate; // 创建时间
}

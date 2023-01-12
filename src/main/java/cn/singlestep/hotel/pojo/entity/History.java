package cn.singlestep.hotel.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/7 17:16
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class History implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long historyId;

    @Column(nullable = false)
    private Long orderId;

    @Column(nullable = false)
    private String personPhone; // 用户手机号

    @Column(nullable = false)
    private String personEmail; // 用户邮箱

    @Column(nullable = false)
    private String personName; // 用户姓名

    @Column(nullable = false)
    private String personSex; // 用户性别

    @Column(nullable = false)
    private String personId; // 身份证号

    @Column(nullable = false)
    private String password; // 密码

    @Column(nullable = false)
    private Long roomId; //入住房间号Id

    @Column(nullable = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime; //入住时间

    @Column(nullable = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime; //结束时间

    @Column(nullable = false)
    private String orderPrice; //费用

    @Column(nullable = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createDate; // 创建时间

    @Column(nullable = true)
    private String appRaise; //评价

    @Column(nullable = true)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date appRaiseDate; //评价时间

}

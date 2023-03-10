package cn.singlestep.hotel.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/7 17:24
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class WorkOrder implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long workOrderId;

    @Column(nullable = false)
    @CreatedDate
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss") //时间样式格式化,也可以使用 @DateTimeFormat 注解
    private Date createTime; // 创建时间

    @Column(nullable = false)
    private Long orderId; //订单ID

    @Column(nullable = false)
    private String content; //工单内容

    @Column(nullable = false)
    private String state; //处理状态
}

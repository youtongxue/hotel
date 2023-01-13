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
 * @description 管理员实体类
 * @date 2023/1/7 11:06
 */
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class HotelAdmin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long adminId;

    @Column(nullable = false, unique = true)
    private String adminName; // 管理员名

    @Column(nullable = false)
    private String password; // 密码

    @Column(nullable = false)
    @CreatedDate
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss") //时间样式格式化
    private Date createDate; // 创建时间
}

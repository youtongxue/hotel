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
 * @description 用户User实体类
 * @date 2023/1/7 10:38
 */
@Data //lombok注解简化编写 get/set/toString/equals/hashcode方法
@Entity // 指定当前类是实体类
@EntityListeners(AuditingEntityListener.class) // 加上此注解 JPA中的 @CreatedDate @CreateBy...注解才能生效
public class HotelOrder implements Serializable {
    @Id //此属性作为表的主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 主键的生成方式，IDENTITY 由数据库自动生成
    @Column(nullable = false) // 实体属性与表对应关系
    private Long orderId; // java Long > MySQL bigint

    @Column(nullable = false)
    private String personPhone; // 用户手机号

    @Column(nullable = false)
    private String personEmail; // 用户邮箱

    @Column(nullable = false)
    private String personName; // 用户姓名

    @Column(nullable = false)
    private String personSex; // 用户性别

    @Column(nullable = false, unique = true)
    private String personId; // 身份证号

    @Column(nullable = false)
    private String password; // 密码

    @Column(nullable = false, unique = true)
    private Long roomId; //入住房间号

    @Column(nullable = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime; //入住时间

    @Column(nullable = false)
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date endTime; //结束时间

    @Column(nullable = false)
    private String orderPrice; //费用

    @Column(nullable = false)
    @CreatedDate
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss") //时间样式格式化,也可以使用 @DateTimeFormat 注解
    private Date createDate; // 创建时间
}

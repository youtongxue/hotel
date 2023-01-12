package cn.singlestep.hotel.pojo.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/7 17:03
 */
@Data
@Entity
public class Room implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long roomId;

    @Column(nullable = false)
    private String state; // 状态 use -> 空闲，leisure -> 使用中

    @Column(nullable = false)
    private String price; // 价格

    @Column(unique = true)
    private String orderId; // 订单id

}

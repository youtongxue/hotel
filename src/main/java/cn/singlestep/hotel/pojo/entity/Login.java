package cn.singlestep.hotel.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/10 11:19
 */
@Data
public class Login implements Serializable {
    private String name;
    private String password;
}

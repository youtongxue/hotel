package cn.singlestep.hotel.service;

import cn.singlestep.hotel.pojo.entity.Login;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/10 11:22
 */
public interface LoginService {
    /**
     * admin、user登录
     * */
    void loginMethod(Login loginInfo);
}

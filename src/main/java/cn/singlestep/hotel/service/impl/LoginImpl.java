package cn.singlestep.hotel.service.impl;

import cn.singlestep.hotel.exception.GlobalException;
import cn.singlestep.hotel.exception.GlobalExceptionEnum;
import cn.singlestep.hotel.pojo.entity.HotelAdmin;
import cn.singlestep.hotel.pojo.entity.HotelOrder;
import cn.singlestep.hotel.pojo.entity.Login;
import cn.singlestep.hotel.service.HotelAdminService;
import cn.singlestep.hotel.service.LoginService;
import cn.singlestep.hotel.service.OrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author onestep
 * @description 管理员、用户登录实现具体逻辑
 * @date 2023/1/10 11:23
 */
@Service
public class LoginImpl implements LoginService {
    @Resource
    OrderService orderService;
    @Resource
    HotelAdminService hotelAdminService;
    @Override
    public void loginMethod(Login loginInfo) {
        String name = loginInfo.getName();
        String password = loginInfo.getPassword();

        /**
         * 新增管理员时限定，长度小于10
         * 根据用户名的长度判断，是管理员还是用户
         * */
        // 管理员登录
        if (name.length() <= 10) {
            HotelAdmin hotelAdmin = hotelAdminService.adminLogin(name);
            // 无此管理员
            if (hotelAdmin == null) {
                throw new GlobalException(GlobalExceptionEnum.NO_ADMIN_ERROR);
            }
            // 密码错误
            if (!hotelAdmin.getPassword().equals(password)) {
                throw new GlobalException(GlobalExceptionEnum.PASSWORD_ERROR);
            }
            // 登录正常
            return;
        }

        // 长度等于18(身份证号)则为用户
        if (name.length() == 18) {
            HotelOrder order = orderService.findByPersonId(name);
            // 不存在此用户
            if (order == null) {
                throw new GlobalException(GlobalExceptionEnum.NO_USER_ERROR);
            }
            // 密码错误
            if (!order.getPassword().equals(password)) {
                throw new GlobalException(GlobalExceptionEnum.PASSWORD_ERROR);
            }

        } else {
            // 用户名长度错误
            throw new GlobalException(GlobalExceptionEnum.USER_NAME_ERROR);
        }
    }
}

package cn.singlestep.hotel.service.impl;

import cn.singlestep.hotel.exception.GlobalException;
import cn.singlestep.hotel.exception.GlobalExceptionEnum;
import cn.singlestep.hotel.pojo.entity.HotelAdmin;
import cn.singlestep.hotel.pojo.entity.NewAdmin;
import cn.singlestep.hotel.repository.HotelAdminRepository;
import cn.singlestep.hotel.service.HotelAdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 15:36
 */
@Service
public class HotelAdminImpl implements HotelAdminService {
    @Resource
    HotelAdminRepository hotelAdminRepository;

    @Override
    public List<HotelAdmin> findAll() {
        return hotelAdminRepository.findAll();
    }

    @Override
    public HotelAdmin insert(NewAdmin newAdmin) {
        String superName = newAdmin.getSuperName();
        String superPassword = newAdmin.getSuperPassword();
        String name = newAdmin.getAdminName();
        String password = newAdmin.getPassword();

        // 判断是否有权限
        if (!superName.equals("super") || !superPassword.equals("123456")) {
            throw new GlobalException(GlobalExceptionEnum.NOT_SUPER_ERROR);
        }

        // 判断是否存在同名管理员
        HotelAdmin admin = hotelAdminRepository.adminLogin(name);
        if (admin != null) {
            throw new GlobalException(GlobalExceptionEnum.HAVE_ADMIN_ERROR);
        }

        /**
         * 不允许管理员的用户名长度大于10
         * 在登录时，管理员和用户登录采用同一登录接口
         * 需要根据登录名的长度进行判断,具体逻辑在 LoginImpl.java
         * */
        if (name.length() > 10) {
            throw new GlobalException(GlobalExceptionEnum.ADMIN_NAME_ERROR);
        }

        HotelAdmin hotelAdmin = new HotelAdmin();
        hotelAdmin.setAdminName(name);
        hotelAdmin.setPassword(password);
        return hotelAdminRepository.save(hotelAdmin);
    }

    @Override
    public void deletedByHotelAdminId(Long hotelAdminId) {
        hotelAdminRepository.deleteById(hotelAdminId);
    }

    @Override
    public HotelAdmin adminLogin(String adminName) {
        return hotelAdminRepository.adminLogin(adminName);
    }
}

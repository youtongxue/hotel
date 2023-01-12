package cn.singlestep.hotel.service;

import cn.singlestep.hotel.pojo.entity.HotelAdmin;
import cn.singlestep.hotel.pojo.entity.NewAdmin;

import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 15:34
 */
public interface HotelAdminService {
    /**
     * 查询所有管理员
     * */
    List<HotelAdmin> findAll();

    /**
     * 新增管理员
     * */
    HotelAdmin insert(NewAdmin newAdmin);

    /**
     * 根据 adminId 删除管理员
     * */
    void deletedByHotelAdminId(Long hotelAdminId);

    /**
     * 管理员登录
     * */
    HotelAdmin adminLogin(String adminName);
}

package cn.singlestep.hotel.repository;

import cn.singlestep.hotel.pojo.entity.HotelAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 15:33
 */
@Repository
public interface HotelAdminRepository extends JpaRepository<HotelAdmin, Long> {
    /**
     * 管理员登录
     * 根据 adminName 查询管理员信息
     * */
    @Query("select admin from HotelAdmin admin where admin.adminName = ?1")
    HotelAdmin adminLogin(String adminName);

}

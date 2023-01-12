package cn.singlestep.hotel.repository;

import cn.singlestep.hotel.pojo.entity.HotelOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author onestep
 * @description Order信息的CRUD接口
 * 继承自JPA的JpaRepository 接口，此接口定义了常用的增删查改方法
 * @date 2023/1/7 11:21
 */
@Repository
public interface OrderRepository extends JpaRepository<HotelOrder, Long> {
    /**
     * 根据 personId 查询
     * */
    @Query("select hotel_order from HotelOrder hotel_order where hotel_order.personId = ?1")
    HotelOrder findByPersonId(String personId);

}

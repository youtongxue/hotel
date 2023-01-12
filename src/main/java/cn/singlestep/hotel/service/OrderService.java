package cn.singlestep.hotel.service;

import cn.singlestep.hotel.pojo.entity.HotelOrder;

import java.util.List;

/**
 * @author onestep
 * @description Order信息的具体操作方法定义
 * @date 2023/1/7 11:36
 */
public interface OrderService {
    /**
     * 新增订单
     * @param order 订单实体类
     * */
    HotelOrder insert(HotelOrder order);

    /**
     * 删除订单信息
     * @param orderId 订单的id
     * */
    void delete(Long orderId);

    /**
     * 修改订单信息
     * @param order 订单实体类
     * */
    HotelOrder updateOrder(HotelOrder order);

    /**
     * 查询所有订单
     * */
    List<HotelOrder> findAll();

    /**
     * 根据id查询dd
     * @param orderId 订单id
     * */
    HotelOrder findByOrderId(Long orderId);

    /**
     * 根据 personId 查询订单(1、登录场景)
     * @param personId 用户身份证号
     */
    HotelOrder findByPersonId(String personId);

}

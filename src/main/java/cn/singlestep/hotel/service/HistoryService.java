package cn.singlestep.hotel.service;

import cn.singlestep.hotel.pojo.entity.History;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/7 23:59
 */
public interface HistoryService {
    /**
     * 新增历史订单
     * */
    void insert(History history);

    /**
     * 查询所有历史订单
     * */
    List<History> findAll();

    /**
     * 根据 personId 查询
     * */
    List<History> findAllByPersonId(String personId);

    /**
     * 用户根据自己的订单对酒店进行评价(更新订单部分字段)
     * @param appRaise 评价
     * @param orderId 订单Id
     * */
    void updateByOrderId(String appRaise, Long orderId);

}

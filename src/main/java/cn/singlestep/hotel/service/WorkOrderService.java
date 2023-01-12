package cn.singlestep.hotel.service;

import cn.singlestep.hotel.pojo.entity.WorkOrder;

import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 11:04
 */
public interface WorkOrderService {
    /**
     * 新增工单
     * */
    WorkOrder insert(WorkOrder workOrder);

    /**
     * 查询所有工单
     * */
    List<WorkOrder> findAll();

    /**
     * 根据工单 state 状态查询所有
     * */
    List<WorkOrder> findAllByWorkOrderState(String state);

    /**
     * 根据工单 id 修改 state 值
     * */
    void updateByWorkOrderState(String workOrderState, Long workOrderId);


    /**
     * 根据 orderId 查询所有工单
     * */
    List<WorkOrder> findAllByOrderId(Long orderId);
}

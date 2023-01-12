package cn.singlestep.hotel.service.impl;

import cn.singlestep.hotel.exception.GlobalException;
import cn.singlestep.hotel.exception.GlobalExceptionEnum;
import cn.singlestep.hotel.exception.GlobalResponse;
import cn.singlestep.hotel.pojo.entity.HotelOrder;
import cn.singlestep.hotel.pojo.entity.WorkOrder;
import cn.singlestep.hotel.repository.HotelAdminRepository;
import cn.singlestep.hotel.repository.OrderRepository;
import cn.singlestep.hotel.repository.WorkOrderRepository;
import cn.singlestep.hotel.service.WorkOrderService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 11:15
 */
@Service
public class WorkOrderImpl implements WorkOrderService {
    @Resource
    WorkOrderRepository workOrderRepository;
    @Resource
    OrderRepository orderRepository;

    @Override
    public WorkOrder insert(WorkOrder workOrder) {
        HotelOrder order = orderRepository.findById(workOrder.getOrderId()).orElse(null);
        if (order == null) {
            throw new GlobalException(GlobalExceptionEnum.HAVE_NO_ORDER_ORDER);
        }
        workOrder.setState("wait");
        return workOrderRepository.save(workOrder);
    }

    @Override
    public List<WorkOrder> findAll() {
        return workOrderRepository.findAll();
    }

    @Override
    public List<WorkOrder> findAllByWorkOrderState(String workOrderState) {

        switch (workOrderState) {
            case "all":
                return findAll();
            case "wait":
            case "done":
            case "withdraw":
                return workOrderRepository.findAllByWorkOrderState(workOrderState);
        }

        throw new GlobalException(GlobalExceptionEnum.WORKER_STATE_ERROR);
    }

    @Override
    public void updateByWorkOrderState(String workOrderState, Long workOrderId) {
        workOrderRepository.updateByWorkOrderState(workOrderState, workOrderId);
    }

    @Override
    public List<WorkOrder> findAllByOrderId(Long orderId) {
        HotelOrder order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new GlobalException(GlobalExceptionEnum.HAVE_NO_ORDER_ORDER);
        }
        return workOrderRepository.finAllByOrderId(orderId);
    }
}

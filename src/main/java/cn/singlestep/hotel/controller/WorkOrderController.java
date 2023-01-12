package cn.singlestep.hotel.controller;

import cn.singlestep.hotel.exception.GlobalResponse;
import cn.singlestep.hotel.pojo.entity.WorkOrder;
import cn.singlestep.hotel.service.WorkOrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 18:26
 */
@RestController
@RequestMapping("/workorder")
public class WorkOrderController {
    @Resource
    WorkOrderService workOrderService;

    /**
     * 新增工单
     * */
    @PostMapping("/add")
    public GlobalResponse insert(@RequestBody WorkOrder workOrder) {
        workOrderService.insert(workOrder);
        return GlobalResponse.success("提交成功");
    }

    /**
     * 查询所有工单
     * */
    @GetMapping("/all")
    public GlobalResponse findAll() {
        return GlobalResponse.success(workOrderService.findAll());
    }

    /**
     * 根据工单 state 状态查询所有
     * */
    @GetMapping("/state")
    public GlobalResponse findAllByWorkOrderState(@RequestParam String state) {
        return GlobalResponse.success(workOrderService.findAllByWorkOrderState(state));
    }

    /**
     * 根据工单 id 修改 state 值
     * */
    @GetMapping("/update")
    public GlobalResponse updateByWorkOrderState(@RequestParam String workOrderState, Long workOrderId) {
        workOrderService.updateByWorkOrderState(workOrderState, workOrderId);
        return GlobalResponse.success("更新Sate值成功");
    }

    /**
     * 根据 orderId 查询所有工单
     * */
    @GetMapping("/orderid")
    public GlobalResponse findAllByOrderId(@RequestParam Long orderId) {
        return GlobalResponse.success(workOrderService.findAllByOrderId(orderId));
    }
}

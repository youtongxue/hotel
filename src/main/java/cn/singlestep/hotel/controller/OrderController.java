package cn.singlestep.hotel.controller;

import cn.singlestep.hotel.exception.GlobalException;
import cn.singlestep.hotel.exception.GlobalExceptionEnum;
import cn.singlestep.hotel.exception.GlobalResponse;
import cn.singlestep.hotel.pojo.entity.HotelOrder;
import cn.singlestep.hotel.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/7 11:57
 */
@RestController
@RequestMapping("/order") //定义此Controller类中，所有接口的公共路径请求头
public class OrderController {
    @Resource //依赖注入
    private OrderService orderService;

    /**
     * 新增订单
     * */
    @PostMapping("/add")
    public GlobalResponse addUser(@RequestBody HotelOrder order) {
        return GlobalResponse.success(orderService.insert(order));
    }

    /**
     * 删除订单信息
     * @param orderId 订单的id
     * */
    @GetMapping("/del")
    public GlobalResponse delOrder(@RequestParam Long orderId) {
        orderService.delete(orderId);
        return GlobalResponse.success("删除成功");
    }

    /**
     * 查询所有订单
     * */
    @GetMapping("/all")
    public GlobalResponse allOrder() {
        return GlobalResponse.success(orderService.findAll());
    }

    /**
     * 根据orderId查询
     * @param orderId 订单id
     * */
    @GetMapping("/orderid")
    public GlobalResponse findOrderByOrderId(@RequestParam Long orderId) {
        return GlobalResponse.success(orderService.findByOrderId(orderId));
    }

    /**
     * 通过 personId 查询用户订单
     * @param personId 用户身份证号
     * */
    @GetMapping("/personid")
    public GlobalResponse findOrderByPersonId(@RequestParam String personId) {
        HotelOrder order = orderService.findByPersonId((personId));

        if (order == null) {
            throw new GlobalException(GlobalExceptionEnum.HAVE_NO_PERSON_ORDER);
        }

        return GlobalResponse.success(order);
    }

}

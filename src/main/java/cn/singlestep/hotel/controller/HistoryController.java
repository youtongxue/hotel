package cn.singlestep.hotel.controller;

import cn.singlestep.hotel.exception.GlobalResponse;
import cn.singlestep.hotel.service.HistoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 18:11
 */
@RestController
@RequestMapping("/history")
public class HistoryController {
    @Resource
    HistoryService historyService;

    /**
     * 查询所有历史订单
     * */
    @GetMapping("/all")
    public GlobalResponse findAll() {
        return GlobalResponse.success(historyService.findAll());
    }

    /**
     * 根据 personId 查询
     * */
    @GetMapping("/personid")
    public GlobalResponse findAllByPersonId(@RequestParam String personId) {
        return GlobalResponse.success(historyService.findAllByPersonId(personId));
    }

    /**
     * 用户根据自己的订单对酒店进行评价(更新订单部分字段)
     * @param appRaise 评价
     * @param orderId 订单Id
     * */
    @GetMapping("/appraise")
    public GlobalResponse updateByOrderId(String appRaise, String orderId) {
        historyService.updateByOrderId(appRaise, Long.valueOf(orderId));
        return GlobalResponse.success("评价成功");
    }


}

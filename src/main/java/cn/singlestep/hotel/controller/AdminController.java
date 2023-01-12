package cn.singlestep.hotel.controller;

import cn.singlestep.hotel.exception.GlobalResponse;
import cn.singlestep.hotel.pojo.entity.NewAdmin;
import cn.singlestep.hotel.service.HotelAdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 19:25
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    HotelAdminService hotelAdminService;

    /**
     * 查询所有管理员
     * */
    @GetMapping("/all")
    public GlobalResponse findAll() {
        return GlobalResponse.success(hotelAdminService.findAll());
    }

    /**
     * 新增管理员
     * */
    @PostMapping("/add")
    public GlobalResponse insert(@RequestBody NewAdmin newAdmin) {
        return GlobalResponse.success(hotelAdminService.insert(newAdmin));
    }

    /**
     * 根据 adminId 删除管理员
     * */
    @GetMapping("/del")
    public GlobalResponse deletedByHotelAdminId(@RequestParam Long hotelAdminId) {
        hotelAdminService.deletedByHotelAdminId(hotelAdminId);
        return GlobalResponse.success("删除成功");
    }

}

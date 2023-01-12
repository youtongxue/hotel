package cn.singlestep.hotel.controller;

import cn.singlestep.hotel.exception.GlobalResponse;
import cn.singlestep.hotel.pojo.entity.HotelAdmin;
import cn.singlestep.hotel.pojo.entity.NewAdmin;
import cn.singlestep.hotel.pojo.entity.Room;
import cn.singlestep.hotel.service.HotelAdminService;
import cn.singlestep.hotel.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/10 13:39
 */
@RestController
public class InitController {
    @Resource
    RoomService roomService;
    @Resource
    HotelAdminService hotelAdminService;

    /**
     * 创建初始化数据
     * 1、随机房间信息
     * 2、新增一个 superadmin 超级管理员
     * */
    /**
     * 新增房间(创建模拟数据使用)
     * */
    @GetMapping("/init")
    public GlobalResponse addRoom() {
        // 创建房间
        for (int i = 0; i < 5; i++) {
            Random random = new Random();
            Room room = new Room();
            room.setState("leisure");
            room.setPrice(String.valueOf(random.nextInt(1000)));

            roomService.insert(room);
        }

        // 创建超级管理员
        NewAdmin superAdmin = new NewAdmin();
        superAdmin.setAdminName("super");
        superAdmin.setPassword("123456");
        superAdmin.setSuperName("super");
        superAdmin.setSuperPassword("123456");
        hotelAdminService.insert(superAdmin);

        return GlobalResponse.success("创建房间、超级管理员(账号 super 密码 123456)成功");
    }

}

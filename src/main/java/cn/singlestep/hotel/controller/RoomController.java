package cn.singlestep.hotel.controller;

import cn.singlestep.hotel.exception.GlobalResponse;
import cn.singlestep.hotel.pojo.entity.Room;
import cn.singlestep.hotel.service.RoomService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 17:46
 */
@RestController
@RequestMapping("/room")
public class RoomController {
    @Resource
    RoomService roomService;

    /**
     * 查询所有房间信息
     * */
    @GetMapping("/all")
    public GlobalResponse allRoom() {
        return GlobalResponse.success(roomService.findAll());
    }

    /**
     * 根据id查询某个房间信息
     * */
    @GetMapping("/roomid")
    public GlobalResponse finByRoomId(@RequestParam Long roomId) {
        return GlobalResponse.success(roomService.findByRoomId(roomId));
    }

    /**
     * 根据类型查询所有 use、leisure、all 房间
     * */
    @GetMapping("/state")
    public GlobalResponse findByRoomState(@RequestParam String state) {
        return GlobalResponse.success(roomService.findAllByRoomState(state));
    }

}

package cn.singlestep.hotel.controller;

import cn.singlestep.hotel.exception.GlobalResponse;
import cn.singlestep.hotel.pojo.entity.Notice;
import cn.singlestep.hotel.service.NoticeService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 19:16
 */
@RestController
@RequestMapping("notice")
public class NoticeController {
    @Resource
    NoticeService noticeService;

    /**
     * 查询所有公告
     * */
    @GetMapping("/all")
    public GlobalResponse findAll() {
        return GlobalResponse.success(noticeService.findAll());
    }

    /**
     * 新增公告
     * */
    @PostMapping("/add")
    public GlobalResponse insert(@RequestBody Notice notice) {
        return GlobalResponse.success(noticeService.insert(notice));
    }

    /**
     * 删除公告
     * */
    @GetMapping("/del")
    public GlobalResponse deletedByNoticeId(@RequestParam Long noticeId) {
        noticeService.deletedByNoticeId(noticeId);
        return GlobalResponse.success("删除成功");
    }
}

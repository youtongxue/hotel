package cn.singlestep.hotel.service;

import cn.singlestep.hotel.pojo.entity.Notice;
import org.aspectj.weaver.ast.Not;

import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 15:20
 */
public interface NoticeService {
    /**
     * 查询所有公告
     * */
    List<Notice> findAll();

    /**
     * 新增公告
     * */
    Notice insert(Notice notice);

    /**
     * 删除公告
     * */
    void deletedByNoticeId(Long noticeId);

}

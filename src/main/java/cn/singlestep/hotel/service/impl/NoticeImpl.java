package cn.singlestep.hotel.service.impl;

import cn.singlestep.hotel.pojo.entity.Notice;
import cn.singlestep.hotel.repository.NoticeRepository;
import cn.singlestep.hotel.service.NoticeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 15:22
 */
@Service
public class NoticeImpl implements NoticeService {
    @Resource
    NoticeRepository noticeRepository;

    @Override
    public List<Notice> findAll() {
        return noticeRepository.findAll();
    }

    @Override
    public Notice insert(Notice notice) {
        return noticeRepository.save(notice);
    }

    @Override
    public void deletedByNoticeId(Long noticeId) {
        noticeRepository.deleteById(noticeId);
    }
}

package cn.singlestep.hotel.repository;

import cn.singlestep.hotel.pojo.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 15:18
 */
public interface NoticeRepository extends JpaRepository<Notice, Long> {

}

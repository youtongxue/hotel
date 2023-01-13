package cn.singlestep.hotel.repository;

import cn.singlestep.hotel.pojo.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/7 23:37
 */
@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
    /**
     * 通过用户 personId 查询所有历史订单
     * */
    @Query("select history from History history where history.personId =?1")
    List<History> findAllByPersonId(String personId);

    /**
     * 根据 OrderId 修改酒店评价
     * */
    @Modifying
    @Transactional
    @Query(value = "update history history set history.app_raise =?1, history.app_raise_date =?2 where history.order_id = ?3",nativeQuery = true)
    void updateByOrderId(String appRaise, Date appRaiseDate, Long orderId);

    /**
     * 根据 OrderID 查询是否存在，此历史订单
     * */
    @Query("select history from History history where history.orderId =?1")
    History findByOrderId(Long orderId);


}

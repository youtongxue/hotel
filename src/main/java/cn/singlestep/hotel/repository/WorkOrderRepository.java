package cn.singlestep.hotel.repository;

import cn.singlestep.hotel.pojo.entity.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 10:58
 */
@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    /**
     * 根据工单 state 状态查询
     * */
    @Query("select workorder from WorkOrder workorder where workorder.state = ?1")
    List<WorkOrder> findAllByWorkOrderState(String state);

    /**
     * 根据订单 orderId 查询提交的工单
     * */
    @Query("select workorder from WorkOrder workorder where workorder.orderId = ?1")
    List<WorkOrder> finAllByOrderId(Long orderId);


    /**
     * 根据 orderId 修改工单 state 状态值
     * */
    @Modifying
    @Transactional
    @Query(value = "update Work_Order workorder set workorder.state =?1 where workorder.work_order_id = ?2",nativeQuery = true)
    void updateByWorkOrderState(String workOrderState, Long workOrderId);

    /**
     * 根据 orderId 删除工单
     * */
    @Modifying
    @Transactional
    @Query(value = "delete from work_order where work_order.order_id=?1", nativeQuery = true)
    void deleteByOrderId(Long orderId);

}

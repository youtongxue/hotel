package cn.singlestep.hotel.repository;

import cn.singlestep.hotel.pojo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/7 17:53
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * 根据Room ID 修改房间状态、用户
     * */
    @Modifying
    @Transactional
    @Query(value = "update Room room set room.state =?1, room.order_id =?2 where room.room_id = ?3",nativeQuery = true)
    void updateByRoomId(String roomState, Long orderId, Long roomId);

    /**
     * 根据查询 Type查询All,Use,Leisure三种状态的房间信息
     * */
    @Query(value = "select room from Room room where room.state =?1")
    List<Room> findAllByRoomState(String roomState);

}

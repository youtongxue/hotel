package cn.singlestep.hotel.service;

import cn.singlestep.hotel.pojo.entity.Room;

import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/7 17:40
 */
public interface RoomService {
    /**
     * 查询所有房间信息
     * */
    List<Room> findAll();

    /**
     * 新增房间(创建模拟数据使用)
     * */
    Room insert(Room room);

    /**
     * 根据id查询某个房间信息
     * */
    Room findByRoomId(Long roomId);

    /**
     * 1.用户注册的时候，选择一个房间
     * 即修改选择的房间 state 值，并且设置房间的 orderId
     *
     * 2.订单结束后修改房间的 state 值，将 orderId 赋Null
     * */
    void updateByRoomId(String roomState, Long orderId, Long roomId);

    /**
     * 根据查询房间的查询类型
     * @param roomState all -> 所有房间信息， use -> 正在使用中的房间信息
     *             leisure -> 空闲房间
     * */
    List<Room> findAllByRoomState(String roomState);

}

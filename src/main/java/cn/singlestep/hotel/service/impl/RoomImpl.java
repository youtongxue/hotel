package cn.singlestep.hotel.service.impl;

import cn.singlestep.hotel.exception.GlobalException;
import cn.singlestep.hotel.exception.GlobalExceptionEnum;
import cn.singlestep.hotel.service.RoomService;
import cn.singlestep.hotel.pojo.entity.Room;
import cn.singlestep.hotel.repository.RoomRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/7 17:51
 */
@Service
public class RoomImpl implements RoomService {
    @Resource
    RoomRepository roomRepository;

    @Override
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room insert(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room findByRoomId(Long id) {
        Room room = roomRepository.findById(id).orElse(null);

        if (room == null) {
            throw new GlobalException(GlobalExceptionEnum.HAVE_NO_ROOM);
        }

        return room;
    }

    @Override
    public void updateByRoomId(String roomState, Long orderId, Long roomId) {

        switch (roomState) {
            case "leisure":
                roomRepository.updateByRoomId(roomState, null, roomId);
                return;
            case "use":
                roomRepository.updateByRoomId(roomState, orderId, roomId);
                return;
        }

        throw new GlobalException(GlobalExceptionEnum.ROOM_STATE_ERROR);

    }

    @Override
    public List<Room> findAllByRoomState(String roomState) {

        switch (roomState) {
            case "all":
                return findAll();
            case "use":
            case "leisure":
                return roomRepository.findAllByRoomState(roomState);
        }

        // 传入 roomState 值错误
        throw new GlobalException(GlobalExceptionEnum.ROOM_STATE_ERROR);
    }

}

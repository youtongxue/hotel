package cn.singlestep.hotel.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdcardUtil;
import cn.hutool.core.util.RandomUtil;
import cn.singlestep.hotel.exception.GlobalException;
import cn.singlestep.hotel.exception.GlobalExceptionEnum;
import cn.singlestep.hotel.pojo.entity.*;
import cn.singlestep.hotel.repository.OrderRepository;
import cn.singlestep.hotel.repository.WorkOrderRepository;
import cn.singlestep.hotel.service.EmailService;
import cn.singlestep.hotel.service.HistoryService;
import cn.singlestep.hotel.service.OrderService;
import cn.singlestep.hotel.service.RoomService;
import cn.singlestep.hotel.util.VerifyEmail;
import cn.singlestep.hotel.util.VerifyPhone;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/7 11:45
 */
@Service
public class OrderImpl implements OrderService {
    @Resource //使用 Resource 注解实现依赖注入
    private OrderRepository orderRepository;
    @Resource
    private RoomService roomService;
    @Resource
    private HistoryService historyService;
    @Resource
    private WorkOrderRepository workOrderRepository;
    @Resource
    private EmailService emailService;


    /**
     * 新订单
     *
     * 插入前需要判断表中是否存在同用户名 personId
     * 不存在则存入表中
     * 存在则返回 以存在此用户
     * @return 成功 -> User信息, 失败 code -> 1000
     *
     */
    @Override
    public HotelOrder insert(HotelOrder order) {
        HotelOrder orderResult = findByPersonId(order.getPersonId());
        Room room = roomService.findByRoomId(order.getRoomId());

        //校验身份证长度
        if (!IdcardUtil.isValidCard(order.getPersonId())) {
            throw new GlobalException(GlobalExceptionEnum.PERSON_ID_ERROR);
        }

        // 校验手机号
        if (!VerifyPhone.check(order.getPersonPhone())) {
            throw new GlobalException(GlobalExceptionEnum.PHONE_ERROR);
        }

        // 校验邮箱
        if (!VerifyEmail.verify(order.getPersonEmail())) {
            throw new GlobalException(GlobalExceptionEnum.EMAIL_ERROR);
        }

        // 存在此用户订单
        if (orderResult != null) {
            throw new GlobalException(GlobalExceptionEnum.HAVE_USER);
        }
        // 此房间正在使用
        if (room.getState().equals("use")) {
            throw new GlobalException(GlobalExceptionEnum.ROOM_USR_ERROR);
        }


        //不存在则存入数据库
        Long roomId = Long.valueOf(order.getRoomId().toString());
        // 生成4位数密码
        order.setPassword(RandomUtil.randomString(4));
        // 计算金额
        long betweenDay = DateUtil.between(order.getStartTime(), order.getEndTime(), DateUnit.DAY);
        int roomPrice = Integer.parseInt(roomService.findByRoomId(roomId).getPrice());
        order.setOrderPrice(String.valueOf((betweenDay + 1) * roomPrice));

        // Email 发送密码信息
        MailRequest mailRequest = new MailRequest();
        mailRequest.setSubject("Hotel酒店");
        mailRequest.setSendTo(order.getPersonEmail());
        mailRequest.setContent(order.getPersonName() + " 您好! 欢迎入住Hotel酒店\n 您的账号为您的身份证号，您的密码为：" + order.getPassword() + "\n 酒店用户服务网址：https://singlestep.cn");
        emailService.sendSimpleMail(mailRequest);
        // 更新HotelOrder表
        orderRepository.save(order);

        //更新 Room 表
        HotelOrder savedOrder = orderRepository.findByPersonId(order.getPersonId());
        roomService.updateByRoomId("use", savedOrder.getOrderId(), roomId);

        //更新 History 表
        History history = new History();
        history.setOrderId(savedOrder.getOrderId());
        history.setPersonName(savedOrder.getPersonName());
        history.setPersonSex(savedOrder.getPersonSex());
        history.setPersonPhone(savedOrder.getPersonPhone());
        history.setPersonId(savedOrder.getPersonId());
        history.setPersonEmail(savedOrder.getPersonEmail());
        history.setRoomId(savedOrder.getRoomId());
        history.setPassword(savedOrder.getPassword());
        history.setStartTime(savedOrder.getStartTime());
        history.setEndTime(savedOrder.getEndTime());
        history.setOrderPrice(savedOrder.getOrderPrice());
        history.setAppRaise(null);
        history.setCreateDate(savedOrder.getCreateDate());

        historyService.insert(history);

        return orderRepository.save(savedOrder);
    }

    @Override
    public void delete(Long orderId) {
        // 查询订单所有信息
        HotelOrder order = orderRepository.findById(orderId).orElse(null);
        // 删除订单
        orderRepository.deleteById(orderId);
        // 更新 Room 表
        assert order != null;
        roomService.updateByRoomId("leisure", orderId, order.getRoomId());
        // 更新WorkOrder表
        List<WorkOrder> workOrders = workOrderRepository.finAllByOrderId(orderId);
        if (!workOrders.isEmpty()) {
            System.out.println(" 》 》 》 根据 orderId 删除工单");
            workOrderRepository.deleteByOrderId(orderId);
        }

    }

    @Override
    public HotelOrder updateOrder(HotelOrder order) {
        // 此处insertOrder、updateOrder 都调用了 save 方法
        // 这里四根据若表中若 id 来判断，若存在 id save()方法则更新
        // 若不存在此 id save()方法则插入数据
        return orderRepository.save(order);
    }

    @Override
    public List<HotelOrder> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public HotelOrder findByOrderId(Long orderId) {
        HotelOrder order = orderRepository.findById(orderId).orElse(null);

        if (order == null) {
            throw new GlobalException(GlobalExceptionEnum.HAVE_NO_ORDER_ORDER);
        }

        return order;
    }


    @Override
    public HotelOrder findByPersonId(String personId) {
        //校验身份证长度
        if (!IdcardUtil.isValidCard(personId)) {
            throw new GlobalException(GlobalExceptionEnum.PERSON_ID_ERROR);
        }

        /**
         * 这里不能写查询到为 null 的情况
         * 因为新增Hotel订单的时候也会查询是否存在当前用户订单
         * */

        return orderRepository.findByPersonId(personId);
    }

}

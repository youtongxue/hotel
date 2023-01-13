package cn.singlestep.hotel.service.impl;

import cn.hutool.core.util.IdcardUtil;
import cn.singlestep.hotel.exception.GlobalException;
import cn.singlestep.hotel.exception.GlobalExceptionEnum;
import cn.singlestep.hotel.pojo.entity.History;
import cn.singlestep.hotel.repository.HistoryRepository;
import cn.singlestep.hotel.service.HistoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

/**
 * @author onestep
 * @description 描述
 * @date 2023/1/8 00:03
 */
@Service
public class HistoryImpl implements HistoryService {
    @Resource
    HistoryRepository historyRepository;

    @Override
    public void insert(History history) {
        historyRepository.save(history);
    }

    @Override
    public List<History> findAll() {
        return historyRepository.findAll();
    }

    @Override
    public List<History> findAllByPersonId(String personId) {
        if (!IdcardUtil.isValidCard(personId)) {
            throw new GlobalException(GlobalExceptionEnum.PERSON_ID_ERROR);
        }

        return historyRepository.findAllByPersonId(personId);
    }

    @Override
    public void updateByOrderId(String appRaise, Long orderId) {
        // 更新前现查询是否有此历史订单
        History history = historyRepository.findByOrderId(orderId);
        if (history == null) {
            throw new GlobalException(GlobalExceptionEnum.HAVE_NO_HISTORY_ORDER);
        } else {
            // 更新评价
            historyRepository.updateByOrderId(appRaise, new Date(), orderId);
        }

    }
}

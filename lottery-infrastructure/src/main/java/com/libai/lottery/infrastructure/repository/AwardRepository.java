package com.libai.lottery.infrastructure.repository;

import com.libai.lottery.domain.award.repository.IAwardRepository;
import com.libai.lottery.infrastructure.dao.IUserStrategyExportDao;
import com.libai.lottery.infrastructure.po.UserStrategyExport;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


/**
 * @description: 仓储实现类
 * @author： 有骥子
 * @date: 2023/7/9
 */

@Component
public class AwardRepository implements IAwardRepository {

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Override
    public void updateUserGrantAwardSate(String uId, Long orderId, String awardId, Integer awardState) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setuId(uId);
        userStrategyExport.setOrderId(orderId);
        userStrategyExport.setAwardId(awardId);
        userStrategyExport.setGrantState(awardState);

        userStrategyExportDao.updateUserGrantAwardSate(userStrategyExport);
    }
}

package com.libai.lottery.domain.award.service.goods;

import com.libai.lottery.domain.award.repository.IAwardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @description: 配送奖品基础类
 * @author： 有骥子
 * @date: 2023/7/9
 */
public class DistributionBase {

    protected Logger logger = LoggerFactory.getLogger(DistributionBase.class);

    @Resource
    IAwardRepository awardRepository;

    protected void updateUserAwardSate(String uId, Long orderId, String awardId, Integer awardState) {
        awardRepository.updateUserGrantAwardSate(uId, orderId, awardId, awardState);
        logger.info("用户个人的抽奖记录表中奖品发奖状态 uId：{}", uId);
    }
}

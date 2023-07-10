package com.libai.lottery.domain.strategy.service.draw;

import com.libai.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.libai.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.libai.lottery.domain.strategy.repository.IStrategyRepository;

import javax.annotation.Resource;

/**
 * @description: 抽奖策略数据支撑：主要是给抽象类做一些通用的数据服务
 * @author： 有骥子
 * @date: 2023/7/5
 */
public class DrawStrategySupport extends DrawConfig{

    @Resource
    protected IStrategyRepository strategyRepository;


    /**
     * 查询策略配置信息
     * @param strategyId  策略ID
     * @return 策略配置信息
     */
    protected StrategyRich queryStrategyRich(Long strategyId) {
        return strategyRepository.queryStrategyRich(strategyId);
    }

    /**
     * 查询奖品详细信息
     * @param awardId 奖品ID
     * @return 奖品信息
     */
    protected AwardBriefVO queryAwardInfo(String awardId) {
        return strategyRepository.queryAwardInfo(awardId);
    }

}

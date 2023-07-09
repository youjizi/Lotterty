package com.libai.lottery.domain.strategy.repository;

import com.libai.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.libai.lottery.infrastructure.po.Award;

import java.util.List;

/**
 * @description: 策略仓储接口
 * @author： 有骥子
 * @date: 2023/7/3
 */
public interface IStrategyRepository {

    /**
     * 查询策略数据集合
     * @param strategyId 策略ID
     * @return 结果
     */
    StrategyRich queryStrategyRich(Long strategyId);

    /**
     * 查询奖品信息
     * @param awardId 奖品ID
     * @return 返回奖品信息
     */
    Award queryAwardInfo(String awardId);

    /**
     * 查询库存不足的奖品
     * @param strategyId  策略ID
     * @return 库存集合
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyId 策略ID
     * @param awardId    奖品ID
     * @return           扣减结果
     */
    boolean deductStock(Long strategyId, String awardId);
}

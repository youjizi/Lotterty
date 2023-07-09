package com.libai.lottery.infrastructure.dao;


import com.libai.lottery.infrastructure.po.StrategyDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 策略详细接口
 * @author： 有骥子
 * @date: 2023/6/28
 */
@Mapper
public interface IStrategyDetailDao {


    /**
     * 查询策略详情
     * @param strategyId 策略ID
     * @return 结果
     */
    List<StrategyDetail> queryStrategyDetailList(Long strategyId);

    /**
     * 查询库存不足的奖品
     * @param strategyId  策略ID
     * @return 库存集合
     */
    List<String> queryNoStockStrategyAwardList(Long strategyId);

    /**
     * 扣减库存
     * @param strategyDetail 策略详细
     * @return           扣减结果
     */
    int deductStock(StrategyDetail strategyDetail);
}

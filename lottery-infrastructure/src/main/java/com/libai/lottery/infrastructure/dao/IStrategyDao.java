package com.libai.lottery.infrastructure.dao;


import com.libai.lottery.infrastructure.po.Strategy;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 策略接口
 * @author： 有骥子
 * @date: 2023/6/28
 */
@Mapper
public interface IStrategyDao {


    /**
     * 查询策略
     * @param strategyId 策略ID
     * @return 结果
     */
    Strategy queryStrategyById(Long strategyId);

}

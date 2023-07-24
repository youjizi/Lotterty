package com.libai.lottery.domain.strategy.service.algorithm;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.strategy.model.vo.AwardRateInfoVO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 共用的算法逻辑
 * @author： 有骥子
 * @date: 2023/7/2
 */
public abstract class BaseAlgorithm implements IDrawAlgorithm{

    /**
     *斐波那契散列增量，逻辑：黄金分割点：(√5 - 1) / 2 = 0.6180339887，Math.pow(2, 32) * 0.6180339887 = 0x61c88647
     */
    private final int HASH_INCREMENT = 0x61c88647;

    /**
     * 数组初始化长度
     */
    private final int RATE_TUPLE_LENGTH = 128;

    /**
     * 存放概率与奖品对应的散列结果，strategyId -> rateTuple
     */
    protected Map<Long, String[]> rateTupleMap = new ConcurrentHashMap<>();

    /**
     * 奖品区间概率值
     */
    protected Map<Long, List<AwardRateInfoVO>> awardRateInfoMap = new ConcurrentHashMap<>();

    @Override
    public void initRateTuple(Long strategyId, Integer strategyMode, List<AwardRateInfoVO> awardRateInfoVOList) {
        // 保存奖品概率信息
        awardRateInfoMap.put(strategyId, awardRateInfoVOList);

        // 不是单体概率则不需要初始化
        if (!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) {
            return;
        }

        String[] rateTuple = rateTupleMap.computeIfAbsent(strategyId, k -> new String[RATE_TUPLE_LENGTH]);
        // 关键变量，避免重复区间
        int cursorVal = 0;
        for (AwardRateInfoVO awardRateInfoVO : awardRateInfoVOList) {
            int rateVal = awardRateInfoVO.getAwardRate().multiply(new BigDecimal(100)).intValue();
            // 循环填充概率范围值
            for (int i = cursorVal + 1; i <= (rateVal + cursorVal); i++) {
                rateTuple[hashIdx(i)] = awardRateInfoVO.getAwardId();
            }
            cursorVal += rateVal;
        }

    }

    @Override
    public boolean isExistAwardRateInfoMap(Long strategyId) {
        return awardRateInfoMap.containsKey(strategyId);
    }

    /**
     * 斐波那契散列法，计算哈希索引下标值，减少hash碰撞，使奖品能充分的分布
     * @param val 值
     * @return 索引
     */
    protected int hashIdx(int val) {
        int hashCode = val * HASH_INCREMENT + HASH_INCREMENT;
        return hashCode & (RATE_TUPLE_LENGTH - 1);
    }
}

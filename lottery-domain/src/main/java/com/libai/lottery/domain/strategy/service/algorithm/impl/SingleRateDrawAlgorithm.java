package com.libai.lottery.domain.strategy.service.algorithm.impl;

import com.libai.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.List;

/**
 * @description: 单向随机概率抽奖，抽到已经排除掉的奖品则未中奖
 * @author： 有骥子
 * @date: 2023/7/2
 */

@Component("singleRateDrawAlgorithm")
public class SingleRateDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {

        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        // 随机索引
        int idx = super.hashIdx(new SecureRandom().nextInt(100) + 1);

        //f 返回结果
        String awardId = rateTuple[idx];
        if (excludeAwardIds.contains(awardId)) {
            return "未中奖";
        }
        return awardId;
    }
}

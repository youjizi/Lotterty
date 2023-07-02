package com.libai.lottery.domain.strategy.service.algorithm.impl;

import com.libai.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

/**
 * @description: 随机概率抽奖
 * @author： 有骥子
 * @date: 2023/7/2
 */

@Component("randomDrawAlgorithm")
public class RandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId) {

        String[] rateTuple = super.rateTupleMap.get(strategyId);
        assert rateTuple != null;

        // 随机索引
        int idx = super.hashIdx(new SecureRandom().nextInt(100) + 1);
        //f 返回结果
        return rateTuple[idx];
    }
}

package com.libai.lottery.domain.strategy.service.draw;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 抽奖策略配置
 * @author： 有骥子
 * @date: 2023/7/3
 */
public class DrawConfig {


    @Resource
    private IDrawAlgorithm singleRateDrawAlgorithm;

    @Resource
    private IDrawAlgorithm entiretyRateRandomDrawAlgorithm;

    protected static Map<Integer, IDrawAlgorithm> drawAlgorithmMap = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        drawAlgorithmMap.put(Constants.StrategyMode.SINGLE.getCode(), singleRateDrawAlgorithm);
        drawAlgorithmMap.put(Constants.StrategyMode.ENTIRETY.getCode(), entiretyRateRandomDrawAlgorithm);
    }
}

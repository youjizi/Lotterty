package com.libai.lottery.domain.strategy.service.draw;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.strategy.model.vo.AwardRateInfo;
import com.libai.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.libai.lottery.infrastructure.po.StrategyDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 抽奖基础类
 * @author： 有骥子
 * @date: 2023/7/3
 */
public class DrawBase extends DrawConfig{

    public void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetail> strategyDetailList) {

        // 不是单体概率则不需要初始化
        if (!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) {
            return;
        }
        IDrawAlgorithm algorithm = drawAlgorithmMap.get(strategyMode);

        // 判断是否已经初始化
        if (algorithm.isExistRateTuple(strategyId)) {
            return;
        }
        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetail strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }
        algorithm.initRateTuple(strategyId, awardRateInfoList);

    }

}

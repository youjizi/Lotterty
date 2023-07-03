package com.libai.lottery.domain.strategy.service.draw.impl;

import com.libai.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.libai.lottery.domain.strategy.model.req.DrawReq;
import com.libai.lottery.domain.strategy.model.res.DrawResult;
import com.libai.lottery.domain.strategy.repository.IStrategyRepository;
import com.libai.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.libai.lottery.domain.strategy.service.draw.DrawBase;
import com.libai.lottery.domain.strategy.service.draw.IDrawExec;
import com.libai.lottery.infrastructure.po.Award;
import com.libai.lottery.infrastructure.po.Strategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * @description: 抽奖模板
 * @author： 有骥子
 * @date: 2023/7/3
 */

@Service("drawExec")
public class DrawExeImpl extends DrawBase implements IDrawExec {

    private Logger logger = LoggerFactory.getLogger(DrawExeImpl.class);

    @Resource
    private IStrategyRepository strategyRepository;

    @Override
    public DrawResult doDrawExec(DrawReq req) {

        logger.info("执行策略抽奖开始, strategyId: {}", req.getStrategyId());

        // 获取抽奖策略配置数据
        StrategyRich strategyRich = strategyRepository.queryStrategyRich(req.getStrategyId());
        Strategy strategy = strategyRich.getStrategy();

        // 校验和初始化数据
        checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(),strategyRich.getStrategyDetailList());

        // 根据策略方式抽奖
        IDrawAlgorithm algorithm = drawAlgorithmMap.get(strategy.getStrategyMode());
        // TODO 排除数据暂时为空， 后序补充
        String awardId = algorithm.randomDraw(req.getStrategyId(), new ArrayList<>());

        // 获取奖品信息
        Award award = strategyRepository.queryAwardInfo(awardId);

        logger.info("策略抽奖完成，中奖用户：{} 奖品ID：{}  奖品名称：{}", req.getuId(), awardId, award.getAwardName());

        // 返回封装结果
        return new DrawResult(req.getuId(), req.getStrategyId(), awardId, award.getAwardName());
    }
}

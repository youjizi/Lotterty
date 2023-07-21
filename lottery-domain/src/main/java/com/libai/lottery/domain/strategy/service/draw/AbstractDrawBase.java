package com.libai.lottery.domain.strategy.service.draw;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.libai.lottery.domain.strategy.model.req.DrawReq;
import com.libai.lottery.domain.strategy.model.res.DrawResult;
import com.libai.lottery.domain.strategy.model.vo.*;
import com.libai.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 抽奖基础类
 * @author： 有骥子
 * @date: 2023/7/3
 */
public abstract class AbstractDrawBase extends DrawStrategySupport implements IDrawExec{


    private final Logger logger = LoggerFactory.getLogger(AbstractDrawBase.class);

    @Override
    public DrawResult doDrawExec(DrawReq req) {

        logger.info("执行策略抽奖开始, strategyId: {}", req.getStrategyId());

        // 1.获取抽奖策略配置数据
        StrategyRich strategyRich = super.queryStrategyRich(req.getStrategyId());
        StrategyBriefVO strategy = strategyRich.getStrategy();

        // 2.校验和初始化数据
        checkAndInitRateData(req.getStrategyId(), strategy.getStrategyMode(),strategyRich.getStrategyDetailList());


        // 3.获取不在抽奖范围内的列表，包括：奖品库存为空，风险策略、临时调整等
        List<String> excludeAwardIds = this.queryExcludeAwardIds(req.getStrategyId());

        // 4.根据策略方式抽奖
        String awardId = this.drawAlgorithm(req.getStrategyId(), drawAlgorithmMap.get(strategy.getStrategyMode()), excludeAwardIds);


        // 包装结果
        return buildDrawResult(req.getuId(), req.getStrategyId(), awardId, strategy);
    }


    /**
     * 获取不在抽奖范围内的列表，包括：奖品库存为空，风险策略、临时调整等
     * @param strategyId 策略ID
     * @return 返回排除奖品ID列表
     */
    protected abstract List<String> queryExcludeAwardIds(Long strategyId);

    /**
     * 进行抽奖
     * @param strategyId 策略ID
     * @param algorithm 算法策略类型
     * @param excludeAwardIds 排除奖品列表
     * @return 奖品ID
     */
    protected abstract String drawAlgorithm(Long strategyId, IDrawAlgorithm algorithm, List<String> excludeAwardIds);

    /**
     * 校验抽奖策略是否已经初始化内存
     * @param strategyId 抽奖策略ID
     * @param strategyMode 抽奖策略模式
     * @param strategyDetailList 抽奖策略详情
     */
    private void checkAndInitRateData(Long strategyId, Integer strategyMode, List<StrategyDetailBriefVO> strategyDetailList) {

        // 不是单体概率则不需要初始化
//        if (!Constants.StrategyMode.SINGLE.getCode().equals(strategyMode)) {
//            return;
//        }
        IDrawAlgorithm algorithm = drawAlgorithmMap.get(strategyMode);

        // 判断是否已经初始化
        if (algorithm.isExistRateTuple(strategyId)) {
            return;
        }
        List<AwardRateInfo> awardRateInfoList = new ArrayList<>(strategyDetailList.size());
        for (StrategyDetailBriefVO strategyDetail : strategyDetailList) {
            awardRateInfoList.add(new AwardRateInfo(strategyDetail.getAwardId(), strategyDetail.getAwardRate()));
        }
        algorithm.initRateTuple(strategyId, awardRateInfoList);

    }

    /**
     * 包装中奖信息
     * @param uid 用户ID
     * @param strategyId 策略ID
     * @param awardId 奖品ID
     * @return 中奖信息
     */
    private DrawResult buildDrawResult(String uid, Long strategyId, String awardId, StrategyBriefVO strategyBriefVO) {
        if (null == awardId) {
            logger.info(" 执行抽奖完成【未中奖】, 用户：{} 策略ID：{}", uid, strategyId);
            return new DrawResult(uid, strategyId, Constants.DrawState.FAIL.getCode());
        }

        // 获取奖品信息
        AwardBriefVO award = super.queryAwardInfo(awardId);
        DrawAwardInfo drawAwardInfo = new DrawAwardInfo(award.getAwardId(), award.getAwardName(), award.getAwardType(), award.getAwardContent(), strategyBriefVO.getStrategyMode(), strategyBriefVO.getGrantType(), strategyBriefVO.getGrantDate());
        logger.info("执行抽奖完成【已中奖】, 用户：{} 策略ID：{} 奖品ID：{} 奖品名称：{} 奖品类型： {} 奖品描述： {}", uid, strategyId, awardId, drawAwardInfo.getAwardName(), drawAwardInfo.getAwardType(), drawAwardInfo.getAwardContent());
        return new DrawResult(uid, strategyId, Constants.DrawState.SUCCESS.getCode(), drawAwardInfo);

    }

}

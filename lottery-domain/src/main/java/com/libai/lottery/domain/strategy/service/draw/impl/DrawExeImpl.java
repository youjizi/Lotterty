package com.libai.lottery.domain.strategy.service.draw.impl;

import com.alibaba.fastjson.JSON;
import com.libai.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import com.libai.lottery.domain.strategy.service.draw.AbstractDrawBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @description: 抽奖模板
 * @author： 有骥子
 * @date: 2023/7/3
 */

@Service("drawExec")
public class DrawExeImpl extends AbstractDrawBase {

    private final Logger logger = LoggerFactory.getLogger(DrawExeImpl.class);


    @Override
    protected List<String> queryExcludeAwardIds(Long strategyId) {
         List<String> awardList = strategyRepository.queryNoStockStrategyAwardList(strategyId);
         logger.info("执行抽奖策略 strategyId：{}  无库存排除奖品ID集合 awardList：{}", strategyId, JSON.toJSONString(awardList));
        return awardList;
    }

    @Override
    protected String drawAlgorithm(Long strategyId, IDrawAlgorithm algorithm, List<String> excludeAwardIds) {

        // 执行抽奖
        String awardId = algorithm.randomDraw(strategyId, excludeAwardIds);

        // 判断抽奖结果
        if (null == awardId) {
            return null;
        }

        /*TODO
         * 扣减库存，暂时采用数据库行级锁的方式进行扣减库存，后续优化为 Redis 分布式锁扣减 decr/incr
         * 注意：通常数据库直接锁行记录的方式并不能支撑较大体量的并发，但此种方式需要了解，
         * 因为在分库分表下的正常数据流量下的个人数据记录中，是可以使用行级锁的，因为他只影响到自己的记录，不会影响到其他人
         */
        boolean isSuccess = strategyRepository.deductStock(strategyId, awardId);

        // 返回结果，库存扣减成功返回奖品ID，否则返回NULL 「在实际的业务场景中，如果中奖奖品库存为空，则会发送兜底奖品，比如各类券」
        return isSuccess ? awardId : null;
    }
}

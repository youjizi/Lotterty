package com.libai.lottery.test.domain.strategy.draw;

import com.alibaba.fastjson.JSON;
import com.libai.lottery.domain.award.model.req.GoodsReq;
import com.libai.lottery.domain.award.model.res.DistributionRes;
import com.libai.lottery.domain.award.service.factory.DistributionGoodsFactory;
import com.libai.lottery.domain.award.service.goods.IDistributionGoods;
import com.libai.lottery.domain.strategy.model.req.DrawReq;
import com.libai.lottery.domain.strategy.model.res.DrawResult;
import com.libai.lottery.domain.strategy.model.vo.DrawAwardInfoVO;
import com.libai.lottery.domain.strategy.service.draw.IDrawExec;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description: 抽奖流程测试
 * @author: 有骥子
 * @date: 2023/7/3
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class drawTest {

    Logger logger = LoggerFactory.getLogger(drawTest.class);


    @Resource
    IDrawExec drawExec;

    @Resource
    DistributionGoodsFactory factory;

    @Test
    public void drawing() throws InterruptedException {


        DrawResult result= drawExec.doDrawExec(new DrawReq("李白", 10001L));
        logger.info(JSON.toJSONString(result.getDrawAwardInfo()));

        DrawAwardInfoVO drawAwardInfoVO = result.getDrawAwardInfo();
        IDistributionGoods distributionGoodsService = factory.getDistributionGoodsService(drawAwardInfoVO.getAwardType());
        DistributionRes order123 = distributionGoodsService.doDistribution(new GoodsReq(result.getuId(), 123L, drawAwardInfoVO.getAwardId(), drawAwardInfoVO.getAwardName(), drawAwardInfoVO.getAwardContent()));
        logger.info(JSON.toJSONString(order123));


    }
}

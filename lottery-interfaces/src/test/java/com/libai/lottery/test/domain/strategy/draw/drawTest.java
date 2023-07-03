package com.libai.lottery.test.domain.strategy.draw;

import com.alibaba.fastjson.JSON;
import com.libai.lottery.domain.strategy.model.req.DrawReq;
import com.libai.lottery.domain.strategy.model.res.DrawResult;
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

    @Test
    public void drawing() throws InterruptedException {
        for (int i = 0; i < 20; i++) {

            DrawResult result= drawExec.doDrawExec(new DrawReq("李白", 10001L));
            logger.info(i + JSON.toJSONString(result.getAwardName()));
            Thread.sleep(200L);
        }

    }
}

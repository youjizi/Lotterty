package com.libai.lottery.test.interfaces;


import com.alibaba.fastjson.JSON;
import libai.lottery.rpc.ILotteryActivityBooth;
import libai.lottery.rpc.req.DrawReq;
import libai.lottery.rpc.req.QuantificationDrawReq;
import libai.lottery.rpc.res.DrawRes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @description:
 * @author： 有骥子
 * @date: 2023/7/24
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class FacadeTest {

    private Logger logger = LoggerFactory.getLogger(FacadeTest.class);

    @Resource
    ILotteryActivityBooth lotteryActivityBooth;

    @Test
    public void test_doDraw() {
        DrawReq drawReq = new DrawReq();
        drawReq.setuId("xiaofuge");
        drawReq.setActivityId(100001L);
        DrawRes drawRes = lotteryActivityBooth.doDraw(drawReq);
        logger.info("请求参数：{}", JSON.toJSONString(drawReq));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));
    }

    @Test
    public void test_doQuantificationDraw() {
        QuantificationDrawReq req = new QuantificationDrawReq();
        req.setuId("xiaofuge");
        req.setTreeId(2110081902L);
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "18");
        }});
        DrawRes drawRes = lotteryActivityBooth.doQuantificationDraw(req);
        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(drawRes));
    }



}

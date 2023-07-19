package com.libai.lottery.test.domain.strategy.activity;

import com.alibaba.fastjson.JSON;
import com.libai.lottery.common.Constants;
import com.libai.lottery.common.Result;
import com.libai.lottery.domain.activity.service.stateflow.IStateHandler;
import com.libai.lottery.test.domain.strategy.draw.drawTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description: 状态流转测试类
 * @author： 有骥子
 * @date: 2023/7/11
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class stateTest {


    Logger logger = LoggerFactory.getLogger(drawTest.class);

    @Resource
    IStateHandler stateHandler;

    @Test
    public void stateFlow()  {
//        Result pass = stateHandler.checkPass(100001L, Constants.ActivityState.ARRAIGNMENT);
//        logger.info(JSON.toJSONString(pass));
//
//        Result doing = stateHandler.doing(100001L, Constants.ActivityState.PASS);
//        logger.info(JSON.toJSONString(doing));

        Result close = stateHandler.close(100001L, Constants.ActivityState.DOING);
        logger.info(JSON.toJSONString(close));
    }

}

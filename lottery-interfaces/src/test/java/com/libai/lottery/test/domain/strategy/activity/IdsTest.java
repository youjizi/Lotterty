package com.libai.lottery.test.domain.strategy.activity;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.support.ids.IIdGenerator;
import com.libai.lottery.test.domain.strategy.draw.drawTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: id生成策略测试
 * @author： 有骥子
 * @date: 2023/7/13
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class IdsTest {

    Logger logger = LoggerFactory.getLogger(drawTest.class);

    @Resource
    Map<Constants.IDs, IIdGenerator> idGenerator;

    @Test
    public void stateFlow()  {
        IIdGenerator iIdGenerator = idGenerator.get(Constants.IDs.SnowFlake);
        long l = iIdGenerator.nextId();
        logger.info(String.valueOf(l));

    }
}

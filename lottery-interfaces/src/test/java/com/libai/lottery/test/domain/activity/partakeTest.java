package com.libai.lottery.test.domain.activity;

import com.alibaba.fastjson.JSON;
import com.libai.lottery.domain.activity.model.req.PartakeReq;
import com.libai.lottery.domain.activity.model.res.PartakeResult;
import com.libai.lottery.domain.activity.service.partake.IActivityPartake;
import com.libai.lottery.test.domain.strategy.draw.drawTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:
 * @author： 有骥子
 * @date: 2023/7/17
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class partakeTest {

    Logger logger = LoggerFactory.getLogger(drawTest.class);

    @Resource
    IActivityPartake activityPartake;

    @Test
    public void partake()  {
        PartakeReq partakeReq = new PartakeReq("xiaohei", 100001L, new Date());

        PartakeResult partakeResult = activityPartake.doPartake(partakeReq);
        logger.info(JSON.toJSONString(partakeResult));
    }
}

package com.libai.lottery.test.application;

import com.libai.lottery.process.IActivityProcess;
import com.libai.lottery.process.req.DrawProcessReq;
import com.libai.lottery.process.res.DrawProcessResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @description:
 * @author： 有骥子
 * @date: 2023/7/20
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessTest {

    Logger logger = LoggerFactory.getLogger(ProcessTest.class);

    @Resource
    IActivityProcess activityProcess;

    @Test
    public void test() throws InterruptedException {

        while (true) {

            DrawProcessResult result = activityProcess.doDrawProcess(new DrawProcessReq("laidehua", 100001L));
//            Thread.sleep(5000L);
            logger.info(result.toString());
        }
    }
}

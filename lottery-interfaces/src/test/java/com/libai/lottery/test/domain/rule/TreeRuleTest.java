package com.libai.lottery.test.domain.rule;

import com.alibaba.fastjson.JSON;
import com.libai.lottery.domain.rule.model.req.DecisionMatterReq;
import com.libai.lottery.domain.rule.model.res.EngineResult;
import com.libai.lottery.domain.rule.service.engine.EngineFilter;
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
 * @date: 2023/7/22
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TreeRuleTest {

    Logger logger = LoggerFactory.getLogger(TreeRuleTest.class);

    @Resource
    private EngineFilter ruleEngineHandle;

    @Test
    public void RuleTest() {
        DecisionMatterReq req = new DecisionMatterReq();
        req.setTreeId(2110081902L);
        req.setUserId("fustack");
        req.setValMap(new HashMap<String, Object>() {{
            put("gender", "man");
            put("age", "25");
        }});

        EngineResult res = ruleEngineHandle.process(req);

        logger.info("请求参数：{}", JSON.toJSONString(req));
        logger.info("测试结果：{}", JSON.toJSONString(res));

    }
}

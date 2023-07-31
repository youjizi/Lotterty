package com.libai.lottery.test.dao;

import com.alibaba.fastjson.JSON;
import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.support.ids.IIdGenerator;
import com.libai.lottery.infrastructure.dao.IUserStrategyExportDao;
import com.libai.lottery.infrastructure.po.UserStrategyExport;
import com.libai.lottery.infrastructure.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author： 有骥子
 * @date: 2023/7/17
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserStrategyExportDaoTest {


    private Logger logger = LoggerFactory.getLogger(UserStrategyExportDaoTest.class);

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Resource
    private RedisUtil redisUtil;

    @Test
    public void test_insert() {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        userStrategyExport.setuId("Uhdgkw766120d");
        userStrategyExport.setActivityId(idGeneratorMap.get(Constants.Ids.ShortCode).nextId());
        userStrategyExport.setOrderId(idGeneratorMap.get(Constants.Ids.SnowFlake).nextId());
        userStrategyExport.setStrategyId(idGeneratorMap.get(Constants.Ids.RandomNumeric).nextId());
        userStrategyExport.setStrategyMode(Constants.StrategyMode.SINGLE.getCode());
        userStrategyExport.setGrantType(1);
        userStrategyExport.setGrantDate(new Date());
        userStrategyExport.setGrantState(1);
        userStrategyExport.setAwardId("1");
        userStrategyExport.setAwardType(Constants.AwardType.DESC.getCode());
        userStrategyExport.setAwardName("超级IP");
        userStrategyExport.setAwardContent("奖品描述");
        userStrategyExport.setUuid(String.valueOf(userStrategyExport.getOrderId()));
        userStrategyExport.setMqState(0);
        userStrategyExportDao.insert(userStrategyExport);
    }

    @Test
    public void test_select() {
        List<UserStrategyExport> uhdgkw766120d = userStrategyExportDao.queryUserStrategyExportByUId("Uhdgkw766120d");
        logger.info("测试结果：{}", JSON.toJSONString(uhdgkw766120d));
    }


    @Test
    public void test_set() {
        redisUtil.set("lottery_user_key","11111");
    }



    @Test
    public void test_get() {
        Object user = redisUtil.get("lottery_user_key");
        logger.info("测试结果：{}", JSON.toJSONString(user));
    }


}

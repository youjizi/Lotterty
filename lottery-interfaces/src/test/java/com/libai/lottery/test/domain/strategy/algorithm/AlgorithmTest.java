package com.libai.lottery.test.domain.strategy.algorithm;

import com.libai.lottery.domain.strategy.model.vo.AwardRateInfoVO;
import com.libai.lottery.domain.strategy.service.algorithm.IDrawAlgorithm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 抽奖策略测试
 * @author： 有骥子
 * @date: 2023/7/1
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AlgorithmTest {


    @Resource
    IDrawAlgorithm defaultRateRandomDrawAlgorithm;


    @Test
    public void test_strategy() {
//        SecureRandom random = new SecureRandom();
//        int rate = random.nextInt(1);

//        System.out.println("概率：" + rate);

        double num = Math.pow(2, 32) * 0.6180339887;
        System.out.println("num:"+ num);

        BigDecimal bigDecimal = BigDecimal.valueOf(Math.pow(2, 32) * 0.6180339887);
        System.out.println("bigDecimal:"+ bigDecimal);

        System.out.println("num 变成 int类型：" + (int)num);
        System.out.println("bigDecimal 变成 int类型：" + bigDecimal.intValue());

    }

    @Test
    public void test_idx() {

        Map<Integer, Integer> map = new HashMap<>();

        int HASH_INCREMENT = 0x61c88647;
        int hashCode = 0;
        for (int i = 1; i <= 100; i++) {
            hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
            int idx = hashCode & (128 - 1);

            int i1 = String.valueOf(i).hashCode() & (128 - 1);
            System.out.println("斐波那契散列：" + idx + " 普通散列：" + i1);
            map.merge(i1, 1, Integer::sum);

        }

        System.out.println(map);
    }


    @Test
    public void test_DrawStrategy() {
        List<AwardRateInfoVO> strategyList = new ArrayList<>();
//        strategyList.add(new AwardRateInfo("特等奖：100W", new BigDecimal("0.02")));
//        strategyList.add(new AwardRateInfo("一等奖：彩电", new BigDecimal("0.08")));
//        strategyList.add(new AwardRateInfo("二等奖：树苗", new BigDecimal("0.3")));
//        strategyList.add(new AwardRateInfo("三等奖：谢谢回顾", new BigDecimal("0.5")));

        ArrayList<String> excludeAwardIds = new ArrayList<>();
        excludeAwardIds.add("特等奖：100W");
        defaultRateRandomDrawAlgorithm.initRateTuple(100001L, strategyList);
        String result = defaultRateRandomDrawAlgorithm.randomDraw(100001L,excludeAwardIds );
        System.out.println(result);
    }

}

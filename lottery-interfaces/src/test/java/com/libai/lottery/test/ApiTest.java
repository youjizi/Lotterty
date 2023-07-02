package com.libai.lottery.test;

import com.libai.lottery.domain.strategy.service.DrawStrategy;
import org.junit.Test;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 抽奖策略测试
 * @author： 有骥子
 * @date: 2023/7/1
 */
public class ApiTest {


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

        List<Map<String, String>> strategyList = new ArrayList<>();

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "一等奖：彩电");
            put("awardId", "10001");
            put("awardCount", "3");
            put("awardRate", "5");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "二等奖：冰箱");
            put("awardId", "10002");
            put("awardCount", "5");
            put("awardRate", "10");
        }});

        strategyList.add(new HashMap<String, String>() {{
            put("awardDesc", "谢谢惠顾");
            put("awardId", "10003");
            put("awardCount", "10");
            put("awardRate", "85");
        }});

        DrawStrategy drawStrategy = new DrawStrategy();
        drawStrategy.initRateTuple(strategyList);

        SecureRandom random = new SecureRandom();

//        for (int i = 0; i < 20; i++) {
//            System.out.println("中奖结果：" + drawStrategy.randomDraw(random.nextInt(100) + 1));
//        }
        System.out.println("中奖结果：" + drawStrategy.randomDraw(1));
        System.out.println("中奖结果：" + drawStrategy.randomDraw(2));
        System.out.println("中奖结果：" + drawStrategy.randomDraw(3));
        System.out.println("中奖结果：" + drawStrategy.randomDraw(4));
        System.out.println("中奖结果：" + drawStrategy.randomDraw(5));

    }

}

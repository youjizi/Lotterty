package com.libai.lottery.domain.strategy.service;

import java.util.List;
import java.util.Map;

/**
 * @description: 抽奖
 * @author： 有骥子
 * @date: 2023/7/1
 */
public class DrawStrategy {

    private final int HASH_INCREMENT = 0x61c88647;

    private String[] rateTuple = new String[128];

    public void initRateTuple(List<Map<String, String>> drawConfig) {
        int  cursorVal = 0;
        for (Map<String, String> drawMap : drawConfig) {
            int rateVal = Integer.parseInt(drawMap.get("awardRate"));

            for (int i = cursorVal + 1; i <= (rateVal + cursorVal); i++) {
                // 计算数组索引并填充数据
                int hashCode = i * HASH_INCREMENT + HASH_INCREMENT;
                int idx = hashCode & (rateTuple.length - 1);
                rateTuple[idx] = drawMap.get("awardDesc");
            }
            cursorVal += rateVal;
        }
    }

    public String randomDraw(int rate) {
        // 计算数组索引并填充数据
        int hashCode = rate * HASH_INCREMENT + HASH_INCREMENT;
        int idx = hashCode & (rateTuple.length - 1);
        return rateTuple[idx];
    }
}

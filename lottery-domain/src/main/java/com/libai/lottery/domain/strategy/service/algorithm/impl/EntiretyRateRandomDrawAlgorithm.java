package com.libai.lottery.domain.strategy.service.algorithm.impl;

import com.libai.lottery.domain.strategy.model.vo.AwardRateInfoVO;
import com.libai.lottery.domain.strategy.service.algorithm.BaseAlgorithm;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 总体概率，必中奖策略，排除掉已经中奖的奖品概率，重新计算概率
 * @author： 有骥子
 * @date: 2023/7/2
 */
@Component("entiretyRateRandomDrawAlgorithm")
public class EntiretyRateRandomDrawAlgorithm extends BaseAlgorithm {

    @Override
    public String randomDraw(Long strategyId, List<String> excludeAwardIds) {
        // 重要参数： 叠加不排除的奖品概率获得总概率
        BigDecimal differenceDenominator = BigDecimal.ZERO;

        // 排除掉不在抽奖范围的奖品ID集合
        ArrayList<AwardRateInfoVO> differenceAwardRateList = new ArrayList<>();
        List<AwardRateInfoVO> awardRateIntervalValList = awardRateInfoMap.get(strategyId);
        for (AwardRateInfoVO awardRateInfoVO : awardRateIntervalValList) {
            String awardId = awardRateInfoVO.getAwardId();
            if (excludeAwardIds.contains(awardId)) {
                continue;
            }
            differenceAwardRateList.add(awardRateInfoVO);
            differenceDenominator = differenceDenominator.add(awardRateInfoVO.getAwardRate());
        }

        // 前置判断
        if (differenceAwardRateList.size() == 0) {
            return "";
        }
        if (differenceAwardRateList.size() == 1) {
            return differenceAwardRateList.get(0).getAwardId();
        }

        //  获取随机概率值
        SecureRandom secureRandom = new SecureRandom();
        int randomVal = secureRandom.nextInt(100) + 1;

        // 循环获取奖品
        String awardId = "";
        int cursorVal = 0;
        for (AwardRateInfoVO awardRateInfoVO : differenceAwardRateList) {
            //  当前奖品概率/总概率  mode： 最后一位加1
            int rateVal = awardRateInfoVO.getAwardRate().divide(differenceDenominator, 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100)).intValue();
            // 当随机数小于区间end，则抽奖成功
            if (randomVal <= (cursorVal + rateVal)) {
                awardId = awardRateInfoVO.getAwardId();
                break;
            }
            cursorVal += rateVal;
        }

        // 返回中奖结果
        return awardId;
    }
}

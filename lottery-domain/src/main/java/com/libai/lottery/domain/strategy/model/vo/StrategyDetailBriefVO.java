package com.libai.lottery.domain.strategy.model.vo;

import java.math.BigDecimal;

/**
 * @description: 策略详情简要信息
 * @author： 有骥子
 * @date: 2023/7/10
 */
public class StrategyDetailBriefVO {

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 奖品ID
     */
    private String awardId;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品库奖品库存
     */
    private String awardCount;

    /**
     * 奖品剩余库存
     */
    private String awardSurplusCount;

    /**
     * 中奖概率
     */
    private BigDecimal awardRate;


    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }

    public String getAwardCount() {
        return awardCount;
    }

    public void setAwardCount(String awardCount) {
        this.awardCount = awardCount;
    }

    public String getAwardSurplusCount() {
        return awardSurplusCount;
    }

    public void setAwardSurplusCount(String awardSurplusCount) {
        this.awardSurplusCount = awardSurplusCount;
    }

    public BigDecimal getAwardRate() {
        return awardRate;
    }

    public void setAwardRate(BigDecimal awardRate) {
        this.awardRate = awardRate;
    }
}

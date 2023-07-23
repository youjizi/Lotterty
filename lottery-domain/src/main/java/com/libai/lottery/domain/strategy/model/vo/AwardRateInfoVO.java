package com.libai.lottery.domain.strategy.model.vo;

import java.math.BigDecimal;

/**
 * @description: 奖品编号、奖品概率信息
 * @author： 有骥子
 * @date: 2023/7/2
 */
public class AwardRateInfoVO {

    /**
     * 奖品ID
     */
    private String awardId;

    /**
     * 中奖概率
     */
    private BigDecimal awardRate;


    public AwardRateInfoVO() {
    }

    public AwardRateInfoVO(String awardId, BigDecimal awardRate) {
        this.awardId = awardId;
        this.awardRate = awardRate;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public BigDecimal getAwardRate() {
        return awardRate;
    }

    public void setAwardRate(BigDecimal awardRate) {
        this.awardRate = awardRate;
    }
}

package com.libai.lottery.domain.strategy.model.res;

/**
 * @description: 抽奖结果
 * @author： 有骥子
 * @date: 2023/7/2
 */
public class DrawResult {

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 抽奖策略ID
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

    public DrawResult() {
    }

    public DrawResult(String uId, Long strategyId, String awardId, String awardName) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.awardId = awardId;
        this.awardName = awardName;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

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
}

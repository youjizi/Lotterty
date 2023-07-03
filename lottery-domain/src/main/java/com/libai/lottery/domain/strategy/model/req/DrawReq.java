package com.libai.lottery.domain.strategy.model.req;

/**
 * @description: 抽奖请求对象
 * @author： 有骥子
 * @date: 2023/7/2
 */
public class DrawReq {

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 抽奖策略ID
     */
    private Long strategyId;

    public DrawReq() {
    }

    public DrawReq(String uId, Long strategyId) {
        this.uId = uId;
        this.strategyId = strategyId;
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
}

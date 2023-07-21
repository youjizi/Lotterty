package com.libai.lottery.domain.activity.model.vo;

/**
 * @description: 用户领取活动
 * @author： 有骥子
 * @date: 2023/7/21
 */
public class UserTakeActivityVO {

    /**
     * 策略ID
     */
    private Long strategyId;

    /**
     * 活动领取ID
     */
    private Long takeId;

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Long getTakeId() {
        return takeId;
    }

    public void setTakeId(Long takeId) {
        this.takeId = takeId;
    }
}

package com.libai.lottery.domain.activity.model.res;

import com.libai.lottery.common.Result;

/**
 * @description:
 * @author： 有骥子
 * @date: 2023/7/17
 */
public class PartakeResult extends Result {

    /**
     * 策略ID
     */
    private Long strategyId;

    public PartakeResult(String code, String info) {
        super(code, info);
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }
}

package com.libai.lottery.process.res;

import com.libai.lottery.common.Result;

/**
 * @description: 规则量化结果
 * @author： 有骥子
 * @date: 2023/7/23
 */
public class RuleQuantificationCrowdResult extends Result {

    /** 活动ID */
    private Long activityId;

    public RuleQuantificationCrowdResult(String code, String info) {
        super(code, info);
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

}

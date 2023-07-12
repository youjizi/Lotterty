package com.libai.lottery.domain.activity.repository;

import com.libai.lottery.common.Constants;

/**
 * @description: 活动仓储接口
 * @author： 有骥子
 * @date: 2023/7/11
 */
public interface IActivityRepository {

    /**
     * 变更活动状态
     * @param activityId 活动ID
     * @param currentState 当前状态
     * @param targetState 目标状态
     * @return 变更结果
     */
    boolean alterStatus(Long activityId, Enum<Constants.ActivityState> currentState, Constants.ActivityState targetState);
}

package com.libai.lottery.domain.activity.service.stateflow;

import com.libai.lottery.common.Constants;
import com.libai.lottery.common.Result;

/**
 * @description: 状态接口
 * @author： 有骥子
 * @date: 2023/7/11
 */
public interface IStateHandler {

    /**
     * 活动提审
     * @param activityId 活动ID
     * @param currentState 当前状态
     * @return 结果
     */
    public  Result arraignment(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 审核通过
     * @param activityId 活动ID
     * @param currentState 当前状态
     * @return 结果
     */
    public  Result checkPass(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 审核拒绝
     * @param activityId 活动ID
     * @param currentState 当前状态
     * @return 结果
     */
    public  Result checkRefuse(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 撤审撤销
     * @param activityId 活动ID
     * @param currentState 当前状态
     * @return 结果
     */
    public  Result checkRevoke(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 活动关闭
     * @param activityId 活动ID
     * @param currentState 当前状态
     * @return 结果
     */
    public  Result close(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 活动开启
     * @param activityId 活动ID
     * @param currentState 当前状态
     * @return 结果
     */
    public  Result open(Long activityId, Enum<Constants.ActivityState> currentState);

    /**
     * 活动执行
     * @param activityId 活动ID
     * @param currentState 当前状态
     * @return 结果
     */
    public  Result doing(Long activityId, Enum<Constants.ActivityState> currentState);
    
}

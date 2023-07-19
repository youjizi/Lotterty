package com.libai.lottery.domain.activity.repository;

import java.util.Date;

/**
 * @description: 用户参与活动仓储接口
 * @author： 有骥子
 * @date: 2023/7/17
 */
public interface IUserTakeActivityRepository {

    /**
     * 扣除个人活动参与次数
     * @param activityId 活动ID
     * @param takeCount 活动个人可领取次数
     * @param userTakeLeftCount 活动个人剩余领取次数
     * @param uId 用户ID
     * @return 更新结果
     */
    int subtractionLeftCount(Long activityId, Integer takeCount, Integer userTakeLeftCount, String uId);

    /**
     * 领取活动
     *
     * @param activityId        活动ID
     * @param activityName      活动名称
     * @param strategyId        策略ID
     * @param takeCount         活动个人可领取次数
     * @param userTakeLeftCount 活动个人剩余领取次数
     * @param uId               用户ID
     * @param takeDate          领取时间
     * @param takeId            领取ID
     */
    void takeActivity(Long activityId, String activityName,Long strategyId ,Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId);
}
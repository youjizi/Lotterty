package com.libai.lottery.domain.activity.repository;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.activity.model.req.PartakeReq;
import com.libai.lottery.domain.activity.model.vo.ActivityBillVO;

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

    /**
     * 查询活动账单
     * @param req 请求参数
     * @return 结果
     */
    ActivityBillVO queryActivityBill(PartakeReq req);

    /**
     * 扣除库存
     * @param activityId 活动ID
     * @return 扣除结果
     */
    int subtractionActivityStock(Long activityId);
}

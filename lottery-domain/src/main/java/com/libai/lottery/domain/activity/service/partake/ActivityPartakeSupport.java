package com.libai.lottery.domain.activity.service.partake;

import com.libai.lottery.domain.activity.model.req.PartakeReq;
import com.libai.lottery.domain.activity.model.vo.ActivityBillVO;
import com.libai.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.libai.lottery.domain.activity.repository.IActivityRepository;
import com.libai.lottery.domain.activity.repository.IUserTakeActivityRepository;

import javax.annotation.Resource;

/**
 * @description: 活动领取通用数据服务
 * @author： 有骥子
 * @date: 2023/7/17
 */

public class ActivityPartakeSupport {

    @Resource
    protected IActivityRepository activityRepository;

    @Resource
    IUserTakeActivityRepository userTakeActivityRepository;

    protected ActivityBillVO queryActivityBill(PartakeReq req) {
        return activityRepository.queryActivityBill(req);
    }

    protected UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {
        return userTakeActivityRepository.queryNoConsumedTakeActivityOrder(activityId, uId);
    }
}

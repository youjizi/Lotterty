package com.libai.lottery.infrastructure.repository;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.activity.model.req.PartakeReq;
import com.libai.lottery.domain.activity.model.vo.ActivityBillVO;
import com.libai.lottery.domain.activity.model.vo.ActivityVO;
import com.libai.lottery.domain.activity.model.vo.AlterStateVO;
import com.libai.lottery.domain.activity.repository.IActivityRepository;
import com.libai.lottery.infrastructure.dao.IActivityDao;
import com.libai.lottery.infrastructure.dao.IUserTakeActivityCountDao;
import com.libai.lottery.infrastructure.po.Activity;
import com.libai.lottery.infrastructure.po.UserTakeActivityCount;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 活动仓储实现
 * @author： 有骥子
 * @date: 2023/7/11
 */

@Component
public class ActivityRepository implements IActivityRepository {

    @Resource
    IActivityDao activityDao;

    @Resource
    IUserTakeActivityCountDao userTakeActivityCountDao;

    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> currentState, Constants.ActivityState targetState) {

        AlterStateVO alterStateVO = new AlterStateVO(activityId, ((Constants.ActivityState)currentState).getCode(), targetState.getCode());
        int count = activityDao.alterStatus(alterStateVO);
        return 1 == count;
    }

    @Override
    public ActivityBillVO queryActivityBill(PartakeReq req) {
            // 查询活动信息
        Activity activity = activityDao.queryActivityById(req.getActivityId());

        // 查询领取次数
        UserTakeActivityCount userTakeActivityCountReq = new UserTakeActivityCount();
        userTakeActivityCountReq.setActivityId(req.getActivityId());
        userTakeActivityCountReq.setuId(req.getuId());
        UserTakeActivityCount userTakeActivityCount = userTakeActivityCountDao.queryUserTakeActivityCount(userTakeActivityCountReq);
        // 封装结果信息
        ActivityBillVO activityBillVO = new ActivityBillVO();
        activityBillVO.setuId(req.getuId());
        activityBillVO.setActivityId(req.getActivityId());
        activityBillVO.setActivityName(activity.getActivityName());
        activityBillVO.setBeginDateTime(activity.getBeginDateTime());
        activityBillVO.setEndDateTime(activity.getEndDateTime());
        activityBillVO.setTakeCount(activity.getTakeCount());
        activityBillVO.setStockSurplusCount(activity.getStockSurplusCount());
        activityBillVO.setStrategyId(activity.getStrategyId());
        activityBillVO.setState(activity.getState());
        activityBillVO.setUserTakeLeftCount(null == userTakeActivityCount ? null : userTakeActivityCount.getLeftCount());

        return activityBillVO;
    }

    @Override
    public int subtractionActivityStock(Long activityId) {
        return activityDao.subtractionActivityStock(activityId);
    }

    @Override
    public List<ActivityVO> scanToDoActivityList(Long id) {
        List<Activity> activityList =  activityDao.scanToDoActivityList(id);
        List<ActivityVO> activityVOList = new ArrayList<>(activityList.size());
        for (Activity activity : activityList) {
            ActivityVO activityVO = new ActivityVO();
            activityVO.setActivityId(activity.getId());
            activityVO.setActivityId(activity.getActivityId());
            activityVO.setActivityName(activity.getActivityName());
            activityVO.setBeginDateTime(activity.getBeginDateTime());
            activityVO.setEndDateTime(activity.getEndDateTime());
            activityVO.setState(activity.getState());
            activityVO.setCreator(activity.getCreator());
            activityVOList.add(activityVO);
        }
        return activityVOList;
    }

}

package com.libai.lottery.infrastructure.repository;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.activity.model.vo.AlterStateVO;
import com.libai.lottery.domain.activity.repository.IActivityRepository;
import com.libai.lottery.infrastructure.dao.IActivityDao;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @description: 活动仓储实现
 * @author： 有骥子
 * @date: 2023/7/11
 */

@Component
public class ActivityRepository implements IActivityRepository {

    @Resource
    IActivityDao activityDao;

    @Override
    public boolean alterStatus(Long activityId, Enum<Constants.ActivityState> currentState, Constants.ActivityState targetState) {

        AlterStateVO alterStateVO = new AlterStateVO(activityId, ((Constants.ActivityState)currentState).getCode(), targetState.getCode());
        int count = activityDao.alterStatus(alterStateVO);
        return 1 == count;
    }
}

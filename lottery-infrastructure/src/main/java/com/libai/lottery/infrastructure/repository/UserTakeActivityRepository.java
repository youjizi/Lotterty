package com.libai.lottery.infrastructure.repository;

import com.libai.lottery.domain.activity.model.vo.DrawOrderVO;
import com.libai.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.libai.lottery.domain.activity.repository.IUserTakeActivityRepository;
import com.libai.lottery.infrastructure.dao.IUserStrategyExportDao;
import com.libai.lottery.infrastructure.dao.IUserTakeActivityCountDao;
import com.libai.lottery.infrastructure.dao.IUserTakeActivityDao;
import com.libai.lottery.infrastructure.po.UserStrategyExport;
import com.libai.lottery.infrastructure.po.UserTakeActivity;
import com.libai.lottery.infrastructure.po.UserTakeActivityCount;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description: 用户参与活动仓储实现类
 * @author： 有骥子
 * @date: 2023/7/17
 */

@Component
public class UserTakeActivityRepository implements IUserTakeActivityRepository {


    @Resource
    private IUserTakeActivityCountDao userTakeActivityCountDao;

    @Resource
    private IUserTakeActivityDao  userTakeActivityDao;

    @Resource
    private IUserStrategyExportDao userStrategyExportDao;

    @Override
    public int subtractionLeftCount(Long activityId, Integer takeCount, Integer userTakeLeftCount, String uId) {

        if (null == userTakeLeftCount) {
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            userTakeActivityCount.setTotalCount(takeCount);
            userTakeActivityCount.setLeftCount(takeCount -1);
            userTakeActivityCountDao.insert(userTakeActivityCount);
            return 1;
        } else {
            UserTakeActivityCount userTakeActivityCount = new UserTakeActivityCount();
            userTakeActivityCount.setuId(uId);
            userTakeActivityCount.setActivityId(activityId);
            return userTakeActivityCountDao.updateLeftCount(userTakeActivityCount);
        }
    }

    @Override
    public void takeActivity(Long activityId, String activityName, Long strategyId,Integer takeCount, Integer userTakeLeftCount, String uId, Date takeDate, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setTakeId(takeId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setStrategyId(strategyId);
        userTakeActivity.setActivityName(activityName);
        userTakeActivity.setTakeDate(takeDate);
        userTakeActivity.setState(0);
        if (null == userTakeLeftCount) {
            userTakeActivity.setTakeCount(1);
        } else {
            userTakeActivity.setTakeCount(takeCount - userTakeLeftCount + 1);
        }
        String uuid = uId + "_" + activityId + "_" + userTakeActivity.getTakeCount();
        userTakeActivity.setUuid(uuid);

        userTakeActivityDao.insert(userTakeActivity);
    }

    @Override
    public int lockTackActivity(String uId, Long activityId, Long takeId) {
        UserTakeActivity userTakeActivity = new UserTakeActivity();
        userTakeActivity.setuId(uId);
        userTakeActivity.setActivityId(activityId);
        userTakeActivity.setTakeId(takeId);
        return userTakeActivityDao.lockTackActivity(userTakeActivity);
    }

    @Override
    public void saveStrategyExport(DrawOrderVO drawOrder) {
        UserStrategyExport userStrategyExport = new UserStrategyExport();
        BeanUtils.copyProperties(drawOrder, userStrategyExport);
        userStrategyExport.setMqState(0);
        userStrategyExport.setUuid(String.valueOf(drawOrder.getTakeId()));
        userStrategyExportDao.insert(userStrategyExport);
    }

    @Override
    public UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId) {

        UserTakeActivity userTakeActivityReq = new UserTakeActivity();
        userTakeActivityReq.setuId(uId);
        userTakeActivityReq.setActivityId(activityId);
        UserTakeActivity userTakeActivity =  userTakeActivityDao.queryNoConsumedTakeActivityOrder(userTakeActivityReq);
        if (null == userTakeActivity) {
            return null;
        }
        UserTakeActivityVO userTakeActivityVO = new UserTakeActivityVO();
        userTakeActivityVO.setTakeId(userTakeActivity.getTakeId());
        userTakeActivityVO.setStrategyId(userTakeActivity.getStrategyId());
        return userTakeActivityVO;
    }


}

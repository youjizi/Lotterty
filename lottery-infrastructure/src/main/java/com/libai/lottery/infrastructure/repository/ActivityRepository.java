package com.libai.lottery.infrastructure.repository;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.activity.model.req.PartakeReq;
import com.libai.lottery.domain.activity.model.res.StockResult;
import com.libai.lottery.domain.activity.model.vo.ActivityBillVO;
import com.libai.lottery.domain.activity.model.vo.ActivityVO;
import com.libai.lottery.domain.activity.model.vo.AlterStateVO;
import com.libai.lottery.domain.activity.repository.IActivityRepository;
import com.libai.lottery.infrastructure.dao.IActivityDao;
import com.libai.lottery.infrastructure.dao.IUserTakeActivityCountDao;
import com.libai.lottery.infrastructure.po.Activity;
import com.libai.lottery.infrastructure.po.UserTakeActivityCount;
import com.libai.lottery.infrastructure.util.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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



    private Logger logger = LoggerFactory.getLogger(ActivityRepository.class);

    @Resource
    IActivityDao activityDao;

    @Resource
    IUserTakeActivityCountDao userTakeActivityCountDao;


    @Resource
    private RedisUtil redisUtil;

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

        // 从缓存中获取库存
        Object usedStockCountObj =  redisUtil.get(Constants.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(req.getActivityId()));


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
        activityBillVO.setStockCount(activity.getStockCount());
        activityBillVO.setStockSurplusCount( null == usedStockCountObj ? activity.getStockSurplusCount() : activity.getStockCount() - Integer.parseInt(String.valueOf(usedStockCountObj)));
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

    @Override
    public StockResult subtractionActivityStockRedis(String uId, Long activityId, Integer stockCount) {

        // 1. 获取抽奖活动库存Key
        String stockKey = Constants.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT(activityId);

        // 2. 扣减库存， 目前占用库存数
        Integer stockUsedCount = (int)redisUtil.incr(stockKey, 1);

        // 3.超出库存判断
        if (stockUsedCount > stockCount) {
            redisUtil.decr(stockKey, 1);
            return new StockResult(Constants.ResponseCode.OUT_OF_STOCK.getCode(), Constants.ResponseCode.OUT_OF_STOCK.getInfo());
        }

        // 4. 以活动库存占用编号， 生成对应加锁Key， 细化锁的颗粒度
        String stockTokenKey = Constants.RedisKey.KEY_LOTTERY_ACTIVITY_STOCK_COUNT_TOKEN(activityId, stockUsedCount);

        // 5. 使用Redis.setNx 加一个分布式锁
        boolean lockToken = redisUtil.setNx(stockTokenKey, 350L);
        if (!lockToken) {
            logger.info("抽奖活动{}用户秒杀{}扣减库存，分布式锁失败：{}", activityId, uId, stockTokenKey);
            return new StockResult(Constants.ResponseCode.ERR_TOKEN.getCode(), Constants.ResponseCode.ERR_TOKEN.getInfo());
        }

        return new StockResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo(), stockTokenKey, stockCount - stockUsedCount);
    }

    @Override
    public void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code) {
        // 删除分布式锁 Key
        redisUtil.del(tokenKey);
    }

}

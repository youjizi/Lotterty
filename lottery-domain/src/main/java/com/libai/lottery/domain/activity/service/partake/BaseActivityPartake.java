package com.libai.lottery.domain.activity.service.partake;

import com.libai.lottery.common.Constants;
import com.libai.lottery.common.Result;
import com.libai.lottery.domain.activity.model.req.PartakeReq;
import com.libai.lottery.domain.activity.model.res.PartakeResult;
import com.libai.lottery.domain.activity.model.res.StockResult;
import com.libai.lottery.domain.activity.model.vo.ActivityBillVO;
import com.libai.lottery.domain.activity.model.vo.UserTakeActivityVO;
import com.libai.lottery.domain.support.ids.IIdGenerator;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 活动领取模板抽象类
 * @author： 有骥子
 * @date: 2023/7/17
 */
public abstract class BaseActivityPartake extends ActivityPartakeSupport implements IActivityPartake{

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public PartakeResult doPartake(PartakeReq req) {

        // 1.查询是否存在未执行抽奖领取活动单
        UserTakeActivityVO userTakeActivityVO = this.queryNoConsumedTakeActivityOrder(req.getActivityId(), req.getuId());
        if (null != userTakeActivityVO) {
            return buildPartakeResult(userTakeActivityVO.getStrategyId(), userTakeActivityVO.getTakeId(), Constants.ResponseCode.NOT_CONSUMED_TAKE);
        }

        // 2.查询活动账单
        ActivityBillVO activityBillVO = super.queryActivityBill(req);

        // 3.活动信息校验处理【活动库存、状态、日期、个人参与次数】
        Result checkResult = this.checkActivityBill(req, activityBillVO);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(checkResult.getCode())) {
            return new PartakeResult(checkResult.getCode(), checkResult.getInfo());
        }

        // 4.扣减活动库存【目前为直接对配置库中的 lottery.activity 直接操作表扣减库存，后续优化为Redis扣减】
        StockResult subtractionActivityResult = this.subtractionActivityStockRedis(req.getuId(), req.getActivityId(), activityBillVO.getStockSurplusCount());
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(subtractionActivityResult.getCode())) {
            return new PartakeResult(subtractionActivityResult.getCode(), subtractionActivityResult.getInfo());
        }

        // 5.领取活动信息【个人用户把活动信息写入到用户表】
        Long takeId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        Result grabResult = this.grabActivity(req, activityBillVO, takeId);
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(grabResult.getCode())) {
            return new PartakeResult(grabResult.getCode(), grabResult.getInfo());
        }

        // 6. 扣减活动库存 通过Redis End
        this.recoverActivityCacheStockByRedis(req.getActivityId(), subtractionActivityResult.getStockKey(), Constants.ResponseCode.SUCCESS.getCode());

        return buildPartakeResult(activityBillVO.getStrategyId(), takeId, activityBillVO.getStockCount() , subtractionActivityResult.getStockSurplusCount(), Constants.ResponseCode.SUCCESS);
    }


    /**
     * 活动信息校验处理【活动库存、状态、日期、个人参与次数】
     * @param partake 参与活动请求
     * @param bill 活动账单
     * @return 校验结果
     */
    protected abstract Result checkActivityBill(PartakeReq partake, ActivityBillVO bill);



    /**
     * 扣减活动库存
     * @param req 参与活动请求
     * @return 扣减结果
     */
    protected abstract Result subtractionActivityStock(PartakeReq req);


    /**
     * 领取活动
     *
     * @param partake 参与活动请求
     * @param bill    活动账单
     * @param takeId  领取ID
     * @return 领取结果
     */
    protected abstract Result grabActivity(PartakeReq partake, ActivityBillVO bill, Long takeId);


    /**
     * 结果封装
     * @param strategyId 策略ID
     * @param takeId 领取ID
     * @param responseCode 结果信息
     * @param stockCount 总库存
     * @param surStockCount 剩余库存
     * @return 封装对象
     */
    private PartakeResult buildPartakeResult(Long strategyId, Long takeId, Integer stockCount, Integer surStockCount,Constants.ResponseCode  responseCode) {
        PartakeResult partakeResult = new PartakeResult(responseCode.getCode(), responseCode.getInfo());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        partakeResult.setStockCount(stockCount);
        partakeResult.setStockSurplusCount(surStockCount);
        return partakeResult;
    }

    /**
     * 结果封装
     * @param strategyId 策略ID
     * @param takeId 领取ID
     * @param responseCode 结果信息
     * @return 封装对象
     */
    private PartakeResult buildPartakeResult(Long strategyId, Long takeId, Constants.ResponseCode  responseCode) {
        PartakeResult partakeResult = new PartakeResult(responseCode.getCode(), responseCode.getInfo());
        partakeResult.setStrategyId(strategyId);
        partakeResult.setTakeId(takeId);
        return partakeResult;
    }


    /**
     * 扣减活动库存， 通过redis
     * @param uId 用户ID
     * @param activityId 活动ID
     * @param stockCount 总库存
     * @return 扣减结果
     */
    protected abstract StockResult subtractionActivityStockRedis(String uId, Long activityId, Integer stockCount);

    /**
     * 恢复活动库存，通过Redis 【如果非常异常，则需要进行缓存库存恢复，只保证不超卖的特性，所以不保证一定能恢复占用库存，另外最终可以由任务进行补偿库存】
     * @param activityId 活动Id
     * @param tokenKey 分布式 Key 用于清理
     * @param code 状态
     */
    protected abstract void recoverActivityCacheStockByRedis(Long activityId, String tokenKey, String code);
}

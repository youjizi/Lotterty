package com.libai.lottery.domain.activity.repository;

import com.libai.lottery.domain.activity.model.vo.DrawOrderVO;
import com.libai.lottery.domain.activity.model.vo.InvoiceVO;
import com.libai.lottery.domain.activity.model.vo.UserTakeActivityVO;

import java.util.Date;
import java.util.List;

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

    /**
     * 锁定活动领取记录
     *
     * @param uId        用户ID
     * @param activityId 活动ID
     * @param takeId     领取ID
     * @return 更新结果
     */
    int lockTackActivity(String uId, Long activityId, Long takeId);

    /**
     * 保存抽奖信息
     *
     * @param drawOrder 中奖单
     */
    void saveStrategyExport(DrawOrderVO drawOrder);

    /**
     * 查询已领取未消费的活动单
     * @param activityId 活动ID
     * @param uId 用户ID
     * @return 结果
     */
    UserTakeActivityVO queryNoConsumedTakeActivityOrder(Long activityId, String uId);

    /**
     * 改变mq状态
     * @param uId 用户Id
     * @param orderId 用户抽奖订单Id
     * @param code 状态码
     */
    void updateInvoiceMqState(String uId, Long orderId, Integer code);

    /**
     * 扫描奖品账单MQ状态： 0未发送， 2发送失败
     * @return 集合
     */
    List<InvoiceVO> scanInvoiceMqState();

}

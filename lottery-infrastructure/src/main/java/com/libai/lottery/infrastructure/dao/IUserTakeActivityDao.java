package com.libai.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import com.libai.lottery.infrastructure.po.UserTakeActivity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 用户领取活动表
 * @author： 有骥子
 * @date: 2023/7/17
 */

@Mapper
public interface IUserTakeActivityDao {

    /**
     * 插入用户领取记录
     * @param userTakeActivity 入参
     */
    void insert(UserTakeActivity userTakeActivity);

    /**
     * 锁定活动领取记录
     * @param userTakeActivity 入参
     * @return 更新结果
     */
    int lockTackActivity(UserTakeActivity userTakeActivity);

    /**
     * 查询已领取未消费的活动单
     * @param userTakeActivityReq 请求信息
     * @return 结果
     */
    @DBRouter
    UserTakeActivity queryNoConsumedTakeActivityOrder(UserTakeActivity userTakeActivityReq);
}

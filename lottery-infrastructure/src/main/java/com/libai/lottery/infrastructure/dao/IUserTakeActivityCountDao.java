package com.libai.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import com.libai.lottery.infrastructure.po.UserTakeActivityCount;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 用户活动参与次数
 * @author： 有骥子
 * @date: 2023/7/17
 */

@Mapper
public interface IUserTakeActivityCountDao {


    /**
     * 查询用户领取次数信息
     * @param userTakeActivityCountReq 请求入参【活动号、用户ID】
     * @return 领取结果
     */
    @DBRouter
    UserTakeActivityCount queryUserTakeActivityCount(UserTakeActivityCount userTakeActivityCountReq);

    /**
     * 增加用户参与次数记录
     * @param userTakeActivityCount 请求入参
     */
    void insert(UserTakeActivityCount userTakeActivityCount);

    /**
     * 扣减参与次数
     * @param userTakeActivityCount 入参
     * @return 扣减结果
     */
    int updateLeftCount(UserTakeActivityCount userTakeActivityCount);
}

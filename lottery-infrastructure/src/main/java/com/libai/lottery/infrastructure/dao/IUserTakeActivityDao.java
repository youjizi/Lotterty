package com.libai.lottery.infrastructure.dao;

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
}

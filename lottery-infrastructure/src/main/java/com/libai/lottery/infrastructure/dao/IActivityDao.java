package com.libai.lottery.infrastructure.dao;

import com.libai.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 活动接口
 * @author： 有骥子
 * @date: 2023/6/28
 */
@Mapper
public interface IActivityDao {


    /**
     * 查询活动
     * @param activityId 活动ID
     * @return 结果
     */
    Activity queryActivityById(Long activityId);
}

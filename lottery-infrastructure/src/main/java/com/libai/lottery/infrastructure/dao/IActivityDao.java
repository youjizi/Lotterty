package com.libai.lottery.infrastructure.dao;

import com.libai.lottery.domain.activity.model.vo.AlterStateVO;
import com.libai.lottery.infrastructure.po.Activity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 更改活动状态
     * @param alterStateVO 状态对象
     * @return 结果
     */
    int alterStatus(AlterStateVO alterStateVO);

    /**
     * 扣减活动库存
     * @param activityId 活动ID
     * @return 更新数量
     */
    int subtractionActivityStock(Long activityId);

    /**
     * 扫描待处理的活动列表，状态为：通过、活动中
     *
     * @param id ID
     * @return 待处理的活动集合
     */
    List<Activity> scanToDoActivityList(Long id);
}

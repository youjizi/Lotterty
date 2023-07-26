package com.libai.lottery.domain.activity.service.deploy;

import com.libai.lottery.domain.activity.model.vo.ActivityVO;

import java.util.List;

/**
 * @description: 部署活动配置接口
 * @author： 有骥子
 * @date: 2023/7/25
 */


public interface IActivityDeploy {

    /**
     * 扫描待处理的活动列表，状态为：通过、活动中
     * <p>
     * 通过 -> 时间符合时 -> 活动中
     * 活动中 -> 时间到期时 -> 关闭
     *
     * @param id ID
     * @return 待处理的活动集合
     */
    List<ActivityVO> scanToDoActivityList(Long id);
}

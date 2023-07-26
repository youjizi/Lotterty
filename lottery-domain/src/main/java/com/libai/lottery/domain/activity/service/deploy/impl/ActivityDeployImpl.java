package com.libai.lottery.domain.activity.service.deploy.impl;

import com.libai.lottery.domain.activity.model.vo.ActivityVO;
import com.libai.lottery.domain.activity.repository.IActivityRepository;
import com.libai.lottery.domain.activity.service.deploy.IActivityDeploy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author： 有骥子
 * @date: 2023/7/25
 */
public class ActivityDeployImpl implements IActivityDeploy {

    private Logger logger = LoggerFactory.getLogger(ActivityDeployImpl.class);

    @Resource
    private IActivityRepository activityRepository;

    @Override
    public List<ActivityVO> scanToDoActivityList(Long id) {
        return activityRepository.scanToDoActivityList(id);
    }
}

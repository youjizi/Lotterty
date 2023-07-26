package com.libai.lottery.worker;

import com.alibaba.fastjson.JSON;
import com.libai.lottery.common.Constants;
import com.libai.lottery.common.Result;
import com.libai.lottery.domain.activity.model.vo.ActivityVO;
import com.libai.lottery.domain.activity.repository.IActivityRepository;
import com.libai.lottery.domain.activity.service.stateflow.IStateHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @description: 抽奖业务， 任务配置
 * @author： 有骥子
 * @date: 2023/7/25
 */

@Component
public class LotteryXxlJob {

    private Logger logger = LoggerFactory.getLogger(LotteryXxlJob.class);


    @Resource
    private IActivityRepository activityRepository;

    @Resource
    private IStateHandler stateHandler;

    @XxlJob("lotteryActivityStateJobHandler")
    public void lotteryActivityStateJobHandler() {
        logger.info("扫描活动状态 Begin");

        List<ActivityVO> activityList = activityRepository.scanToDoActivityList(0L);
        if (activityList.isEmpty()) {
            logger.info("扫描活动状态 End 暂无符合需要扫描的活动列表");
            return;
        }

        while (!activityList.isEmpty()) {
            for (ActivityVO activityVO : activityList) {
                Integer state = activityVO.getState();
                switch (state) {
                    // 活动状态为审核通过，在临近活动开启时间前，审核活动为活动中。在使用活动的时候，需要依照活动状态核时间两个字段进行判断和使用。
                    case 4:
                        Result state4Result = stateHandler.doing(activityVO.getActivityId(), Constants.ActivityState.PASS);
                        logger.info("扫描活动状态：活动中  结果：{}  activityId: {} activityName : {} creator: {}", JSON.toJSONString(state4Result), activityVO.getActivityId(), activityVO.getActivityName(), activityVO.getCreator());
                        break;
                    // 扫描时间已过期的活动，从活动中状态变更为关闭状态【这里也可以细化为2个任务来处理，也可以把时间判断放到数据库中操作】
                    case 5:
                        if (activityVO.getEndDateTime().before(new Date())) {
                            Result state5Result = stateHandler.close(activityVO.getActivityId(), Constants.ActivityState.DOING);
                            logger.info("扫描活动状态：关闭  结果：{}  activityId: {} activityName : {} creator: {}", JSON.toJSONString(state5Result), activityVO.getActivityId(), activityVO.getActivityName(), activityVO.getCreator());
                        }
                        break;
                    default:
                        break;
                }
            }
            // 获取集合中最后一条记录，继续扫描后面10条记录
            ActivityVO activityVO = activityList.get(activityList.size() - 1);
            activityList = activityRepository.scanToDoActivityList(activityVO.getId());
        }
        logger.info("扫描活动状态 End");
    }



}

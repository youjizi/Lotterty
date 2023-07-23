package com.libai.lottery.process.impl;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.activity.model.req.PartakeReq;
import com.libai.lottery.domain.activity.model.res.PartakeResult;
import com.libai.lottery.domain.activity.model.vo.DrawOrderVO;
import com.libai.lottery.domain.activity.service.partake.IActivityPartake;
import com.libai.lottery.domain.rule.model.req.DecisionMatterReq;
import com.libai.lottery.domain.rule.model.res.EngineResult;
import com.libai.lottery.domain.rule.service.engine.EngineFilter;
import com.libai.lottery.domain.strategy.model.req.DrawReq;
import com.libai.lottery.domain.strategy.model.res.DrawResult;
import com.libai.lottery.domain.strategy.model.vo.DrawAwardInfoVO;
import com.libai.lottery.domain.strategy.service.draw.IDrawExec;
import com.libai.lottery.domain.support.ids.IIdGenerator;
import com.libai.lottery.process.IActivityProcess;
import com.libai.lottery.process.req.DrawProcessReq;
import com.libai.lottery.process.res.DrawProcessResult;
import com.libai.lottery.process.res.RuleQuantificationCrowdResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description: 活动抽奖流程编排
 * @author： 有骥子
 * @date: 2023/7/20
 */

@Service
public class ActivityProcessImpl implements IActivityProcess {

    @Resource
    private IActivityPartake activityPartake;

    @Resource
    private IDrawExec drawExec;

    @Resource(name = "ruleEngineHandle")
    private EngineFilter engineFilter;

    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Override
    public DrawProcessResult doDrawProcess(DrawProcessReq req) {

        // 1.领取活动
        PartakeResult partakeResult = activityPartake.doPartake(new PartakeReq(req.getuId(), req.getActivityId()));
        if (!Constants.ResponseCode.SUCCESS.getCode().equals(partakeResult.getCode())) {
            return new DrawProcessResult(partakeResult.getCode(), partakeResult.getInfo());
        }
        Long strategyId = partakeResult.getStrategyId();
        Long takeId = partakeResult.getTakeId();

        // 2.执行抽奖
        DrawResult drawResult = drawExec.doDrawExec(new DrawReq(req.getuId(), strategyId));
        if (Constants.DrawState.FAIL.getCode().equals(drawResult.getDrawState())) {
            return new DrawProcessResult(Constants.ResponseCode.LOSING_DRAW.getCode(), Constants.ResponseCode.LOSING_DRAW.getInfo());
        }
        DrawAwardInfoVO drawAwardInfoVO = drawResult.getDrawAwardInfo();

        // 3.结果落地
        activityPartake.recordDrawOrder(buildDrawOrderVO(req, strategyId, drawAwardInfoVO, takeId));

        // 4.发送MQ, 触发奖流程

        // 5. 返回结果
        return new DrawProcessResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo(), drawAwardInfoVO);
    }

    @Override
    public RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req) {

        EngineResult engineResult = engineFilter.process(req);

        if (!engineResult.isSuccess()) {
            return new RuleQuantificationCrowdResult(Constants.ResponseCode.RULE_ERR.getCode(), Constants.ResponseCode.RULE_ERR.getInfo());
        }

        RuleQuantificationCrowdResult ruleQuantificationCrowdResult = new RuleQuantificationCrowdResult(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
        ruleQuantificationCrowdResult.setActivityId(Long.valueOf(engineResult.getNodeValue()));


        return ruleQuantificationCrowdResult;
    }


    private DrawOrderVO buildDrawOrderVO(DrawProcessReq req, Long strategyId, DrawAwardInfoVO drawAwardInfoVO, Long takeId) {
        Long orderId = idGeneratorMap.get(Constants.Ids.SnowFlake).nextId();
        DrawOrderVO drawOrderVO = new DrawOrderVO();
        drawOrderVO.setuId(req.getuId());
        drawOrderVO.setActivityId(req.getActivityId());
        drawOrderVO.setOrderId(orderId);
        drawOrderVO.setTakeId(takeId);
        drawOrderVO.setStrategyId(strategyId);
        drawOrderVO.setStrategyMode(drawAwardInfoVO.getStrategyMode());
        drawOrderVO.setGrantType(drawAwardInfoVO.getGrantType());
        drawOrderVO.setGrantDate(drawAwardInfoVO.getGrantDate());
        drawOrderVO.setGrantState(Constants.GrantState.INIT.getCode());
        drawOrderVO.setAwardId(drawAwardInfoVO.getAwardId());
        drawOrderVO.setAwardType(drawAwardInfoVO.getAwardType());
        drawOrderVO.setAwardName(drawAwardInfoVO.getAwardName());
        drawOrderVO.setAwardContent(drawAwardInfoVO.getAwardContent());
        return drawOrderVO;
    }
}

package com.libai.lottery.interfaces.facade;

import com.alibaba.fastjson.JSON;
import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.rule.model.req.DecisionMatterReq;
import com.libai.lottery.domain.strategy.model.vo.DrawAwardInfoVO;
import com.libai.lottery.interfaces.assembler.IMapping;
import com.libai.lottery.process.IActivityProcess;
import com.libai.lottery.process.req.DrawProcessReq;
import com.libai.lottery.process.res.DrawProcessResult;
import com.libai.lottery.process.res.RuleQuantificationCrowdResult;
import libai.lottery.rpc.ILotteryActivityBooth;
import libai.lottery.rpc.dto.AwardDTO;
import libai.lottery.rpc.req.DrawReq;
import libai.lottery.rpc.req.QuantificationDrawReq;
import libai.lottery.rpc.res.DrawRes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;

/**
 * @description:
 * @author： 有骥子
 * @date: 2023/7/23
 */

@Controller
public class LotteryActivityBooth implements ILotteryActivityBooth {

    private Logger logger = LoggerFactory.getLogger(LotteryActivityBooth.class);

    @Resource
    private IActivityProcess activityProcess;

    @Resource
    private IMapping<DrawAwardInfoVO, AwardDTO> awardMapping;


    @Override
    public DrawRes doDraw(DrawReq drawReq) {


        try {
            logger.info("抽奖，开始 uId：{} activityId：{}", drawReq.getuId(), drawReq.getActivityId());

            // 1.执行抽奖
            DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(new DrawProcessReq(drawReq.getuId(), drawReq.getActivityId()));
            if (!Constants.ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())) {
                logger.error("抽奖，失败(抽奖过程异常) uId：{} activityId：{}", drawReq.getuId(), drawReq.getActivityId());
                return new DrawRes(drawProcessResult.getCode(), drawProcessResult.getInfo());
            }

            // 2. 数据转换
            DrawAwardInfoVO drawAwardInfo = drawProcessResult.getDrawAwardInfo();
            AwardDTO awardDTO = awardMapping.sourceToTarget(drawAwardInfo);
            awardDTO.setActivityId(drawReq.getActivityId());


            DrawRes drawRes = new DrawRes(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDTO(awardDTO);

            logger.info("抽奖，完成 uId：{} activityId：{} drawRes： {}", drawReq.getActivityId(), drawReq.getActivityId(), JSON.toJSONString(awardDTO));

            return drawRes;
        } catch (Exception e) {
            logger.error("抽奖，失败 uId：{} activityId：{} reqJson：{}", drawReq.getuId(), drawReq.getActivityId(), JSON.toJSONString(drawReq), e);
            return new DrawRes(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
        }
    }

    @Override
    public DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq) {


        try {
            String uid = quantificationDrawReq.getuId();
            logger.info("量化人群抽奖，开始 uId：{} treeId：{}", uid, quantificationDrawReq.getTreeId());

            // 0 执行规则引擎，获取用户可以参与的活动号
            RuleQuantificationCrowdResult ruleQuantificationCrowdResult = activityProcess.doRuleQuantificationCrowd(new DecisionMatterReq(quantificationDrawReq.getTreeId(), uid, quantificationDrawReq.getValMap()));
            if (!Constants.ResponseCode.SUCCESS.getCode().equals(ruleQuantificationCrowdResult.getCode())) {
                logger.error("量化人群抽奖，失败(规则引擎执行异常) uId：{} terrId：{}", uid, quantificationDrawReq.getTreeId());
                return new DrawRes(ruleQuantificationCrowdResult.getCode(), ruleQuantificationCrowdResult.getInfo());
            }

            // 1.执行抽奖
            Long activityId = ruleQuantificationCrowdResult.getActivityId();
            DrawProcessResult drawProcessResult = activityProcess.doDrawProcess(new DrawProcessReq(quantificationDrawReq.getuId(), activityId));
            if (!Constants.ResponseCode.SUCCESS.getCode().equals(drawProcessResult.getCode())) {
                logger.error("抽奖，失败(抽奖过程异常) uId：{} activityId：{}", uid, activityId);
                return new DrawRes(drawProcessResult.getCode(), drawProcessResult.getInfo());
            }

            // 2. 数据转换
            DrawAwardInfoVO drawAwardInfo = drawProcessResult.getDrawAwardInfo();
            AwardDTO awardDTO = awardMapping.sourceToTarget(drawAwardInfo);
            awardDTO.setActivityId(activityId);


            DrawRes drawRes = new DrawRes(Constants.ResponseCode.SUCCESS.getCode(), Constants.ResponseCode.SUCCESS.getInfo());
            drawRes.setAwardDTO(awardDTO);

            logger.info("抽奖，完成 uId：{} activityId：{} drawRes： {}", uid, activityId, JSON.toJSONString(awardDTO));

            return drawRes;
        } catch (Exception e) {
            logger.error("抽奖，失败 uId：{} treeId：{} reqJson：{}", quantificationDrawReq.getuId(), quantificationDrawReq.getTreeId(), JSON.toJSONString(quantificationDrawReq), e);
            return new DrawRes(Constants.ResponseCode.UN_ERROR.getCode(), Constants.ResponseCode.UN_ERROR.getInfo());
        }
    }
}

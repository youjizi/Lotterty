package com.libai.lottery.process;

import com.libai.lottery.domain.rule.model.req.DecisionMatterReq;
import com.libai.lottery.process.req.DrawProcessReq;
import com.libai.lottery.process.res.DrawProcessResult;
import com.libai.lottery.process.res.RuleQuantificationCrowdResult;

/**
 * @description: 活动抽奖流程编排接口
 * @author： 有骥子
 * @date: 2023/7/20
 */
public interface IActivityProcess {

    /**
     * 执行抽奖流程
     * @param req 抽奖请求
     * @return 抽奖结果
     */
    DrawProcessResult doDrawProcess(DrawProcessReq req);

    /**
     * 规则量化人群， 返回可参与的活动ID
     * @param req 规则请求
     * @return 量化结果， 用户可以参与的活动ID
     */
    RuleQuantificationCrowdResult doRuleQuantificationCrowd(DecisionMatterReq req);
}

package com.libai.lottery.domain.rule.service.engine;

import com.libai.lottery.domain.rule.model.req.DecisionMatterReq;
import com.libai.lottery.domain.rule.model.res.EngineResult;

/**
 * @description: 规则过滤器引擎
 * @author： 有骥子
 * @date: 2023/7/22
 */
public interface EngineFilter {

    /**
     * 规则过滤器接口
     * @param matter 规则决策物料
     * @return 规则决策结果
     */
    EngineResult process(final DecisionMatterReq matter);

}

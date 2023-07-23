package com.libai.lottery.domain.rule.service.engine.impl;

import com.libai.lottery.domain.rule.model.aggregates.TreeRuleRich;
import com.libai.lottery.domain.rule.model.req.DecisionMatterReq;
import com.libai.lottery.domain.rule.model.res.EngineResult;
import com.libai.lottery.domain.rule.model.vo.TreeNodeVO;
import com.libai.lottery.domain.rule.repository.IRuleRepository;
import com.libai.lottery.domain.rule.service.engine.EngineBase;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @description: 规则引擎处理器
 * @author： 有骥子
 * @date: 2023/7/22
 */

@Service("ruleEngineHandle")
public class RuleEngineHandle extends EngineBase {

    @Resource
    IRuleRepository ruleRepository;


    @Override
    public EngineResult process(DecisionMatterReq matter) {

        // 决策规则树
        TreeRuleRich treeRuleRich = ruleRepository.queryTreeRuleRich(matter.getTreeId());
        if (null == treeRuleRich) {
            throw new RuntimeException("Tree Rule is null!");
        }

        // 决策节点
        TreeNodeVO treeNodeInfo = engineDecisionMaker(treeRuleRich, matter);


        return new EngineResult(matter.getUserId(),treeNodeInfo.getTreeId(), treeNodeInfo.getTreeNodeId(), treeNodeInfo.getNodeValue());
    }
}

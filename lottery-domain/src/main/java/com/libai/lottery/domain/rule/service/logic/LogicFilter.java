package com.libai.lottery.domain.rule.service.logic;

import com.libai.lottery.domain.rule.model.req.DecisionMatterReq;
import com.libai.lottery.domain.rule.model.vo.TreeNodeLineVO;

import java.util.List;

/**
 * @description: 规则过滤器接口
 * @author： 有骥子
 * @date: 2023/7/21
 */
public interface LogicFilter {

    /**
     * 逻辑决策器
     * @param matterValue          决策值
     * @param treeNodeLineInfoList 决策节点
     * @return 下一个节点
     */
    Long filter(String matterValue, List<TreeNodeLineVO> treeNodeLineInfoList);

    /**
     * 获取决策值
     * @param decisionMatter 决策事态
     * @return 决策值
     */
    String matterValue(DecisionMatterReq decisionMatter);
}

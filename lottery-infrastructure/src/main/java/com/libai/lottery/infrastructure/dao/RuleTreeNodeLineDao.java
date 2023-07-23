package com.libai.lottery.infrastructure.dao;

import com.libai.lottery.infrastructure.po.RuleTreeNodeLine;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 规则树节点连载接口
 * @author： 有骥子
 * @date: 2023/7/22
 */

@Mapper
public interface RuleTreeNodeLineDao {

    /**
     * 查询规则树节点连线集合
     * @param ruleTreeNodeLineReq 请求数据
     * @return 规则树节点连线集合
     */
    List<RuleTreeNodeLine> queryRuleTreeNodeLineList(RuleTreeNodeLine ruleTreeNodeLineReq);
}

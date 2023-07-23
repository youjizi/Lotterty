package com.libai.lottery.infrastructure.dao;

import com.libai.lottery.infrastructure.po.RuleTreeNode;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 规则树节点接口
 * @author： 有骥子
 * @date: 2023/7/22
 */

@Mapper
public interface RuleTreeNodeDao {

    /**
     * 查询规则树节点集合
     * @param treeId 规则树ID
     * @return 查询集合
     */
    List<RuleTreeNode> queryRuleTreeNodeList(Long treeId);
}

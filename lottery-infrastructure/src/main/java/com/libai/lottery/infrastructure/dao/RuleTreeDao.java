package com.libai.lottery.infrastructure.dao;

import com.libai.lottery.infrastructure.po.RuleTree;
import org.apache.ibatis.annotations.Mapper;

/**
 * @description: 规则树接口
 * @author： 有骥子
 * @date: 2023/7/22
 */

@Mapper
public interface RuleTreeDao {

    /**
     * 查询规则树
     * @param treeId 规则树ID
     * @return 结果
     */
    RuleTree queryRuleTreeByTreeId(Long treeId);
}

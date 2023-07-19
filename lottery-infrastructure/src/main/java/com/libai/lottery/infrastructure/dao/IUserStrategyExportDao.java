package com.libai.lottery.infrastructure.dao;

import cn.bugstack.middleware.db.router.annotation.DBRouter;
import cn.bugstack.middleware.db.router.annotation.DBRouterStrategy;
import com.libai.lottery.infrastructure.po.UserStrategyExport;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @description: 户策略计算结果
 * @author： 有骥子
 * @date: 2023/7/17
 */

@Mapper
@DBRouterStrategy(splitTable = true)
public interface IUserStrategyExportDao {

    /**
     * 新增数据
     * @param userTakeExport 用户策略
     */
    @DBRouter(key = "uId")
    void insert(UserStrategyExport userTakeExport);

    /**
     * 查询数据
     * @param id  自增Id
     * @return 用户策略
     */
    @DBRouter
    List<UserStrategyExport> queryUserStrategyExportByUId(String id);


}

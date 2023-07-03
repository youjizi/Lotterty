package com.libai.lottery.infrastructure.dao;

import com.libai.lottery.infrastructure.po.Award;
import org.apache.ibatis.annotations.Mapper;
/**
 * @description: 奖品接口
 * @author： 有骥子
 * @date: 2023/6/28
 */
@Mapper
public interface IAwardDao {


    /**
     * 查询奖品信息
     * @param awardId 奖品ID
     * @return 结果
     */
    Award queryAwardInfo(String awardId);

}

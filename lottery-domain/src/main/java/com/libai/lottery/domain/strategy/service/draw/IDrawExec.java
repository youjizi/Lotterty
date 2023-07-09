package com.libai.lottery.domain.strategy.service.draw;

import com.libai.lottery.domain.strategy.model.req.DrawReq;
import com.libai.lottery.domain.strategy.model.res.DrawResult;

/**
 * @description: 抽奖执行接口
 * @author： 有骥子
 * @date: 2023/7/3
 */
public interface IDrawExec {

    /**
     * 抽奖方式
     * @param req 请求参数
     * @return 抽奖奖品
     */
    DrawResult doDrawExec(DrawReq req);
}

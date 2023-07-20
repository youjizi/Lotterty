package com.libai.lottery.process;

import com.libai.lottery.process.req.DrawProcessReq;
import com.libai.lottery.process.res.DrawProcessResult;

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
}

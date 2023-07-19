package com.libai.lottery.domain.activity.service.partake;

import com.libai.lottery.domain.activity.model.req.PartakeReq;
import com.libai.lottery.domain.activity.model.res.PartakeResult;

/**
 * @description: 参与活动接口
 * @author： 有骥子
 * @date: 2023/7/17
 */
public interface IActivityPartake {


    /**
     * 参与活动
     * @param req 入参
     * @return 领取结果
     */
    PartakeResult doPartake(PartakeReq req);
}

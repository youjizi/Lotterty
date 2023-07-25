package com.libai.lottery.domain.activity.service.partake;

import com.libai.lottery.common.Result;
import com.libai.lottery.domain.activity.model.req.PartakeReq;
import com.libai.lottery.domain.activity.model.res.PartakeResult;
import com.libai.lottery.domain.activity.model.vo.DrawOrderVO;

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

    /**
     * 保存奖品单
     * @param drawOrder 奖品单
     * @return 保存结果
     */
    Result recordDrawOrder(DrawOrderVO drawOrder);

    /**
     * 改变mq状态
     * @param uId 用户Id
     * @param orderId 用户抽奖订单Id
     * @param code 状态码
     */
    void updateInvoiceMqState(String uId, Long orderId, Integer code);
}

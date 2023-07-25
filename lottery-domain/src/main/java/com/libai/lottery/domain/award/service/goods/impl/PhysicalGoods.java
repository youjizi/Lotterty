package com.libai.lottery.domain.award.service.goods.impl;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.award.model.req.GoodsReq;
import com.libai.lottery.domain.award.model.res.DistributionRes;
import com.libai.lottery.domain.award.service.goods.DistributionBase;
import com.libai.lottery.domain.award.service.goods.IDistributionGoods;
import org.springframework.stereotype.Component;


/**
 * @description: 实物商品
 * @author： 有骥子
 * @date: 2023/7/9
 */

@Component
public class PhysicalGoods extends DistributionBase implements IDistributionGoods {


    @Override
    public DistributionRes doDistribution(GoodsReq req) {

        //  模拟调用优惠卷发放接口
        logger.info("拟调用实物发放接口 uId: {} awardContent： {}", req.getuId(), req.getAwardContent());

        // 更新用户领奖结果
        super.updateUserAwardSate(req.getuId(), req.getOrderId(), req.getAwardId(), Constants.AwardState.SUCCESS.getCode());

        return new DistributionRes(req.getuId(), Constants.AwardState.SUCCESS.getCode(), Constants.AwardState.SUCCESS.getInfo());
    }
}

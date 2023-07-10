package com.libai.lottery.domain.award.service.goods;

import com.libai.lottery.domain.award.model.req.GoodsReq;
import com.libai.lottery.domain.award.model.res.DistributionRes;

/**
 * @description: 奖品配送接口
 * @author： 有骥子
 * @date: 2023/7/9
 */
public interface IDistributionGoods {

    /**
     * 奖品配送接口，奖品类型（1:文字描述、2:兑换码、3:优惠券、4:实物奖品）
     * @param req
     * @return
     */
    DistributionRes doDistribution(GoodsReq req);
}

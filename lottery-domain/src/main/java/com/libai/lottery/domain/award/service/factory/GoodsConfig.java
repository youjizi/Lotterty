package com.libai.lottery.domain.award.service.factory;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.award.service.goods.IDistributionGoods;
import com.libai.lottery.domain.award.service.goods.impl.CouponGoods;
import com.libai.lottery.domain.award.service.goods.impl.DescGoods;
import com.libai.lottery.domain.award.service.goods.impl.PhysicalGoods;
import com.libai.lottery.domain.award.service.goods.impl.RedeemCodeGoods;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 奖品发放配置类
 * @author： 有骥子
 * @date: 2023/7/9
 */
public class GoodsConfig {

    /**
     * 奖品发放策略组
     */
    protected static Map<Integer, IDistributionGoods> goodsMap = new ConcurrentHashMap<>();

    @Resource
    private CouponGoods couponGoods;

    @Resource
    private DescGoods descGoods;

    @Resource
    private PhysicalGoods physicalGoods;

    @Resource
    private RedeemCodeGoods redeemCodeGoods;


    @PostConstruct
    public void init() {
        goodsMap.put(Constants.AwardType.CouponGoods.getCode(), couponGoods);
        goodsMap.put(Constants.AwardType.DESC.getCode(), descGoods);
        goodsMap.put(Constants.AwardType.PhysicalGoods.getCode(), physicalGoods);
        goodsMap.put(Constants.AwardType.RedeemCodeGoods.getCode(), redeemCodeGoods);
    }

}

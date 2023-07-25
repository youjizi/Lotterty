package com.libai.lottery.domain.award.repository;

/**
 * @description: 奖品发放仓储
 * @author： 有骥子
 * @date: 2023/7/9
 */
public interface IAwardRepository {

    /**
     * 更新奖品发放状态
     *
     * @param uId               用户ID
     * @param orderId           订单ID
     * @param awardId           奖品ID
     * @param awardState        奖品状态
     */
    void updateUserGrantAwardSate(String uId, Long orderId, String awardId, Integer awardState);
}

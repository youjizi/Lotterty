package com.libai.lottery.domain.strategy.model.vo;

/**
 * @description: 获奖奖品信息
 * @author： 有骥子
 * @date: 2023/7/5
 */
public class DrawAwardInfo {


    /**
     * 奖品ID
     */
    private String awardId;

    /**
     * 奖品名称
     */
    private String awardName;


    public DrawAwardInfo() {
    }

    public DrawAwardInfo(String awardId, String awardName) {
        this.awardId = awardId;
        this.awardName = awardName;
    }

    public String getAwardId() {
        return awardId;
    }

    public void setAwardId(String awardId) {
        this.awardId = awardId;
    }

    public String getAwardName() {
        return awardName;
    }

    public void setAwardName(String awardName) {
        this.awardName = awardName;
    }
}

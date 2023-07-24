package com.libai.lottery.domain.strategy.model.vo;

import java.util.Date;

/**
 * @description: 获奖奖品信息
 * @author： 有骥子
 * @date: 2023/7/5
 */
public class DrawAwardInfoVO {


    /**
     * 用户ID
     */
    private String uId;

    /**
     * 奖品ID
     */
    private String awardId;

    /**
     * 奖品名称
     */
    private String awardName;

    /**
     * 奖品类型
     */
    private Integer awardType;

    /**
     * 奖品内容
     */
    private String awardContent;

    /**
     * 策略方式（1:单项概率、2:总体概率）
     */
    private Integer strategyMode;

    /**
     * 发放奖品方式（1:即时、2:定时[含活动结束]、3:人工）
     */
    private Integer grantType;
    /**
     * 发奖时间
     */
    private Date grantDate;


    public DrawAwardInfoVO() {
    }

    public DrawAwardInfoVO(String awardId, String awardName) {
        this.awardId = awardId;
        this.awardName = awardName;
    }

    public DrawAwardInfoVO(String awardId, String awardName, Integer awardType) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardType = awardType;
    }

    public DrawAwardInfoVO(String awardId, String awardName, Integer awardType, String awardContent) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardType = awardType;
        this.awardContent = awardContent;
    }

    public DrawAwardInfoVO(String awardId, String awardName, Integer awardType, String awardContent, Integer strategyMode, Integer grantType, Date grantDate) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardType = awardType;
        this.awardContent = awardContent;
        this.strategyMode = strategyMode;
        this.grantType = grantType;
        this.grantDate = grantDate;
    }

    public DrawAwardInfoVO(String uId, String awardId, String awardName, Integer awardType, String awardContent, Integer strategyMode, Integer grantType, Date grantDate) {
        this.uId = uId;
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardType = awardType;
        this.awardContent = awardContent;
        this.strategyMode = strategyMode;
        this.grantType = grantType;
        this.grantDate = grantDate;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
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

    public Integer getAwardType() {
        return awardType;
    }

    public void setAwardType(Integer awardType) {
        this.awardType = awardType;
    }

    public String getAwardContent() {
        return awardContent;
    }

    public void setAwardContent(String awardContent) {
        this.awardContent = awardContent;
    }

    public Date getGrantDate() {
        return grantDate;
    }

    public void setGrantDate(Date grantDate) {
        this.grantDate = grantDate;
    }

    public Integer getStrategyMode() {
        return strategyMode;
    }

    public void setStrategyMode(Integer strategyMode) {
        this.strategyMode = strategyMode;
    }

    public Integer getGrantType() {
        return grantType;
    }

    public void setGrantType(Integer grantType) {
        this.grantType = grantType;
    }
}

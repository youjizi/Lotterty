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

    /**
     * 奖品类型
     */
    private Integer awardType;


    /**
     * 奖品内容
     */
    private String awardContent;


    public DrawAwardInfo() {
    }

    public DrawAwardInfo(String awardId, String awardName) {
        this.awardId = awardId;
        this.awardName = awardName;
    }

    public DrawAwardInfo(String awardId, String awardName, Integer awardType) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardType = awardType;
    }

    public DrawAwardInfo(String awardId, String awardName, Integer awardType, String awardContent) {
        this.awardId = awardId;
        this.awardName = awardName;
        this.awardType = awardType;
        this.awardContent = awardContent;
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
}

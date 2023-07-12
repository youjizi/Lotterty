package com.libai.lottery.domain.activity.model.vo;

/**
 * @description: 变更活动状态对象
 * @author： 有骥子
 * @date: 2023/7/11
 */
public class AlterStateVO {

    /**
     * 活动ID
     */
    private Long activityId;

    /**
     * 当前状态
     */
    private Integer currentState;

    /**
     * 变更目标状态
     */
    private Integer targetState;

    public AlterStateVO() {
    }

    public AlterStateVO(Long activityId, Integer currentState, Integer targetState) {
        this.activityId = activityId;
        this.currentState = currentState;
        this.targetState = targetState;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Integer getCurrentState() {
        return currentState;
    }

    public void setCurrentState(Integer currentState) {
        this.currentState = currentState;
    }

    public Integer getTargetState() {
        return targetState;
    }

    public void setTargetState(Integer targetState) {
        this.targetState = targetState;
    }
}

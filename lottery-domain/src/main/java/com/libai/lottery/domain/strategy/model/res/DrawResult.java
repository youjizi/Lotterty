package com.libai.lottery.domain.strategy.model.res;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.strategy.model.vo.DrawAwardInfoVO;

/**
 * @description: 抽奖结果
 * @author： 有骥子
 * @date: 2023/7/2
 */
public class DrawResult {

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 抽奖策略ID
     */
    private Long strategyId;

    /**
     * 中奖状态：0未中奖、1已中奖、2兜底奖 Constants.DrawState
     */
    private Integer drawState = Constants.DrawState.FAIL.getCode();

    /**
     * 中奖奖品信息
     */
    private DrawAwardInfoVO drawAwardInfoVO;


    public DrawResult() {
    }

    public DrawResult(String uId, Long strategyId, Integer drawState) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
    }

    public DrawResult(String uId, Long strategyId, Integer drawState, DrawAwardInfoVO drawAwardInfoVO) {
        this.uId = uId;
        this.strategyId = strategyId;
        this.drawState = drawState;
        this.drawAwardInfoVO = drawAwardInfoVO;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getStrategyId() {
        return strategyId;
    }

    public void setStrategyId(Long strategyId) {
        this.strategyId = strategyId;
    }

    public Integer getDrawState() {
        return drawState;
    }

    public void setDrawState(Integer drawState) {
        this.drawState = drawState;
    }

    public DrawAwardInfoVO getDrawAwardInfo() {
        return drawAwardInfoVO;
    }

    public void setDrawAwardInfo(DrawAwardInfoVO drawAwardInfoVO) {
        this.drawAwardInfoVO = drawAwardInfoVO;
    }
}

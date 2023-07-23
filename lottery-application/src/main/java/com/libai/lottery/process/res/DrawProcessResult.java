package com.libai.lottery.process.res;

import com.libai.lottery.common.Result;
import com.libai.lottery.domain.strategy.model.vo.DrawAwardInfoVO;

/**
 * @description: 抽奖结果
 * @author： 有骥子
 * @date: 2023/7/20
 */
public class DrawProcessResult extends Result {

    private DrawAwardInfoVO drawAwardInfoVO;


    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardInfoVO drawAwardInfoVO) {
        super(code, info);
        this.drawAwardInfoVO = drawAwardInfoVO;
    }

    public DrawAwardInfoVO getDrawAwardInfo() {
        return drawAwardInfoVO;
    }

    public void setDrawAwardInfo(DrawAwardInfoVO drawAwardInfoVO) {
        this.drawAwardInfoVO = drawAwardInfoVO;
    }
}

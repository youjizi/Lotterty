package com.libai.lottery.process.res;

import com.libai.lottery.common.Result;
import com.libai.lottery.domain.strategy.model.vo.DrawAwardInfo;

/**
 * @description: 抽奖结果
 * @author： 有骥子
 * @date: 2023/7/20
 */
public class DrawProcessResult extends Result {

    private DrawAwardInfo drawAwardInfo;


    public DrawProcessResult(String code, String info) {
        super(code, info);
    }

    public DrawProcessResult(String code, String info, DrawAwardInfo drawAwardInfo) {
        super(code, info);
        this.drawAwardInfo = drawAwardInfo;
    }

    public DrawAwardInfo getDrawAwardInfo() {
        return drawAwardInfo;
    }

    public void setDrawAwardInfo(DrawAwardInfo drawAwardInfo) {
        this.drawAwardInfo = drawAwardInfo;
    }
}

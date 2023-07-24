package libai.lottery.rpc;

import libai.lottery.rpc.req.DrawReq;
import libai.lottery.rpc.req.QuantificationDrawReq;
import libai.lottery.rpc.res.DrawRes;

/**
 * @description: 抽奖活动展台接口
 * @author： 有骥子
 * @date: 2023/7/23
 */
public interface ILotteryActivityBooth {

    /**
     * 指定活动抽奖
     * @param drawReq 请求参数
     * @return 抽奖结果
     */
    DrawRes doDraw(DrawReq drawReq);

    /**
     * 量化人群抽奖
     * @param quantificationDrawReq 请求参数
     * @return 抽奖结果
     */
    DrawRes doQuantificationDraw(QuantificationDrawReq quantificationDrawReq);
}

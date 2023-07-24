package libai.lottery.rpc.res;

import com.libai.lottery.common.Result;
import libai.lottery.rpc.dto.AwardDTO;

import java.io.Serializable;

/**
 * @description: 抽奖结果
 * @author： 有骥子
 * @date: 2023/7/23
 */
public class DrawRes extends Result implements Serializable {

    private AwardDTO awardDTO;

    public DrawRes(String code, String info) {
        super(code, info);
    }

    public AwardDTO getAwardDTO() {
        return awardDTO;
    }

    public void setAwardDTO(AwardDTO awardDTO) {
        this.awardDTO = awardDTO;
    }
}

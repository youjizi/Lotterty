package libai.lottery.rpc.req;

import java.io.Serializable;

/**
 * @description: 抽奖请求
 * @author： 有骥子
 * @date: 2023/7/23
 */
public class DrawReq  implements Serializable {

    /**
     * 用户ID
     */
    private String uId;

    /**
     * 活动ID
     */
    private Long activityId;


    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }
}

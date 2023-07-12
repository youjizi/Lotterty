package com.libai.lottery.domain.activity.service.stateflow;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.activity.service.stateflow.event.*;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: 状态处理配置
 * @author： 有骥子
 * @date: 2023/7/11
 */
public class StateConfig {

    @Resource
    private ArraignmentState arraignmentState;

    @Resource
    private CloseState closeState;

    @Resource
    private DoingState doingState;

    @Resource
    private EditingState editingState;

    @Resource
    private OpenState openState;

    @Resource
    private PassState passState;

    @Resource
    private RefuseState refuseState;

    protected  Map<Enum<Constants.ActivityState>, AbstractState> stateGroup = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        stateGroup.put(Constants.ActivityState.ARRAIGNMENT, arraignmentState);
         stateGroup.put(Constants.ActivityState.CLOSE , closeState);
         stateGroup.put(Constants.ActivityState.DOING , doingState);
         stateGroup.put(Constants.ActivityState.EDIT , editingState);
         stateGroup.put(Constants.ActivityState.OPEN , openState);
         stateGroup.put(Constants.ActivityState.PASS , passState);
         stateGroup.put(Constants.ActivityState.REFUSE , refuseState);
    }

}

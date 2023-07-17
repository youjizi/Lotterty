package com.libai.lottery.domain.support.ids.policy;

import com.libai.lottery.domain.support.ids.IIdGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

/**
 * @description: 随机算法，用于生成策略ID  工具类生成 org.apache.commons.lang3.RandomStringUtils
 * @author： 有骥子
 * @date: 2023/7/12
 */

@Component
public class RandomNumeric implements IIdGenerator {

    @Override
    public long nextId() {
        return Long.parseLong(RandomStringUtils.randomNumeric(11));
    }
}

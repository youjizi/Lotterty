package com.libai.lottery.domain.support.ids;

import com.libai.lottery.common.Constants;
import com.libai.lottery.domain.support.ids.policy.RandomNumeric;
import com.libai.lottery.domain.support.ids.policy.ShortCode;
import com.libai.lottery.domain.support.ids.policy.SnowFlake;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: 策略服务上下文
 * @author： 有骥子
 * @date: 2023/7/12
 */

@Configuration
public class IdContext {

    @Bean
    public Map<Constants.IDs, IIdGenerator> idGenerator(SnowFlake snowFlake, ShortCode shortCode, RandomNumeric randomNumeric) {
        Map<Constants.IDs, IIdGenerator> idGeneratorMap = new HashMap<>(8);
        idGeneratorMap.put(Constants.IDs.SnowFlake, snowFlake);
        idGeneratorMap.put(Constants.IDs.ShortCode, shortCode);
        idGeneratorMap.put(Constants.IDs.RandomNumeric, randomNumeric);
        return idGeneratorMap;
    }
}

package com.libai.lottery.domain.support.ids.policy;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import com.libai.lottery.domain.support.ids.IIdGenerator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description: 雪花算法，用于生成单号
 *               hutool 工具包下的雪花算法，15位雪花算法推荐：https://github.com/yitter/idgenerator/blob/master/Java/source/src/main/java/com/github/yitter/core/SnowWorkerM1.java
 * @author： 有骥子
 * @date: 2023/7/12
 */

@Component
public class SnowFlake implements IIdGenerator {


    private Snowflake snowflake;

    @PostConstruct
    public void init() {
        // 0-31位， 可以采用配置的方式使用
        long workerId;
        try {
            String localhostStr = NetUtil.getLocalhostStr();
            workerId = NetUtil.ipv4ToLong(localhostStr);
        } catch (Exception e) {
            workerId = NetUtil.getLocalhostStr().hashCode();
        }
        workerId = workerId >> 16 & 31;

        long dataCenterId = 1L;
        snowflake = IdUtil.createSnowflake(workerId, dataCenterId);
    }


    @Override
    public synchronized long nextId() {
        return snowflake.nextId();
    }
}

package com.libai.lottery.domain.support.ids;

/**
 * @description:
 * @author： 有骥子
 * @date: 2023/7/12
 */
public interface IIdGenerator {

    /**
     * 获取ID，目前有两种实现方式
     * 1. 雪花算法，用于生成单号
     * 2. 日期算法，用于生成活动编号类，特性是生成数字串较短，但指定时间内不能生成太多
     * 3. 随机算法，用于生成策略ID
     *
     * @return ID
     */

    long nextId();

}

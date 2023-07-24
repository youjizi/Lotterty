package com.libai.lottery.interfaces.assembler;

import com.libai.lottery.domain.strategy.model.vo.DrawAwardInfoVO;
import libai.lottery.rpc.dto.AwardDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;


/**
 * @description: 对象转换配置
 * @author： 有骥子
 * @date: 2023/7/23
 */

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AwardMapping extends IMapping<DrawAwardInfoVO, AwardDTO>{

    @Mapping(target = "userId", source = "uId")
    @Override
    AwardDTO sourceToTarget(DrawAwardInfoVO var1);

    @Override
    DrawAwardInfoVO targetToSource(AwardDTO var1);
}

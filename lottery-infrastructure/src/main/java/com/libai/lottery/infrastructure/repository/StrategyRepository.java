package com.libai.lottery.infrastructure.repository;

import com.libai.lottery.domain.strategy.model.aggregates.StrategyRich;
import com.libai.lottery.domain.strategy.model.vo.AwardBriefVO;
import com.libai.lottery.domain.strategy.model.vo.StrategyBriefVO;
import com.libai.lottery.domain.strategy.model.vo.StrategyDetailBriefVO;
import com.libai.lottery.domain.strategy.repository.IStrategyRepository;
import com.libai.lottery.infrastructure.dao.IAwardDao;
import com.libai.lottery.infrastructure.dao.IStrategyDao;
import com.libai.lottery.infrastructure.dao.IStrategyDetailDao;
import com.libai.lottery.infrastructure.po.Award;
import com.libai.lottery.infrastructure.po.Strategy;
import com.libai.lottery.infrastructure.po.StrategyDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 策略表仓储服务
 * @author： 有骥子
 * @date: 2023/7/3
 */

@Component
public class StrategyRepository implements IStrategyRepository {

    @Resource
    IStrategyDao strategyDao;

    @Resource
    IStrategyDetailDao strategyDetailDao;

    @Resource
    IAwardDao awardDao;


    @Override
    public StrategyRich queryStrategyRich(Long strategyId) {

        Strategy strategy = strategyDao.queryStrategyById(strategyId);
        List<StrategyDetail> strategyDetailList = strategyDetailDao.queryStrategyDetailList(strategyId);

        StrategyBriefVO strategyBriefVO = new StrategyBriefVO();
        BeanUtils.copyProperties(strategy, strategyBriefVO);

        List<StrategyDetailBriefVO> strategyDetailBriefVOList = new ArrayList<>();
        for (StrategyDetail strategyDetail : strategyDetailList) {
            StrategyDetailBriefVO strategyDetailBriefVO = new StrategyDetailBriefVO();
            BeanUtils.copyProperties(strategyDetail, strategyDetailBriefVO);
            strategyDetailBriefVOList.add(strategyDetailBriefVO);
        }



        return new StrategyRich(strategyId,strategyBriefVO, strategyDetailBriefVOList);
    }

    @Override
    public AwardBriefVO queryAwardInfo(String awardId) {
        Award award = awardDao.queryAwardInfo(awardId);
        AwardBriefVO awardBriefVO = new AwardBriefVO();
        BeanUtils.copyProperties(award, awardBriefVO);
        return awardBriefVO;
    }

    @Override
    public List<String> queryNoStockStrategyAwardList(Long strategyId) {
        return strategyDetailDao.queryNoStockStrategyAwardList(strategyId);
    }

    @Override
    public boolean deductStock(Long strategyId, String awardId) {
        StrategyDetail req = new StrategyDetail();
        req.setStrategyId(strategyId);
        req.setAwardId(awardId);
        int count = strategyDetailDao.deductStock(req);
        return count == 1;
    }
}

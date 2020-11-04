package com.sinosoft.visualization.business.service.serviceImpl;

import com.sinosoft.visualization.business.api.vo.TodayOverviewVO;
import com.sinosoft.visualization.business.repository.AppVisDataRepository;
import com.sinosoft.visualization.business.repository.PcVisDataRepository;
import com.sinosoft.visualization.business.repository.WapVisDataRepository;
import com.sinosoft.visualization.business.service.TodayVisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import static com.sinosoft.visualization.common.basic.constants.CommonConstants.*;

/**
 * TodayVisServiceImpl
 *
 * @author wangyl
 * Description:
 * Created in: 2020/11/3
 * Modified by:
 */
@Service
public class TodayVisServiceImpl implements TodayVisService {

    @Autowired
    private AppVisDataRepository appVisDataRepository;

    @Autowired
    private PcVisDataRepository pcVisDataRepository;

    @Autowired
    private WapVisDataRepository wapVisDataRepository;

    /**
     * 获取T+1各平台各指标概览数据
     */
    @Override
    public List<TodayOverviewVO> getTodayOverview() {
        // APP
        List<Map<String, Object>> appInfoList = appVisDataRepository.getAppInfo();
        TodayOverviewVO appOverviewVO = new TodayOverviewVO();
        appOverviewVO.setTodayOverviewMap(appInfoList);
        appOverviewVO.setPlatformName(APP);
        // PC
        List<Map<String, Object>> pcInfoList = pcVisDataRepository.getPcInfo();
        TodayOverviewVO pcOverviewVO = new TodayOverviewVO();
        pcOverviewVO.setTodayOverviewMap(pcInfoList);
        pcOverviewVO.setPlatformName(PC);
        // WAP
        List<Map<String, Object>> wapInfoList = wapVisDataRepository.getWapInfo();
        TodayOverviewVO wapOverviewVO = new TodayOverviewVO();
        wapOverviewVO.setTodayOverviewMap(wapInfoList);
        wapOverviewVO.setPlatformName(WAP);
        // 组装数据
        List<TodayOverviewVO> todayOverviewVOList = new ArrayList<TodayOverviewVO>() {{
            add(appOverviewVO);
            add(pcOverviewVO);
            add(wapOverviewVO);
        }};

        return todayOverviewVOList;
    }

}
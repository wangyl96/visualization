package com.sinosoft.visualization.business.service.serviceImpl;

import com.sinosoft.visualization.business.api.entity.AppVisData;
import com.sinosoft.visualization.business.api.entity.PcVisData;
import com.sinosoft.visualization.business.api.entity.QuotaDic;
import com.sinosoft.visualization.business.api.entity.WapVisData;
import com.sinosoft.visualization.business.api.vo.TodayOverviewVO;
import com.sinosoft.visualization.business.repository.AppVisDataRepository;
import com.sinosoft.visualization.business.repository.PcVisDataRepository;
import com.sinosoft.visualization.business.repository.QuotaDicRepository;
import com.sinosoft.visualization.business.repository.WapVisDataRepository;
import com.sinosoft.visualization.business.service.TodayVisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public TodayOverviewVO getTodayOverview() {
        // APP
        List<AppVisData> appVisDataList = appVisDataRepository.getOverview();
        TodayOverviewVO todayOverviewVO = new TodayOverviewVO();
        todayOverviewVO.setVisDate(appVisDataList.get(0).getVisDate().toString());
        List<Map> todayOverviewMap = new ArrayList<>();

        Map<String, Object> appAzMap= new HashMap<>();
        appAzMap.put("contentName", "安装量");
        appAzMap.put("contentData",  appVisDataList.get(0).getAppInstallation());
        appAzMap.put("ratio", getLinkRelative(appVisDataList.get(0).getAppInstallation(), appVisDataList.get(1).getAppInstallation()));
        appAzMap.put("isUp", appVisDataList.get(0).getAppInstallation() >= appVisDataList.get(1).getAppInstallation());
        todayOverviewMap.add(appAzMap);

        Map<String, Object> appZcMap= new HashMap<>();
        appZcMap.put("contentName", "注册量");
        appZcMap.put("contentData",  appVisDataList.get(0).getRegistrations());
        appZcMap.put("ratio", getLinkRelative(appVisDataList.get(0).getRegistrations(), appVisDataList.get(1).getRegistrations()));
        appZcMap.put("isUp", appVisDataList.get(0).getRegistrations() >= appVisDataList.get(1).getRegistrations());
        todayOverviewMap.add(appZcMap);

        Map<String, Object> appRhMap= new HashMap<>();
        appRhMap.put("contentName", "日活");
        appRhMap.put("contentData",  appVisDataList.get(0).getLifeDay());
        appRhMap.put("ratio", getLinkRelative(appVisDataList.get(0).getLifeDay(), appVisDataList.get(1).getLifeDay()));
        appRhMap.put("isUp", appVisDataList.get(0).getLifeDay() >= appVisDataList.get(1).getLifeDay());
        todayOverviewMap.add(appRhMap);

        // PC
        List<PcVisData> pcVisDataList =  pcVisDataRepository.getOverview();
        Map<String, Object> pcFwMap= new HashMap<>();
        appAzMap.put("contentName", "访问量");
        appAzMap.put("contentData",  pcVisDataList.get(0).getVisitsOld() + pcVisDataList.get(0).getVisitsNew());
        appAzMap.put("ratio", getLinkRelative(pcVisDataList.get(0).getVisitsOld() + pcVisDataList.get(0).getVisitsNew(), pcVisDataList.get(1).getVisitsOld() + pcVisDataList.get(1).getVisitsNew()));
        appAzMap.put("isUp", pcVisDataList.get(0).getVisitsOld() + pcVisDataList.get(0).getVisitsNew() >= pcVisDataList.get(1).getVisitsOld() + pcVisDataList.get(1).getVisitsNew());
        todayOverviewMap.add(appAzMap);

        Map<String, Object> pcZcMap= new HashMap<>();
        appAzMap.put("contentName", "访问量");
        appAzMap.put("contentData",  pcVisDataList.get(0).getVisitsOld() + pcVisDataList.get(0).getVisitsNew());
        appAzMap.put("ratio", getLinkRelative(pcVisDataList.get(0).getVisitsOld() + pcVisDataList.get(0).getVisitsNew(), pcVisDataList.get(1).getVisitsOld() + pcVisDataList.get(1).getVisitsNew()));
        appAzMap.put("isUp", pcVisDataList.get(0).getVisitsOld() + pcVisDataList.get(0).getVisitsNew() >= pcVisDataList.get(1).getVisitsOld() + pcVisDataList.get(1).getVisitsNew());
        todayOverviewMap.add(appAzMap);

        Map<String, Object> pcRhMap= new HashMap<>();
        appRhMap.put("contentName", "注册量");
        appRhMap.put("contentData",  appVisDataList.get(0).getLifeDay());
        appRhMap.put("ratio", getLinkRelative(appVisDataList.get(0).getLifeDay(), appVisDataList.get(1).getLifeDay()));
        appRhMap.put("isUp", appVisDataList.get(0).getLifeDay() >= appVisDataList.get(1).getLifeDay());
        todayOverviewMap.add(appRhMap);
        // WAP
        List<WapVisData> wapVisDataList = wapVisDataRepository.getOverview();
        // 组装数据
        List<TodayOverviewVO> todayOverviewVOList = new ArrayList<>();

        // 根据平台查
        return null;
    }

    /**
     * r1今日数据
     * r2昨日数据
     * @param r1
     * @param r2
     * @return
     */
    private double getLinkRelative(Long r1, Long r2) {
        // 环比计算公式 (今日-昨日)/昨日 * 100
        return (r1 - r2) / r2 * 100;
    }
}

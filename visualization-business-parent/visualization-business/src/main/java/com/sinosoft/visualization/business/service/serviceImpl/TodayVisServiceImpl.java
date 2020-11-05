package com.sinosoft.visualization.business.service.serviceImpl;

import com.sinosoft.visualization.business.api.dto.MapDataDTO;
import com.sinosoft.visualization.business.api.entity.AppComVisData;
import com.sinosoft.visualization.business.api.vo.MapDataVO;
import com.sinosoft.visualization.business.api.vo.TodayOverviewVO;
import com.sinosoft.visualization.business.repository.AppComVisDataRepository;
import com.sinosoft.visualization.business.repository.AppVisDataRepository;
import com.sinosoft.visualization.business.repository.PcVisDataRepository;
import com.sinosoft.visualization.business.repository.WapVisDataRepository;
import com.sinosoft.visualization.business.service.TodayVisService;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private AppComVisDataRepository appComVisDataRepository;



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

    /**
     * 获取各平台及指标编码和名称
     * @return
     */
    @Override
    public List<TodayOverviewVO> getTabData() {
        return null;
    }

    /**
     * 获取各平台及指标地图数据
     * @param mapDataDTO
     * @return
     */
    @Override
    public MapDataVO getTodayMapData(MapDataDTO mapDataDTO) {
        String platformCode = mapDataDTO.getPlatformCode();
        List<Map<String, Integer>> mapData = new ArrayList<>();
        // 判断平台
        switch (platformCode) {
            case "app":
                // 查APP分公司数据表
                if (StringUtils.equals("app_installation", mapDataDTO.getQuotaCode())) {
                    mapData = appComVisDataRepository.getInstallMapData("2020-09-18");
                } else if (StringUtils.equals("registrations", mapDataDTO.getQuotaCode())) {
                    mapData = appComVisDataRepository.getRegistMapData( "2020-09-18");
                }
                break;
            default:
                // 初APP外目前没有其他分公司数据
                break;
        }
        // 指标MAP
        HashMap<String, String> quotaMap =new HashMap<String, String>() {
            {
                put("name", mapDataDTO.getQuotaName());
                put("code", mapDataDTO.getQuotaCode());
            }
        };
        return new MapDataVO().setMap(mapData).setQuota(quotaMap);
    }

}
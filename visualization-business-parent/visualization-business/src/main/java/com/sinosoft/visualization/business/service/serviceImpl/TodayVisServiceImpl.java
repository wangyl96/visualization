package com.sinosoft.visualization.business.service.serviceImpl;

import com.sinosoft.visualization.business.api.dto.MapDataDTO;
import com.sinosoft.visualization.business.api.dto.OverviewDTO;
import com.sinosoft.visualization.business.api.dto.PieDataDto;
import com.sinosoft.visualization.business.api.vo.BarDataViewVo;
import com.sinosoft.visualization.business.api.vo.MapDataVO;
import com.sinosoft.visualization.business.api.vo.OldPieDataViewVo;
import com.sinosoft.visualization.business.api.vo.TodayOverviewVO;
import com.sinosoft.visualization.business.repository.*;
import com.sinosoft.visualization.business.service.TodayVisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

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


    @Autowired
    private SysDictRepository sysDictRepository;


    /**
     * 获取T+1各平台各指标概览数据
     */
    @Override
    public List<TodayOverviewVO> getTodayOverview(OverviewDTO overviewDTO) {
        // APP
        List<Map<String, Object>> appInfoList = appVisDataRepository.getAppInfo(overviewDTO.getQueryDate(), overviewDTO.getRatioDate());
        TodayOverviewVO appOverviewVO = new TodayOverviewVO();
        appOverviewVO.setTodayOverviewMap(appInfoList);
        appOverviewVO.setPlatformName(APP);
        // PC
        List<Map<String, Object>> pcInfoList = pcVisDataRepository.getPcInfo(overviewDTO.getQueryDate(), overviewDTO.getRatioDate());
        TodayOverviewVO pcOverviewVO = new TodayOverviewVO();
        pcOverviewVO.setTodayOverviewMap(pcInfoList);
        pcOverviewVO.setPlatformName(PC);
        // WAP
        List<Map<String, Object>> wapInfoList = wapVisDataRepository.getWapInfo(overviewDTO.getQueryDate(), overviewDTO.getRatioDate());
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
     *
     * @return
     */
    @Override
    public List<TodayOverviewVO> getTabData() {
        return null;
    }

    /**
     * 获取各平台及指标地图数据
     *
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
                    mapData = appComVisDataRepository.getInstallMapData(mapDataDTO.getQueryDate());
                } else if (StringUtils.equals("registrations", mapDataDTO.getQuotaCode())) {
                    mapData = appComVisDataRepository.getRegistMapData(mapDataDTO.getQueryDate());
                }
                break;
            default:
                // 初APP外目前没有其他分公司数据
                break;
        }
        // 指标MAP
        HashMap<String, String> quotaMap = new HashMap<String, String>() {
            {
                put("name", mapDataDTO.getQuotaName());
                put("code", mapDataDTO.getQuotaCode());
            }
        };
        return new MapDataVO().setMap(mapData).setQuota(quotaMap);
    }

    /**
     * 获取饼图的数据
     *
     * @return
     */
    @Override
    public List<BarDataViewVo> getAppData(PieDataDto pieDataDto) {

        String yestDay = pieDataDto.getQueryDate();
        String beforeYestDay = pieDataDto.getRatioDate();
        List<BarDataViewVo> barDataViewVoList = new ArrayList<>();
        List<Map<String, Object>> appDatas = appVisDataRepository.getAppDatas(yestDay, yestDay);
        // 加入app的数据
        if (appDatas.size() > 0) {
            BarDataViewVo barDataViewVo = new BarDataViewVo();
            barDataViewVo.setBarViewMap(appDatas);
            barDataViewVo.setPlatForm("APP");
            // 获取环比
            BigDecimal yestDaySum = new BigDecimal(appVisDataRepository.getMom(yestDay).toString());
            BigDecimal beforeDaySum = new BigDecimal(appVisDataRepository.getMom(beforeYestDay).toString());
            double mom = ((yestDaySum.subtract(beforeDaySum)).divide(beforeDaySum, 2, BigDecimal.ROUND_HALF_UP)).doubleValue();
            barDataViewVo.setMom(mom);
            barDataViewVoList.add(barDataViewVo);
            // 加入图例
            List<Map<String, Object>> legends = appVisDataRepository.getLegends("app");
            if (legends.size() > 0)
                barDataViewVo.setLegend(legends);
        }

        // 获取pc数据
        List<Map<String, Object>> pcDatas = pcVisDataRepository.getPcDatas(yestDay, yestDay);
        // 加入pc的数据
        if (pcDatas.size() > 0) {
            BarDataViewVo barDataViewVo = new BarDataViewVo();
            barDataViewVo.setBarViewMap(pcDatas);
            barDataViewVo.setPlatForm("PC");
            // 获取环比
            BigDecimal yestDaySum = new BigDecimal(pcVisDataRepository.getPcMom(yestDay).toString());
            BigDecimal beforeDaySum = new BigDecimal(pcVisDataRepository.getPcMom(beforeYestDay).toString());
            double mom = ((yestDaySum.subtract(beforeDaySum)).divide(beforeDaySum, 2, BigDecimal.ROUND_HALF_UP)).doubleValue();
            barDataViewVo.setMom(mom);
            barDataViewVoList.add(barDataViewVo);
            // 加入图标
            List<Map<String, Object>> legends = appVisDataRepository.getLegends("pc");
            if (legends.size() > 0) {
                barDataViewVo.setLegend(legends);
            }
        }


        //获取wap的数据
        List<Map<String, Object>> wapDatas = wapVisDataRepository.getWapDatas(yestDay, yestDay);
        // 加入wap的数据
        if (wapDatas.size() > 0) {
            BarDataViewVo barDataViewVo = new BarDataViewVo();
            barDataViewVo.setBarViewMap(wapDatas);
            barDataViewVo.setPlatForm("WAP");
            // 获取环比
            BigDecimal yestDaySum = new BigDecimal(wapVisDataRepository.getWapMom(yestDay).toString());
            BigDecimal beforeDaySum = new BigDecimal(wapVisDataRepository.getWapMom(beforeYestDay).toString());
            double mom = ((yestDaySum.subtract(beforeDaySum)).divide(beforeDaySum, 2, BigDecimal.ROUND_HALF_UP)).doubleValue();
            barDataViewVo.setMom(mom);
            barDataViewVoList.add(barDataViewVo);

            // 加入图标
            List<Map<String, Object>> legends = appVisDataRepository.getLegends("wap");
            if (legends.size() > 0) {
                barDataViewVo.setLegend(legends);
            }
        }
        return barDataViewVoList;


    }

}
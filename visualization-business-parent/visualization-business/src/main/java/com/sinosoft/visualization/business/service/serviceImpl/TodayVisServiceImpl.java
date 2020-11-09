package com.sinosoft.visualization.business.service.serviceImpl;

import com.sinosoft.visualization.business.api.dto.MapDataDTO;
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
                    mapData = appComVisDataRepository.getInstallMapData("2020-10-09");
                } else if (StringUtils.equals("registrations", mapDataDTO.getQuotaCode())) {
                    mapData = appComVisDataRepository.getRegistMapData("2020-10-09");
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
     * 获取各个饼图的数据（弃用）
     *
     * @return
     */
    @Override
    public List<OldPieDataViewVo> getAppMoney() {

        // 获取昨天的日期
        // LocalDate yestDay = LocalDate.now().plusDays(-1);
        // 测试昨天日期
        LocalDate yestDay = LocalDate.of(2020, 01, 05);

        // 获取前天的日期
        //LocalDate beforeYestDay = LocalDate.now().plusDays(-2);

        // 测试前天日期
        LocalDate beforeYestDay = LocalDate.of(2020, 01, 05);

        List<OldPieDataViewVo> data = new ArrayList<>();

        //获取所有的平台
        List<String> platFroms = sysDictRepository.getPlatFrom();

        if (!Objects.isNull(platFroms)) {
            for (String platForm : platFroms) {
                List<Map<String, Object>> appMoneys = new ArrayList<>();
                OldPieDataViewVo oldPieDataViewVo = new OldPieDataViewVo();
                oldPieDataViewVo.setPlatformName(platForm);
                oldPieDataViewVo.setVisDate(yestDay.toString());
                appMoneys = appVisDataRepository.getAppMoney(platForm, yestDay);
                oldPieDataViewVo.setTodayOverviewMap(appMoneys);

                // 获取环比值
                if (appMoneys.size() > 0) {

                    // 昨天的总和
                    Map<String, Double> yestDayData = appVisDataRepository.getSumMoney(platForm, yestDay);
                    // 前天的总和
                    Map<String, Double> beforeYestDayData = appVisDataRepository.getSumMoney(platForm, beforeYestDay);

                    if (!Objects.isNull(yestDayData) && !Objects.isNull(beforeYestDayData)) {

                        BigDecimal beforeDayValue = new BigDecimal(beforeYestDayData.get("value"));
                        BigDecimal yestDayValue = new BigDecimal(yestDayData.get("value"));
                        // 获取比较值

                        if (beforeDayValue.compareTo(yestDayValue) >= -1 || beforeDayValue.compareTo(yestDayValue) == 1) {
                            String divide = yestDayValue.divide(beforeDayValue, 2, RoundingMode.HALF_UP).toString();
                            oldPieDataViewVo.setMom(divide);
                            data.add(oldPieDataViewVo);
                        } else {
                            String divide = yestDayValue.divide(beforeDayValue, 2, RoundingMode.HALF_UP).toString();
                            oldPieDataViewVo.setMom("-" + divide);
                            data.add(oldPieDataViewVo);
                        }

                    }


                }

            }


        }
        return data;
    }

    /**
     * 获取饼图的数据
     *
     * @return
     */
    @Override
    public List<BarDataViewVo> getAppData(PieDataDto pieDataDto) {

        // 获取昨天的日期
        // LocalDate yestDay = LocalDate.now().plusDays(-1);
        // 测试昨天日期
        LocalDate yestDay = LocalDate.of(2020, 01, 03);
        // 获取前天的日期
        //LocalDate beforeYestDay = LocalDate.now().plusDays(-2);
        // 测试前天日期
        LocalDate beforeYestDay = LocalDate.of(2020, 01, 02);
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
        }
        return barDataViewVoList;


    }

}
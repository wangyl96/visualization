package com.sinosoft.visualization.business.controller;

import com.sinosoft.visualization.business.api.dto.MapDataDTO;
import com.sinosoft.visualization.business.api.dto.OverviewDTO;
import com.sinosoft.visualization.business.api.dto.PieDataDto;
import com.sinosoft.visualization.business.api.vo.BarDataViewVo;
import com.sinosoft.visualization.business.api.vo.MapDataVO;
import com.sinosoft.visualization.business.api.vo.OldPieDataViewVo;
import com.sinosoft.visualization.business.api.vo.TodayOverviewVO;
import com.sinosoft.visualization.business.service.TodayVisService;
import com.sinosoft.visualization.common.basic.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;


/**
 * TodayVisController
 * 今日可视化数据页面展示接口
 *
 * @author wangyl
 * Description:
 * Created in: 2020/11/2
 * Modified by:
 */
@Api(value = "可视化controller", tags = "可视化T+1数据可视化控制层")
@Slf4j
@ResponseResult
@RestController
@RequestMapping(value = "/business")
public class TodayVisController {

    @Autowired
    private TodayVisService todayVisService;


    /**
     * 获取今日各平台各指标概览数据
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/getTodayOverview")
    @ApiOperation(value = "获取今日各平台各指标概览数据", tags = {"获取今日各平台各指标概览数据"}, notes = "此处今日指的是前一天,即已T+1时间表示")
    public List<TodayOverviewVO> getTodayOverview(OverviewDTO overviewDTO) {
        log.info("------------------获取今日各平台各指标概览数据开始-----------------");
        List<TodayOverviewVO> todayOverviewVOList = todayVisService.getTodayOverview(overviewDTO);
        log.info("------------------获取今日各平台各指标概览数据结束-----------------");
        return todayOverviewVOList;
    }

    /**
     * 获取各平台及指标编码和名称
     *
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/getTabData")
    @ApiOperation(value = "获取各平台及指标编码和名称", tags = {"获取各平台及指标编码和名称"})
    public List<TodayOverviewVO> getTabData(OverviewDTO overviewDTO) {
        log.info("------------------获取各平台及指标编码和名称开始-----------------");
        List<TodayOverviewVO> todayOverviewVOList = todayVisService.getTabData();
        log.info("------------------获取各平台及指标编码和名称结束-----------------");
        return todayOverviewVOList;
    }

    /**
     * 获取各平台及指标地图数据
     *
     * @param mapDataDTO
     * @return
     */
    @ResponseBody
    @GetMapping(value = "/getTodayMapData")
    @ApiOperation(value = "获取各平台及指标地图数据", tags = {"获取各平台及指标编码和名称"})
    public MapDataVO getTodayMapData(MapDataDTO mapDataDTO) {
        log.info("------------------获取各平台及指标地图数据开始-----------------");
        MapDataVO mapDataVO = todayVisService.getTodayMapData(mapDataDTO);
        log.info("------------------获取各平台及指标地图数据结束-----------------");
        return mapDataVO;
    }

    /**
     * 获取今日的保险值
     * @param pieDataDto 昨天和前天
     * @return
     */
    @GetMapping("/getAppData")
    @ApiOperation(value = "获取今日数据的保险值", tags = "获取所有的今日数据")
    public List<BarDataViewVo> appMoneyData(PieDataDto pieDataDto) {
        log.info("-------------------获取今日数据的保险值开始------------------");
        List<BarDataViewVo> appData = todayVisService.getAppData(pieDataDto);
        log.info("-------------------获取今日数据的保险值结束------------------");
        return appData;
    }
}

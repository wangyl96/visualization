package com.sinosoft.visualization.business.controller;

import com.sinosoft.visualization.business.api.vo.TodayOverviewVO;
import com.sinosoft.visualization.business.service.TodayVisService;
import com.sinosoft.visualization.common.basic.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * TodayVisController
 * 今日可视化数据页面展示接口
 * @author wangyl
 * Description:
 * Created in: 2020/11/2
 * Modified by:
 */
@Api(value="可视化controller", tags = "可视化T+1数据可视化控制层")
@Slf4j
@ResponseResult
@RestController
@RequestMapping(value = "/business")
public class TodayVisController {

    @Autowired
    private TodayVisService todayVisService;

    @ResponseBody
    @GetMapping(value = "/getTodayOverview")
    @ApiOperation(value="获取今日各平台各指标概览数据", tags={"获取今日各平台各指标概览数据"}, notes="此处今日指的是前一天,即已T+1时间表示")
    public TodayOverviewVO getTodayOverview() {
        log.info("------------------获取今日各平台各指标概览数据开始-----------------");
        TodayOverviewVO todayOverviewVO = todayVisService.getTodayOverview();
        log.info("------------------获取今日各平台各指标概览数据结束-----------------");
        return todayOverviewVO;
    }
}

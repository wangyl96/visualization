package com.sinosoft.visualization.business.controller;

import com.sinosoft.visualization.business.api.vo.BarDataViewVo;
import com.sinosoft.visualization.business.api.vo.TodayOverviewVO;
import com.sinosoft.visualization.business.service.AppVisDataService;
import com.sinosoft.visualization.common.basic.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author wangb
 * @date 2020/11/2 16:25
 */
@Slf4j
@RequestMapping("/business")
@ResponseResult
@RestController
@Api(value = "保费数据统计饼图")
public class AppVisDataConroller {

    @Autowired
    private AppVisDataService appVisDataService;


    /**
     * 获取app的今日的保险值(弃用)
     * @return
     */
    @GetMapping("/appOldMoney")
    public List<TodayOverviewVO> appMoney() {
        return appVisDataService.getAppMoney();
    }


    /**
     * 获取保险值
     * @return
     */
    @GetMapping("/getAppData")
    @ApiOperation(value = "获取今日数据的保险值开始")
    public BarDataViewVo appMoneyData(){
        log.info("-------------------获取今日数据的保险值开始------------------");
        BarDataViewVo appData = appVisDataService.getAppData();
        log.info("-------------------获取今日数据的保险值结束------------------");
        return appData;
    }







}

package com.sinosoft.visualization.business.controller;

import com.sinosoft.visualization.common.basic.response.ResponseResult;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

/**
 * TodayVisController
 * 今日可视化数据页面展示接口
 * @author wangyl
 * Description:
 * Created in: 2020/11/2
 * Modified by:
 */
@Slf4j
@ResponseResult
@RestController
@RequestMapping(value = "/business")
public class TodayVisController {

    @ResponseBody
    @GetMapping(value = "/getTodayOverview")
    public String getTodayOverview() {
        log.info("----------------接口通畅-------------------");
        return "12323";
    }
}

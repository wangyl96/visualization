package com.sinosoft.visualization.business.controller;

import com.sinosoft.visualization.business.api.dto.YearAndHistoryDataDto;
import com.sinosoft.visualization.business.api.vo.YearDataVo;
import com.sinosoft.visualization.business.service.YearVisService;
import com.sinosoft.visualization.common.basic.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**今年数据化数据展示接口
 * @author wangb
 * @date 2020/11/10 9:42
 */
@Api(value = "今年可视化controller", tags = "今年可视化数据可视化控制层")
@Slf4j
@ResponseResult
@RestController
@RequestMapping(value = "/yearVis")
public class YearVisController {

    @Autowired
    private YearVisService yearVisService;

    /**
     * 获取各个平台的总总注册 日活 安装
     * @return
     */
    @ApiOperation(value = "各个平台总注册量，日活 安装",tags = "获取平台最新一年和总的安装，注册，日活")
    @GetMapping("getYearPlatData")
    public List<YearDataVo> getYearPlatFormData(YearAndHistoryDataDto yearAndHistoryDataDto){

        log.info("-----------------------开始获取平台最新一年和总的安装，注册，日活------------------------");
        List<YearDataVo> yearDataVoList = yearVisService.getYearPlatFormData(yearAndHistoryDataDto);
        log.info("-----------------------平台最新一年和总的安装，注册，日活获取结束------------------------");
        return yearDataVoList;
    }
}

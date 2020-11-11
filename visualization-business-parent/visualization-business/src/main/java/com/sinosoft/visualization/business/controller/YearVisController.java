package com.sinosoft.visualization.business.controller;

import com.sinosoft.visualization.business.api.dto.PieDataByMonDTO;
import com.sinosoft.visualization.business.api.dto.YearAndHistoryDataDto;
import com.sinosoft.visualization.business.api.vo.BarDataViewVo;
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

/**今年数据化数据展示接口
 * @author wangb
 * @date 2020/11/10 9:42
 */
@Api(value = "今年可视化controller", tags = "今年可视化数据可视化控制层")
@Slf4j
@ResponseResult
@RestController
@RequestMapping(value = "/business")
public class YearVisController {

    @Autowired
    private YearVisService yearVisService;

    /**
     * 获取各个平台的总安装 访客
     * @param yearAndHistoryDataDto 平台
     * @return
     */
    @ApiOperation(value = "各个平台总注册量，日活 安装",tags = "获取平台最新一年和总的安装，注册，日活")
    @GetMapping("/getYearPlatData")
    public YearDataVo getYearPlatFormData(YearAndHistoryDataDto yearAndHistoryDataDto){

        log.info("-----------------------开始获取平台最新一年和总的安装，注册，日活------------------------");
        YearDataVo yearDataVo = yearVisService.getYearPlatFormData(yearAndHistoryDataDto);
        log.info("-----------------------平台最新一年和总的安装，注册，日活获取结束------------------------");
        return yearDataVo;
    }

    /**
     * 通过年和月来获取各个平台一年的数据的环比
     * @return
     */
    @ApiOperation(value = "各个平台的月数据和年环比",tags = "获取平台指定月的环比")
    @GetMapping("/getMontPlatData")
    public BarDataViewVo getPieDataByMon(PieDataByMonDTO pieDataByMonDTO){
        log.info("------------------------开始获取指定一个月的环比值开始-----------------------");
        BarDataViewVo barDataViewVo = yearVisService.getPieDataByMon(pieDataByMonDTO);
        log.info("------------------------结束获取指定一个月的环比值开始-----------------------");
        return barDataViewVo;
    }
}

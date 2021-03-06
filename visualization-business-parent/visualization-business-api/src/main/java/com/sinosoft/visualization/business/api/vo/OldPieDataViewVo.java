package com.sinosoft.visualization.business.api.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author wangb
 * @date 2020/11/6 13:21
 */
@Data
public class OldPieDataViewVo {
    /**
     * 今日数据概览返参map
     */
    private List<Map<String, Object>> todayOverviewMap;

    /**
     * 数据展示日期
     */
    private String visDate;

    private String platformName;

    private String mom;
}

package com.sinosoft.visualization.business.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * TodayOverviewVO
 *
 * @author wangyl
 * Description:
 * Created in: 2020/11/3
 * Modified by:
 */
@Data
@Accessors(chain = true)
public class TodayOverviewVO {

    /**
     * 今日数据概览返参map
     */
    private List<Map<String, Object>> todayOverviewMap;

    /**
     * 数据展示日期
     */
    private String visDate;

    private String platformName;

    /**
     * 环比
     */
    private String mom;
}

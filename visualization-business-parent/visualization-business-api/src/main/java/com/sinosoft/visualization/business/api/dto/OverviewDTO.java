package com.sinosoft.visualization.business.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * OverviewDTO
 *
 * @author wangyl
 * Description:
 * Created in: 2020/11/10
 * Modified by:
 */
@Data
@Accessors(chain = true)
public class OverviewDTO {
    /**
     * 查询日期
     */
    private String queryDate;

    /**
     * 环比数据日期
     */
    private String ratioDate;
}

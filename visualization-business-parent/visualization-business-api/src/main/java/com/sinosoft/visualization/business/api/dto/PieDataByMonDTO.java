package com.sinosoft.visualization.business.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wangb
 * @date 2020/11/11 17:13
 */
@Data
@Accessors(chain = true)
public class PieDataByMonDTO {
    /**
     * 日期
     */
    private String data;
}

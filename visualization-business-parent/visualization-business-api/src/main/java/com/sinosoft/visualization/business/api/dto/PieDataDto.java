package com.sinosoft.visualization.business.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;

/**
 * @author wangb
 * @date 2020/11/9 16:25
 */
@Data
@Accessors(chain = true)
public class PieDataDto {

    /**
     * 日期(今天)
     */
    private LocalDate yestDay;

    /**
     * 日期
     */
    private LocalDate beforeDay;

}

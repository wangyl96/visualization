package com.sinosoft.visualization.business.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * MapDataDTO
 *
 * @author wangyl
 * Description:
 * Created in: 2020/11/5
 * Modified by:
 */
@Data
@Accessors(chain = true)
public class MapDataDTO {

    /**
     * 平台编码
     */
    private String platformCode;

    /**
     * 指标编码
     */
    private String quotaCode;

    /**
     * 指标名称
     */
    private String quotaName;
}

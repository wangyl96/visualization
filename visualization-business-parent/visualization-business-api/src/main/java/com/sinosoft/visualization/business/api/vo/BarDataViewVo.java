package com.sinosoft.visualization.business.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * @author wangb
 * @date 2020/11/4 14:09
 */
@Data
@Accessors(chain = true)
public class BarDataViewVo {
    /**
     * map
     */
    private List<Map<String, Object>> barViewMap;

    /**
     * 环比
     */
    private List<Map<String,Object>> mom;
}

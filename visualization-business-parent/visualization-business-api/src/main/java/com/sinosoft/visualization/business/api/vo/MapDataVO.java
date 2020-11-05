package com.sinosoft.visualization.business.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Map;

/**
 * MapDataVO
 *
 * @author wangyl
 * Description:
 * Created in: 2020/11/5
 * Modified by:
 */
@Data
@Accessors(chain = true)
public class MapDataVO {

    /**
     * 根据指标倒叙排列的地图数据
     */
    private List<Map<String, Integer>> map;

    /**
     * 选中的指标名称及编码
     */
    private Map<String, String> quota;
}

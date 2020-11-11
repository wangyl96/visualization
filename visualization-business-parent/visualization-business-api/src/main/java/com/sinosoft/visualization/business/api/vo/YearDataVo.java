package com.sinosoft.visualization.business.api.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangb
 * @date 2020/11/10 9:57
 */
@Data
@Accessors(chain = true)
public class YearDataVo {

    private List<Map<String, Object>> yearData = new ArrayList<>();

}

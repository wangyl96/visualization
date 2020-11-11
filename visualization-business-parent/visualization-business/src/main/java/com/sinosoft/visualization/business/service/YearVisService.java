package com.sinosoft.visualization.business.service;

import com.sinosoft.visualization.business.api.dto.PieDataByMonDTO;
import com.sinosoft.visualization.business.api.dto.YearAndHistoryDataDto;
import com.sinosoft.visualization.business.api.vo.BarDataViewVo;
import com.sinosoft.visualization.business.api.vo.YearDataVo;

import java.util.List;

/**
 * @author wangb
 * @date 2020/11/10 9:53
 */
public interface YearVisService {
    YearDataVo getYearPlatFormData(YearAndHistoryDataDto yearAndHistoryDataDto);

    BarDataViewVo getPieDataByMon(PieDataByMonDTO pieDataByMonDTO);
}

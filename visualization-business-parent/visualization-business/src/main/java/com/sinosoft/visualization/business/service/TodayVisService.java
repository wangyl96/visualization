package com.sinosoft.visualization.business.service;

import com.sinosoft.visualization.business.api.dto.MapDataDTO;
import com.sinosoft.visualization.business.api.dto.OverviewDTO;
import com.sinosoft.visualization.business.api.dto.PieDataDto;
import com.sinosoft.visualization.business.api.vo.BarDataViewVo;
import com.sinosoft.visualization.business.api.vo.MapDataVO;
import com.sinosoft.visualization.business.api.vo.OldPieDataViewVo;
import com.sinosoft.visualization.business.api.vo.TodayOverviewVO;

import java.time.LocalDate;
import java.util.List;

/**
 * TodayVisService
 *
 * @author wangyl
 * Description:
 * Created in: 2020/11/3
 * Modified by:
 */
public interface TodayVisService {
    List<TodayOverviewVO> getTodayOverview(OverviewDTO overviewDTO);

    List<TodayOverviewVO> getTabData();

    MapDataVO getTodayMapData(MapDataDTO mapDataDTO);

    List<BarDataViewVo> getAppData(PieDataDto pieDataDto);
}

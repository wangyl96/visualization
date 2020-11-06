package com.sinosoft.visualization.business.service;

import com.sinosoft.visualization.business.api.dto.MapDataDTO;
import com.sinosoft.visualization.business.api.vo.BarDataViewVo;
import com.sinosoft.visualization.business.api.vo.MapDataVO;
import com.sinosoft.visualization.business.api.vo.OldPieDataViewVo;
import com.sinosoft.visualization.business.api.vo.TodayOverviewVO;

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
    List<TodayOverviewVO> getTodayOverview();

    List<TodayOverviewVO> getTabData();

    MapDataVO getTodayMapData(MapDataDTO mapDataDTO);

    List<OldPieDataViewVo> getAppMoney();

    BarDataViewVo getAppData();
}

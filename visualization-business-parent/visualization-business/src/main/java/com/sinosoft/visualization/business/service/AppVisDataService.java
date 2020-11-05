package com.sinosoft.visualization.business.service;

import com.sinosoft.visualization.business.api.vo.BarDataViewVo;
import com.sinosoft.visualization.business.api.vo.TodayOverviewVO;
import com.sinosoft.visualization.business.controller.TodayVisController;

import java.util.List;

/**
 * @author wangb
 * @date 2020/11/2 16:27
 */
public interface AppVisDataService {
    List<TodayOverviewVO> getAppMoney();

     BarDataViewVo getAppData();

}

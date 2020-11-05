package com.sinosoft.visualization.business.service.ipml;


import com.sinosoft.visualization.business.api.vo.BarDataViewVo;
import com.sinosoft.visualization.business.api.vo.TodayOverviewVO;
import com.sinosoft.visualization.business.controller.TodayVisController;
import com.sinosoft.visualization.business.repository.AppComVisDataRepository;
import com.sinosoft.visualization.business.repository.AppVisDataRepository;
import com.sinosoft.visualization.business.repository.SysDictRepository;
import com.sinosoft.visualization.business.service.AppVisDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;

/**
 * @author wangb
 * @date 2020/11/2 16:28
 */

@Service
public class AppVisDataServiceIpml implements AppVisDataService {

    @Autowired
    private SysDictRepository sysDictRepository;

    @Autowired
    private AppVisDataRepository appVisDataRepository;


    @Override
    public List<TodayOverviewVO> getAppMoney() {

        // 获取昨天的日期
        // LocalDate yestDay = LocalDate.now().plusDays(-1);
        // 测试昨天日期
        LocalDate yestDay = LocalDate.of(2020, 01, 05);

        // 获取前天的日期
        //LocalDate beforeYestDay = LocalDate.now().plusDays(-2);

        // 测试前天日期
        LocalDate beforeYestDay = LocalDate.of(2020, 01, 05);

        List<TodayOverviewVO> data = new ArrayList<>();

        //获取所有的平台
        List<String> platFroms = sysDictRepository.getPlatFrom();

        if (!Objects.isNull(platFroms)) {
            for (String platForm : platFroms) {
                List<Map<String, Object>> appMoneys = new ArrayList<>();
                TodayOverviewVO todayOverviewVO = new TodayOverviewVO();
                todayOverviewVO.setPlatformName(platForm);
                todayOverviewVO.setVisDate(yestDay.toString());
                appMoneys = appVisDataRepository.getAppMoney(platForm, yestDay);
                todayOverviewVO.setTodayOverviewMap(appMoneys);

                // 获取环比值
                if (appMoneys.size() > 0) {

                    // 昨天的总和
                    Map<String, Double> yestDayData = appVisDataRepository.getSumMoney(platForm, yestDay);
                    // 前天的总和
                    Map<String, Double> beforeYestDayData = appVisDataRepository.getSumMoney(platForm, beforeYestDay);

                    if (!Objects.isNull(yestDayData) && !Objects.isNull(beforeYestDayData)) {

                        BigDecimal beforeDayValue = new BigDecimal(beforeYestDayData.get("value"));
                        BigDecimal yestDayValue = new BigDecimal(yestDayData.get("value"));
                        // 获取比较值

                        if (beforeDayValue.compareTo(yestDayValue) >= -1 || beforeDayValue.compareTo(yestDayValue) == 1) {
                            String divide = yestDayValue.divide(beforeDayValue, 2, RoundingMode.HALF_UP).toString();
                            todayOverviewVO.setMom(divide);
                            data.add(todayOverviewVO);
                        } else {
                            String divide = yestDayValue.divide(beforeDayValue, 2, RoundingMode.HALF_UP).toString();
                            todayOverviewVO.setMom("-" + divide);
                            data.add(todayOverviewVO);
                        }

                    }


                }

            }


        }
        return data;
    }

    @Override
    public BarDataViewVo getAppData() {

        List<Map<String, Object>> appDatas = appVisDataRepository.getAppData();
        List<Map<String,Object>> mom =  appVisDataRepository.getMom();
        if (appDatas.size() > 0 && mom.size()>0) {

//            appDatas.forEach(appData->{
//                Iterator<Map.Entry<String, Object>> it = appData.entrySet().iterator();
//                while (it.hasNext()){
//                    Map.Entry<String, Object> entry = it.next();
//                    if (entry.getValue().toString().equals("nan")){
//                        it.remove();
//                    }
//                }
//            });

            BarDataViewVo barDataViewVo = new BarDataViewVo();
            barDataViewVo.setBarViewMap(appDatas);
            barDataViewVo.setMom(mom);
            return barDataViewVo;
        }else {
            return null;
        }

    }


}

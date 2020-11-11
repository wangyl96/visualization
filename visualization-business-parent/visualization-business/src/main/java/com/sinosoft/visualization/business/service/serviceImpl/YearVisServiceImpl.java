package com.sinosoft.visualization.business.service.serviceImpl;

import com.sinosoft.visualization.business.api.dto.YearAndHistoryDataDto;
import com.sinosoft.visualization.business.api.vo.YearDataVo;
import com.sinosoft.visualization.business.repository.AppVisDataRepository;
import com.sinosoft.visualization.business.repository.PcVisDataRepository;
import com.sinosoft.visualization.business.repository.WapVisDataRepository;
import com.sinosoft.visualization.business.service.YearVisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wangb
 * @date 2020/11/10 9:53
 */
@Service
public class YearVisServiceImpl implements YearVisService {

    @Autowired
    private AppVisDataRepository appVisDataRepository;
    @Autowired
    private PcVisDataRepository pcVisDataRepository;
    @Autowired
    private WapVisDataRepository wapVisDataRepository;

    /**
     * 获取各平台的日活 安装 注册
     *
     * @return
     */
    @Override
    public List<YearDataVo> getYearPlatFormData(YearAndHistoryDataDto yearAndHistoryDataDto) {
        // 获取东八区时间 获取年
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        Integer year = c.get(Calendar.YEAR);
        List<YearDataVo> yearDataVos = new ArrayList<>();

        switch (yearAndHistoryDataDto.getPlatForm()){
            case "APP":
               List<Map<String, Object>> appYearDataMap = appVisDataRepository.getYearAndTotalData(year);
                if (appYearDataMap.size() > 0){
                    YearDataVo yearDataVo = new YearDataVo();

                    yearDataVo.setYearData(appYearDataMap);
                    yearDataVos.add(yearDataVo);
                }
                break;
            case "PC":
                List<Map<String, Object>> pcYearDataMap = pcVisDataRepository.getYearAndTotalData(year);
                if (pcYearDataMap.size() > 0){
                    YearDataVo yearDataVo = new YearDataVo();

                    yearDataVo.setYearData(pcYearDataMap);
                    yearDataVos.add(yearDataVo);
                }
                break;
            case "WAP":
                List<Map<String, Object>> wapYearDataMap = wapVisDataRepository.getYearAndTotalData(year);
                if (wapYearDataMap.size() > 0){
                    YearDataVo yearDataVo = new YearDataVo();

                    yearDataVo.setYearData(wapYearDataMap);
                    yearDataVos.add(yearDataVo);
                }
                break;
            default :
                break;
        }

            return yearDataVos;
    }
}

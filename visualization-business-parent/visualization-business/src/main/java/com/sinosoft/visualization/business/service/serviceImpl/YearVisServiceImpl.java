package com.sinosoft.visualization.business.service.serviceImpl;

import com.sinosoft.visualization.business.api.dto.PieDataByMonDTO;
import com.sinosoft.visualization.business.api.dto.YearAndHistoryDataDto;
import com.sinosoft.visualization.business.api.vo.BarDataViewVo;
import com.sinosoft.visualization.business.api.vo.YearDataVo;
import com.sinosoft.visualization.business.repository.AppVisDataRepository;
import com.sinosoft.visualization.business.repository.PcVisDataRepository;
import com.sinosoft.visualization.business.repository.WapVisDataRepository;
import com.sinosoft.visualization.business.service.YearVisService;
import com.sinosoft.visualization.common.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataUnit;

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
    public YearDataVo getYearPlatFormData(YearAndHistoryDataDto yearAndHistoryDataDto) {
        // 获取东八区时间 获取年
        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("GMT+08:00"));
        Integer year = c.get(Calendar.YEAR);

        YearDataVo yearDataVo = new YearDataVo();
        if (Objects.isNull(year)) {
            return yearDataVo;
        }
        switch (yearAndHistoryDataDto.getPlatForm()) {
            case "APP":
                List<Map<String, Object>> appYearDataMap = appVisDataRepository.getYearAndTotalData(year);
                if (appYearDataMap.size() > 0) {
                    yearDataVo.setYearData(appYearDataMap);
                }
                break;
            case "PC":
                List<Map<String, Object>> pcYearDataMap = pcVisDataRepository.getYearAndTotalData(year);
                if (pcYearDataMap.size() > 0) {
                    yearDataVo.setYearData(pcYearDataMap);
                }
                break;
            case "WAP":
                List<Map<String, Object>> wapYearDataMap = wapVisDataRepository.getYearAndTotalData(year);
                if (wapYearDataMap.size() > 0) {
                    yearDataVo.setYearData(wapYearDataMap);
                }
                break;
            default:
                break;
        }

        return yearDataVo;
    }

    /**
     * 获取指定年月的饼图数据
     *
     * @param pieDataByMonDTO
     * @return
     */
    @Override
    public BarDataViewVo getPieDataByMon(PieDataByMonDTO pieDataByMonDTO) {
        // 获取指定日期的开始时间和结束时间
        BarDataViewVo barDataViewVo = new BarDataViewVo();
        if (Objects.isNull(pieDataByMonDTO)) {
            return barDataViewVo;
        }
        String appointData = pieDataByMonDTO.getData();
        Integer year = Integer.parseInt(appointData.split("-")[0]);
        Integer mon = Integer.parseInt(appointData.split("-")[1]);


        return null;
    }
}

package com.sinosoft.visualization.business.repository;

import com.sinosoft.visualization.business.api.entity.AppComVisData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;


/**
 * AppDemandRepository
 * APP平台各机构数据表
 * @author wangyl
 * Description:
 * Created in: 2020年11月3日09:50:28
 * Modified by:
 */
public interface AppComVisDataRepository extends JpaRepository<AppComVisData, Long>, JpaSpecificationExecutor<AppComVisData> {

    @Query(value = "SELECT company name, app_installation value FROM app_com_vis_data WHERE vis_date = ?1 AND company != '其他' ORDER BY app_installation DESC", nativeQuery = true)
    List<Map<String, Integer>> getInstallMapData(String mapDate);

    @Query(value = "SELECT company name, registrations value FROM app_com_vis_data WHERE vis_date = ?1 AND company != '其他' ORDER BY registrations DESC", nativeQuery = true)
    List<Map<String, Integer>> getRegistMapData(String mapDate);



}

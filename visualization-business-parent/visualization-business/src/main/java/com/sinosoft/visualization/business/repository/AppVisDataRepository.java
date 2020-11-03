package com.sinosoft.visualization.business.repository;

import com.sinosoft.visualization.business.api.entity.AppVisData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppVisDataRepository extends JpaRepository<AppVisData, Long> {

    @Query(value = "SELECT vis_date, app_installation, registrations, life_day FROM app_vis_data ORDER BY vis_date DESC LIMIT 2", nativeQuery = true)
    List<AppVisData> getOverview();
}

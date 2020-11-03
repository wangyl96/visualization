package com.sinosoft.visualization.business.repository;

import com.sinosoft.visualization.business.api.entity.PcVisData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PcVisDataRepository extends JpaRepository<PcVisData, Long> {

    @Query(value = "SELECT visits_new,visits_old, visitors_new, visitors_old , registrations FROM pc_vis_data ORDER BY vis_date DESC LIMIT 2", nativeQuery = true)
    List<PcVisData> getOverview();
}

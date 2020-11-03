package com.sinosoft.visualization.business.repository;

import com.sinosoft.visualization.business.api.entity.AppComVisData;
import com.sinosoft.visualization.business.api.entity.QuotaDic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * AppDemandRepository
 *
 * @author wangyl
 * Description:
 * Created in: 2020年11月3日09:50:28
 * Modified by:
 */
public interface QuotaDicRepository extends JpaRepository<QuotaDic, Long> {

    @Query(value = "SELECT platform_name,quota_name,quota_code FROM quota_dic WHERE is_active = 1 GROUP BY platform_name", nativeQuery = true)
    List<QuotaDic> findAllPlatform();
}

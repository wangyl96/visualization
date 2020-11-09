package com.sinosoft.visualization.business.repository;

import com.sinosoft.visualization.business.api.entity.WapVisData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * WapVisDataRepository
 *
 * @author wangyl
 * Description:
 * Created in: 2020/11/3
 * Modified by:
 */
public interface WapVisDataRepository extends JpaRepository<WapVisData, Long> {

    @Query(value = "(SELECT '注册量' AS contentName, pvd.registrations contentData, (pvd.registrations - (SELECT registrations FROM (SELECT pvd.vis_date,pvd.registrations FROM wap_vis_data pvd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            "ORDER BY vis_date LIMIT 1))/(SELECT registrations FROM (SELECT pvd.vis_date,pvd.registrations FROM wap_vis_data pvd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            "ORDER BY vis_date LIMIT 1) AS ratio FROM wap_vis_data pvd ORDER BY vis_date DESC LIMIT 1) \n" +
            "UNION\n" +
            "(SELECT '访问量' AS contentName, pvd.visits_new + pvd.visits_old contentData, (pvd.visits_new + pvd.visits_old - (SELECT visits FROM (SELECT pvd.vis_date, pvd.visits_new + pvd.visits_old visits FROM wap_vis_data pvd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            "ORDER BY vis_date LIMIT 1))/(SELECT  pvd.visits_new + pvd.visits_old FROM (SELECT pvd.vis_date, pvd.visits_new + pvd.visits_old FROM wap_vis_data pvd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            "ORDER BY vis_date LIMIT 1) AS ratio FROM wap_vis_data pvd ORDER BY vis_date DESC LIMIT 1) \n" +
            "UNION\n" +
            "(SELECT '访客量' AS contentName, pvd.visitors_new + pvd.visitors_old contentData, (pvd.visitors_new + pvd.visitors_old - (SELECT visitors FROM (SELECT pvd.vis_date, pvd.visitors_new + pvd.visitors_old visitors FROM wap_vis_data pvd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            "ORDER BY vis_date LIMIT 1))/(SELECT  pvd.visitors_new + pvd.visitors_old FROM (SELECT pvd.vis_date, pvd.visitors_new + pvd.visitors_old FROM wap_vis_data pvd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            "ORDER BY vis_date LIMIT 1) AS ratio FROM wap_vis_data pvd ORDER BY vis_date DESC LIMIT 1) ", nativeQuery = true)
    List<Map<String, Object>> getWapInfo();


    @Query(value = "select product ,life,health,SUM(product\n" +
            "+ life + health ) realSum, SUM(ABS(product)+ABS(life)+ABS(health)) falseSum  from wap_vis_data where vis_date between ?1 and ?2 and is_active = 1",nativeQuery = true)
    List<Map<String,Object>> getWapDatas(LocalDate dateBefore, LocalDate dateAfter);

    @Query(value = "SELECT SUM(product+ life + health ) mom FROM wap_vis_data WHERE is_active =1\n" +
            "AND vis_date = ?1",nativeQuery = true)
    Double getWapMom(LocalDate date);
}

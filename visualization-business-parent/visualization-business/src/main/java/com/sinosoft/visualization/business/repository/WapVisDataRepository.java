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

    @Query(value = "(SELECT '注册量' AS contentName, pvd.registrations contentData,\n" +
            "((pvd.registrations - (SELECT pvd.registrations FROM wap_vis_data pvd WHERE vis_date = ?2))/\n" +
            "(SELECT pvd.registrations FROM wap_vis_data pvd WHERE vis_date = ?2)) AS ratio FROM wap_vis_data pvd WHERE vis_date = ?1)  \n" +
            "UNION \n" +
            "(SELECT '访问量' AS contentName, pvd.visits_new + pvd.visits_old contentData,\n" +
            "((pvd.visits_new + pvd.visits_old - (SELECT pvd.visits_new + pvd.visits_old FROM wap_vis_data pvd WHERE vis_date = ?2 ))/\n" +
            "(SELECT pvd.visits_new + pvd.visits_old FROM wap_vis_data pvd WHERE vis_date = ?2)) AS ratio FROM wap_vis_data pvd WHERE vis_date = ?1)  \n" +
            " UNION \n" +
            "(SELECT '访客量' AS contentName, pvd.visitors_new + pvd.visitors_old contentData,\n" +
            "((pvd.visitors_new + pvd.visitors_old - (SELECT pvd.visitors_new + pvd.visitors_old FROM wap_vis_data pvd WHERE vis_date = ?2))/\n" +
            "(SELECT pvd.visitors_new + pvd.visitors_old FROM wap_vis_data pvd WHERE vis_date = ?2)) AS ratio FROM wap_vis_data pvd WHERE vis_date = ?1)", nativeQuery = true)
    List<Map<String, Object>> getWapInfo(String queryDate, String ratioDate);


    @Query(value = "select product ,life,health,SUM(product\n" +
            "+ life + health ) realSum, SUM((case when health> 0 then health else 0 end)+(case when product> 0 then product else 0 end)+(case when life> 0 then life else 0 end)) falseSum  from wap_vis_data where vis_date between ?1 and ?2 and is_active = 1", nativeQuery = true)
    List<Map<String, Object>> getWapDatas(String dateBefore, String dateAfter);

    @Query(value = "SELECT SUM(product+ life + health ) mom FROM wap_vis_data WHERE is_active =1\n" +
            "AND vis_date = ?1", nativeQuery = true)
    Double getWapMom(String date);

    @Query(value = "SELECT SUM(registrations) '总注册量'," +
            "(select  SUM(registrations) '年注册量' FROM wap_vis_data WHERE is_active = 1 and year(vis_date) = ?1)'年注册量' ," +
            "SUM(visits_old+visits_new)'总访问量',(select  SUM(visits_old+visits_new)  FROM wap_vis_data WHERE is_active = 1 and year(vis_date) = ?1)'年访问量'," +
            "SUM(visitors_new+visitors_old) '总访客量',(select  SUM(visitors_new+visitors_old)  FROM wap_vis_data WHERE is_active = 1 and year(vis_date) =?1) '年访客量'" +
            "FROM wap_vis_data WHERE is_active = 1", nativeQuery = true)
    Map<String, Object> getYearAndTotalData(Integer year);
}

package com.sinosoft.visualization.business.repository;

import com.sinosoft.visualization.business.api.entity.PcVisData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * PcVisDataRepository
 *
 * @author wangyl
 * Description:
 * Created in: 2020/11/3
 * Modified by:
 */
public interface PcVisDataRepository extends JpaRepository<PcVisData, Long> {

    @Query(value = "(SELECT '注册量' AS contentName, pvd.registrations contentData,\n" +
            "((pvd.registrations - (SELECT pvd.registrations FROM pc_vis_data pvd WHERE vis_date = ?2))/\n" +
            "(SELECT pvd.registrations FROM pc_vis_data pvd WHERE vis_date = ?2)) AS ratio FROM pc_vis_data pvd WHERE vis_date = ?1)  \n" +
            "UNION \n" +
            "(SELECT '访问量' AS contentName, pvd.visits_new + pvd.visits_old contentData,\n" +
            "((pvd.visits_new + pvd.visits_old - (SELECT pvd.visits_new + pvd.visits_old FROM pc_vis_data pvd WHERE vis_date = ?2))/\n" +
            "(SELECT pvd.visits_new + pvd.visits_old FROM pc_vis_data pvd WHERE vis_date = ?2)) AS ratio FROM pc_vis_data pvd WHERE vis_date = ?1)  \n" +
            " UNION \n" +
            "(SELECT '访客量' AS contentName, pvd.visitors_new + pvd.visitors_old contentData,\n" +
            "((pvd.visitors_new + pvd.visitors_old - (SELECT pvd.visitors_new + pvd.visitors_old FROM pc_vis_data pvd WHERE vis_date = ?2))/\n" +
            "(SELECT pvd.visitors_new + pvd.visitors_old FROM pc_vis_data pvd WHERE vis_date = ?2)) AS ratio FROM pc_vis_data pvd WHERE vis_date = ?1)", nativeQuery = true)
    List<Map<String, Object>> getPcInfo(String queryDate, String ratioDate);


    @Query(value = "select product ,life,health,wealth,SUM(product\n" +
            "+ life + health + wealth) realSum, SUM((case when health> 0 then health else 0 end)+(case when product> 0 then product else 0 end)+(case when life> 0 then life else 0 end)+(case when wealth> 0 then wealth else 0 end)) falseSum  from pc_vis_data where vis_date between ?1 and ?2 and is_active = 1",nativeQuery = true)
    List<Map<String,Object>> getPcDatas(LocalDate dateBefore, LocalDate dateAfter);

    @Query(value = "SELECT SUM(product+ life + health + wealth) mom FROM pc_vis_data WHERE is_active =1\n" +
            "AND vis_date = ?1",nativeQuery = true)
    Double getPcMom(LocalDate date);
}

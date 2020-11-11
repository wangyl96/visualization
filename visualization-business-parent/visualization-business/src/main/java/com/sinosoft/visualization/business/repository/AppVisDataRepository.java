package com.sinosoft.visualization.business.repository;

import com.sinosoft.visualization.business.api.entity.AppVisData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * AppVisDataRepository
 *
 * @author wangyl
 * Description:
 * Created in: 2020/11/3
 * Modified by:
 */
public interface AppVisDataRepository extends JpaRepository<AppVisData, Long> {

    @Query(value = "(SELECT '安装量' AS contentName, avd.app_installation contentData,\n" +
            "((avd.app_installation - (SELECT avd.app_installation FROM app_vis_data avd WHERE vis_date = ?2))/\n" +
            "(SELECT avd.app_installation FROM app_vis_data avd WHERE vis_date = ?2)) AS ratio FROM app_vis_data avd WHERE vis_date = ?1)  \n" +
            "UNION \n" +
            " (SELECT '注册量' AS contentName, avd.registrations contentData,\n" +
            "((avd.registrations - (SELECT avd.registrations FROM app_vis_data avd WHERE vis_date = ?2))/\n" +
            "(SELECT avd.registrations FROM app_vis_data avd WHERE vis_date = ?2)) AS ratio FROM app_vis_data avd WHERE vis_date = ?1)  \n" +
            " UNION \n" +
            " (SELECT '日活' AS contentName, avd.life_day contentData,\n" +
            "((avd.life_day - (SELECT avd.life_day FROM app_vis_data avd WHERE vis_date = ?2))/\n" +
            "(SELECT avd.life_day FROM app_vis_data avd WHERE vis_date = ?2)) AS ratio FROM app_vis_data avd WHERE vis_date = ?1)  \n" +
            " ", nativeQuery = true)
    List<Map<String, Object>> getAppInfo(String queryDate, String ratioDate);

    /**
     * 通过时间获取
     * @param dateBefore
     * @param dateAfter
     * @return
     */
    @Query(value = "select product ,life,health,wealth,gold,SUM(product\n" +
            "+ life + health + wealth+ gold) realSum, SUM((case when health> 0 then health else 0 end)+(case when product> 0 then product else 0 end)" +
            "+(case when life> 0 then life else 0 end)+(case when wealth> 0 then wealth else 0 end)+(case when gold> 0 then gold else 0 end)) falseSum  from app_vis_data where vis_date between ?1 and ?2 and is_active = 1",nativeQuery = true)
    List<Map<String,Object>> getAppPieData(String dateBefore, String dateAfter);

    @Query(value = "SELECT SUM(product+ life + health + wealth+ gold) mom FROM app_vis_data WHERE is_active =1\n" +
            "AND vis_date = ?1",nativeQuery = true)
    Double getMom(String date);


    @Query(value = "select name, 'circle' as 'icon'\n" +
            "from sys_dict where platform =?1 and is_active = 1\n",nativeQuery = true)
    List<Map<String, Object>> getLegends(String platForm);

    @Query(value = "SELECT '注册量' custNmae, SUM(registrations) 'total',(select  SUM(registrations)  FROM app_vis_data WHERE is_active = 1 and year(vis_date) = ?1) yearValue FROM app_vis_data WHERE is_active = 1\n" +
            "\t   UNION all\n" +
            "\tSELECT '安装量' custNmae, SUM(app_installation) 'total',(select  SUM(app_installation)  FROM app_vis_data WHERE is_active = 1 and year(vis_date) = ?1) yearValue FROM app_vis_data WHERE is_active = 1\n" +
            "\tUNION all\n" +
            "\tSELECT '访问量' custNmae, SUM(visits) 'total',(select  SUM(visits)  FROM app_vis_data WHERE is_active = 1 and year(vis_date) = ?1) yearValue FROM app_vis_data WHERE is_active = 1", nativeQuery = true)
    List<Map<String, Object>> getYearAndTotalData(Integer year);

    /**
     * 通过limit来获取数据
     * @return
     */
    @Query(value = "select a.*, a.product + a.life + a.wealth + a.gold + a.health num from \n" +
            "(SELECT 'app' as platForm ,product ,life,health,wealth,gold \n" +
            "from app_vis_data WHERE is_active = 1 ORDER BY vis_date desc LIMIT 1 ) a\n" +
            "UNION all\n" +
            "select p.* , p.product + p.life + p.health + p.wealth num from \n" +
            "(SELECT 'pc' as platForm ,product ,life,health,wealth ,\"nan\" gold FROM pc_vis_data WHERE is_active = 1  ORDER BY vis_date desc LIMIT 1) p\n" +
            "UNION all\n" +
            "SELECT w.* , w.product +w.life + w.health FROM\n" +
            "(SELECT 'wap' as platForm ,product ,life,health,\"nan\" wealth ,\"nan\" gold FROM wap_vis_data WHERE is_active = 1 ORDER BY vis_date LIMIT 1 ) w",nativeQuery = true)
    List<Map<String, Object>> getAppData();


}

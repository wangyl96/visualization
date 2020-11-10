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

    @Query(value = "(SELECT '安装量' AS contentName, avd.app_installation contentData, (avd.app_installation - (SELECT app_installation FROM (SELECT avd.vis_date,avd.app_installation FROM app_vis_data avd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            " ORDER BY vis_date LIMIT 1))/(SELECT app_installation FROM (SELECT avd.vis_date,avd.app_installation FROM app_vis_data avd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            " ORDER BY vis_date LIMIT 1) AS ratio FROM app_vis_data avd ORDER BY vis_date DESC LIMIT 1) \n" +
            " UNION\n" +
            " (SELECT '注册量' AS contentName, avd.registrations contentData, (avd.registrations - (SELECT registrations FROM (SELECT avd.vis_date,avd.registrations FROM app_vis_data avd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            " ORDER BY vis_date LIMIT 1))/(SELECT registrations FROM (SELECT avd.vis_date,avd.registrations FROM app_vis_data avd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            " ORDER BY vis_date LIMIT 1) AS ratio FROM app_vis_data avd ORDER BY vis_date DESC LIMIT 1) \n" +
            " UNION\n" +
            " (SELECT '日活' AS contentName, avd.life_day, (avd.life_day - (SELECT life_day FROM (SELECT avd.vis_date,avd.life_day FROM app_vis_data avd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            " ORDER BY vis_date LIMIT 1))/(SELECT life_day FROM (SELECT avd.vis_date,avd.life_day FROM app_vis_data avd ORDER BY vis_date DESC LIMIT 2) AS table1\n" +
            " ORDER BY vis_date LIMIT 1) AS ratio FROM app_vis_data avd ORDER BY vis_date DESC LIMIT 1)", nativeQuery = true)
    List<Map<String, Object>> getAppInfo();


    @Query(value = "SELECT  bc.`sv` type,bc.`name`,SUM(bc.`value`) `value` from (SELECT ac.`value`,sd.`value` sv ,sd.`name` ,sd.platform from\n" +
            "app_vis_data ac\n" +
            "JOIN (\n" +
            "SELECT  sys_dict.`value`,sys_dict.`name`,sys_dict.id,sys_dict.platform\n" +
            "FROM\n" +
            "sys_dict\n" +
            "WHERE is_active = 1\n" +
            ")\n" +
            "sd\n" +
            "on\n" +
            "sd.id = ac.sys_id\n" +
            "WHERE ac.is_active = 1\n" +
            "AND sd.platform=?1\n" +
            "and\n" +
            "SUBSTRING(vis_date,1,10) =?2 \n" +
            ")\n" +
            "bc \n" +
            "GROUP  BY bc.sv,bc.`name`",nativeQuery = true)
    List<Map<String,Object>> getAppMoney(String platForm, LocalDate testDay);

    @Query(value = "SELECT\n" +
            "\tSUM( bc.`value` ) `value` \n" +
            "FROM\n" +
            "\t(\n" +
            "SELECT\n" +
            "\tac.`value`,\n" +
            "\tsd.platform \n" +
            "FROM\n" +
            "\tapp_vis_data ac\n" +
            "\tJOIN ( SELECT sys_dict.id, sys_dict.platform FROM sys_dict WHERE is_active = 1 ) sd ON sd.id = ac.sys_id \n" +
            "WHERE\n" +
            "\tac.is_active = 1 \n" +
            "AND sd.platform=?1\n" +
            "and\n" +
            "SUBSTRING(vis_date,1,10) =?2 \n" +
            "\t) bc" ,nativeQuery = true)
    Map<String, Double> getSumMoney(String platForm, LocalDate testDay);


    @Query(value = "select product ,life,health,wealth,gold,SUM(product\n" +
            "+ life + health + wealth+ gold) realSum, SUM((case when health> 0 then health else 0 end)+(case when product> 0 then product else 0 end)" +
            "+(case when life> 0 then life else 0 end)+(case when wealth> 0 then wealth else 0 end)+(case when gold> 0 then gold else 0 end)) falseSum  from app_vis_data where vis_date between ?1 and ?2 and is_active = 1",nativeQuery = true)
    List<Map<String,Object>> getAppDatas(LocalDate dateBefore, LocalDate dateAfter);

    @Query(value = "SELECT SUM(product+ life + health + wealth+ gold) mom FROM app_vis_data WHERE is_active =1\n" +
            "AND vis_date = ?1",nativeQuery = true)
    Double getMom(LocalDate date);


    @Query(value = "select name, 'circle' as 'icon'\n" +
            "from sys_dict where platform =?1 and is_active = 1\n",nativeQuery = true)
    List<Map<String, Object>> getLegends(String platForm);

}

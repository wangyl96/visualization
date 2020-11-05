package com.sinosoft.visualization.business.repository;

import com.sinosoft.visualization.business.api.entity.AppVisData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
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

    @Query(value = " SELECT 'app' platform ,(SELECT product + life + health + wealth + gold  mum from app_vis_data WHERE is_active = 1 ORDER BY vis_date desc LIMIT 1)\n" +
            " /(\n" +
            " SELECT table1.mum from\n" +
            " (SELECT  vis_date,product + life + health + wealth + gold  mum from app_vis_data WHERE is_active = 1 ORDER BY vis_date desc LIMIT 2) table1 ORDER BY table1.vis_date LIMIT 1) num\n" +
            " UNION all \n" +
            " SELECT 'pc' platform ,(SELECT product + life + health + wealth   mum from pc_vis_data WHERE is_active = 1 ORDER BY vis_date desc LIMIT 1)\n" +
            " /(\n" +
            " SELECT table2.mum from\n" +
            " (SELECT  vis_date,product + life + health + wealth   mum from pc_vis_data WHERE is_active = 1 ORDER BY vis_date desc LIMIT 2) table2 ORDER BY table2.vis_date LIMIT 1)\n" +
            " \n" +
            " UNION all \n" +
            " SELECT 'wap' platform ,(SELECT product + life + health  mum from wap_vis_data WHERE is_active = 1 ORDER BY vis_date desc LIMIT 1)\n" +
            " /(\n" +
            " SELECT table3.mum from\n" +
            " (SELECT  vis_date,product + life + health  mum from wap_vis_data WHERE is_active = 1 ORDER BY vis_date desc LIMIT 2) table3 ORDER BY table3.vis_date LIMIT 1)",nativeQuery = true)
    List<Map<String, Object>> getMom();
}

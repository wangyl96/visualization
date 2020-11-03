package com.sinosoft.visualization.business.repository;

import com.sinosoft.visualization.business.api.entity.AppVisData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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
}

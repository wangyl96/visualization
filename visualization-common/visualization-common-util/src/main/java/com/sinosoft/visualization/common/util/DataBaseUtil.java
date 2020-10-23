package com.sinosoft.visualization.common.util;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

/**
 * DataBaseUtil
 * 获取数据库链接
 * @author wangyl
 * Description:
 * Created in: 2020/8/20
 * Modified by:
 */
@Slf4j
public class DataBaseUtil {

    /**
     * 根据数据库连接参数, 获取数据库连接
     * @param dataBaseMap
     * @return
     */
    public static Connection getMobileDataBaseInfo(Map<String, String> dataBaseMap) {
        try {
            String dbKey = AesUtil.decryptByKey(dataBaseMap.get("dbKey"), dataBaseMap.get("aes_key"));
            Class.forName(dataBaseMap.get("jarName"));
            Connection conn = DriverManager.getConnection(dataBaseMap.get("url"), dataBaseMap.get("user"), dbKey);
            return conn;
        } catch (Exception e) {
            log.info("获取数据连接失败, 数据库名称:{}", dataBaseMap.get("sign"));
        }
        return null;
    }
}

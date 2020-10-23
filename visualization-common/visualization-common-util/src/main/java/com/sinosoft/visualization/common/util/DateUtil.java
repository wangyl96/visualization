package com.sinosoft.visualization.common.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DateUtil
 *
 * @author wangyl
 * Description:
 * Created in: 2020/8/25
 * Modified by:
 */
public class DateUtil {

    private static final String NOW_DATE="yyyy-MM-dd";
    private static final String NOW_TIME="yyyy-MM-dd HH:mm:ss";

    /**
     * 得到当前日期
     */
    public static String getNowDate(){
        return format(LocalDateTime.now(), NOW_DATE);
    }

    /**
     * 获取前一天日期
     */
    public static String getBeforeDate() {
        return format(LocalDateTime.now().plusDays(-1), NOW_DATE);
    }

    /**
     * 获取上周五时间
     * @return
     */
    public static String getBeforeWeek() {
        return format(LocalDateTime.now().plusDays(-7), NOW_DATE);
    }

    /**
     * 得到当前时间
     */
    public static String getNowTime(){
        return format(LocalDateTime.now(), NOW_TIME);
    }

    /**
     * localDateTime转换为格式化时间
     * @param localDateTime
     * @param pattern 格式
     */
    public static String format(LocalDateTime localDateTime, String pattern){
        DateTimeFormatter formatter =DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(formatter);
    }
}

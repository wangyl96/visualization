package com.sinosoft.visualization.common.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

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
    private static final String NOW_YEAR_MON = "yyyy-MM";

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

    /**
     * 获取指定年月的开始时间
     * @param year
     * @param month
     * @return
     */
    public static Date getBeginTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate localDate = yearMonth.atDay(1);
        LocalDateTime startOfDay = localDate.atStartOfDay();
        ZonedDateTime zonedDateTime = startOfDay.atZone(ZoneId.of("Asia/Shanghai"));

        return Date.from(zonedDateTime.toInstant());
    }

    /**
     * 获取指定年月的结束时间
     * @param year
     * @param month
     * @return
     */
    public static Date getEndTime(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        LocalDate endOfMonth = yearMonth.atEndOfMonth();
        LocalDateTime localDateTime = endOfMonth.atTime(23, 59, 59, 999);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Shanghai"));
        return Date.from(zonedDateTime.toInstant());
    }


    /**
     * 通过localDate进行转换格式
     * @return
     */
    public  static  String getTimeStrNow1(){
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    /**
     *
     * 获取指定年月的上一个月
     * @param data
     * @return
     */
    public static String getLastYearMo(String data){

        DateFormat format2 =  new SimpleDateFormat(NOW_YEAR_MON);
        Date date = new Date();
        try{
            date = format2.parse(data);
        }catch(Exception e){
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        SimpleDateFormat format =  new SimpleDateFormat(NOW_YEAR_MON);
        c.add(Calendar.MONTH, -1);
        return format.format(c.getTime());
    }
}

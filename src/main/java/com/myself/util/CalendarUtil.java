package com.myself.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

/**
 * 常用日历操作辅助类
 *
 * @author Created by zion
 * @Date 2018/5/18.
 */
public class CalendarUtil {

    /**
     * 实例化日历
     */
    private static Calendar calendar = Calendar.getInstance();

    private static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * 获得当前年份
     *
     * @return
     */
    public static Integer getCurrentYear() {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 获得当前月份
     *
     * @return
     */
    public static Integer getCurrentMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }


    /**
     * 获得今天在本月的第几天
     *
     * @return
     */
    public static Integer getCurrentDayOfMonth() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得今天在本年的第几天
     *
     * @return
     */
    public static Integer getCurrentDayOfYear() {
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获得今天在本周的第几天
     *
     * @return
     */
    public static Integer getCurrentDayOfWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得今天是这个月的第几周
     *
     * @return
     */
    public static Integer getCurrentWeekOfMonth() {
        return calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH);
    }

    /**
     * 得到二个日期间的间隔天数
     */
    public static String getIntervalOfTwoDay(String previous, String latter) {
        long day = 0;
        try {
            Date previousDate = DateUtil.parse(previous, DATE_FORMAT);
            Date latterDate = DateUtil.parse(latter, DATE_FORMAT);
            if (previousDate.getTime() > latterDate.getTime()) {
                day = (previousDate.getTime() - latterDate.getTime()) / (24 * 60 * 60 * 1000);
            } else {
                day = (latterDate.getTime() - previousDate.getTime()) / (24 * 60 * 60 * 1000);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day + "";
    }

    /**
     * 获得本年有多少天
     *
     * @return
     */
    public static Integer getTotalDayOfCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        // 把日期设为当年第一天
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        // 把日期回滚一天。对月份使用roll方法，它会在1-12的范围内变化，不会影响的年
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        return calendar.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 判断当前日期是否是双休日
     *
     * @return
     */
    public static boolean checkIsHoliday() {
        //判断日期是否是周六周日
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY
                || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY;
    }
}

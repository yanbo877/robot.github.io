package com.bigdata.coin.service.common.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * .
 */
public class DateTimeUtils {

    public static String getCurrentDate() {
        return getCurrentDate("yyyyMMdd");
    }


    /**
     * 以指定格式返回日期字符串.
     */
    public static String getCurrentDate(String dateFormat) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String currentDate = sdf.format(date);
        return currentDate;
    }

    /**
     * .
     */
    public static String getNDaysBeforeTheDate(String thedate, int days) {

        // 时间表示格式可以改变，yyyyMMdd需要写例如20160523这种形式的时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        // 将字符串的日期转为Date类型，ParsePosition(0)表示从第一个字符开始解析
        Date date = sdf.parse(thedate, new ParsePosition(0));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // add方法中的第二个参数n中，正数表示该日期后n天，负数表示该日期的前n天
        calendar.add(Calendar.DATE, days);
        Date date1 = calendar.getTime();
        String result = sdf.format(date1);
        return result;

    }


    /**
     * 时间段内的全部日期.
     */
    public static List<String> findDates(String startDate, String endDate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date dateBegin = sdf.parse(startDate);
        Date dateEnd = sdf.parse(endDate);

        List<String> listDate = new ArrayList<>();
        listDate.add(sdf.format(dateBegin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dateBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dateEnd);
        // 测试此日期是否在指定日期之后
        while (dateEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            listDate.add(sdf.format(calBegin.getTime()));
        }
        return listDate;
    }

    /**
     * 增加日期中某类型的某数值。如增加日期.
     *
     * @param date 日期字符串
     * @param dateType 类型
     * @param amount 数值
     * @return 计算后日期
     */
    public static Date addInteger(Date date, int dateType, int amount) {
        Date myDate = null;
        if (date != null) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(dateType, amount);
            myDate = calendar.getTime();
        }
        return myDate;
    }

    /**
     * 增加日期的小时。失败返回null.
     *
     * @param date 日期字符串
     * @param hourAmount 增加数量。可为负数
     * @return 增加小时后的日期字符串.
     */
    public static Date addHour(Date date, int hourAmount) {
        return addInteger(date, Calendar.HOUR_OF_DAY, hourAmount);
    }


}

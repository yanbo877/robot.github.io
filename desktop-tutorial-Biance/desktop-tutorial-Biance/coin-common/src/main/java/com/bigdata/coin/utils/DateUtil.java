package com.bigdata.coin.utils;

import org.joda.time.DateTime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间处理.
 */
public class DateUtil {

    public static final String DEFAULT_FORMAT_DATE = "yyyy-MM-dd";
    public static final String SIMPLE_FORMAT_DATE = "yyyyMMddHH";
    public static final String DEFAULT_FORMAT_DATE_TIME_CONNECTIONLESS = "yyyyMMddHHmmss";
    public static final String DEFAULT_FORMAT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DEFAULT_FORMAT_DATE_TIME_MILLIS = "yyyy-MM-dd HH:mm:ss:SSS";

    /**
     * 获取秒.
     */
    public static String getSecondBefore(int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.SECOND, calendar.get(Calendar.SECOND) + second);
        return new SimpleDateFormat("yyyyMMddHHmmss").format(calendar.getTime());
    }

    /**
     * 获取分钟.
     */
    public static String getMinuteBefore(int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, calendar.get(Calendar.MINUTE) + minute);
        return new SimpleDateFormat("yyyyMMddHHmm").format(calendar.getTime());
    }

    /**
     * 获取小时.
     */
    public static String getHourBefore(int hour) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        return new SimpleDateFormat("yyyyMMddHH").format(calendar.getTime());
    }

    /**
     * 获取小时.
     */
    public static String getHourBeforeWithFoemat(int hour, String format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) + hour);
        return new SimpleDateFormat(format).format(calendar.getTime());
    }

    /**
     * 以指定格式返回日期字符串.
     */
    public static String getCurrentDate() {
        return getCurrentDate("yyyy-MM-dd HH:mm:ss");
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
     * 获取当前时间格式为: 年-月-日
     *
     * @return
     */
    public static String getDateDayStr() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date());
    }

    /**
     * 获取当前时间格式为: 年月日时分秒
     *
     * @return
     */
    public static String getDateTimeNoSymbol() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        return dateFormat.format(new Date());
    }

    /**
     * 获取当日凌晨时间(20xx-xx-xx 00:00:00).
     */
    public static String getWeeTime() {
        String currentDate = DateUtil.getCurrentDate("yyyy-MM-dd");
        Date date = null;
        try {
            date = DateUtil.strToDate(currentDate, "yyyy-MM-dd");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return DateUtil.getSpecifiedDate(date);
    }

    /**
     * 以指定格式返回日期字符串.
     */
    public static String getSpecifiedDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String specifiedDate = sdf.format(date);
        return specifiedDate;
    }

    /**
     * 以指定格式返回日期字符串.
     */
    public static String getSpecifiedDate(Date date, String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        String specifiedDate = sdf.format(date);
        return specifiedDate;
    }

    /**
     * 获取当前小时.
     */
    public static int getCurrentHour() {
        LocalTime time = LocalTime.now();
        return time.getHour();
    }

    /**
     * 获取当前时间戳 精确到秒
     * @return
     */
    public static Integer getCurrent(){
        return (int) (System.currentTimeMillis() / 1000);
    }


    /**
     * 时间字符串转日期.
     */
    public static Date strToDate(String dateStr, String format) throws ParseException {
        return new SimpleDateFormat(format).parse(dateStr);
    }

    /**
     * 时间字符串转日期.
     */
    public static Date strToDate(String dateStr) throws ParseException {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
    }

    /**
     * 时间戳转日期.
     */
    public static String timeStampToDate(String seconds) {
        return timeStampToDate(seconds, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 时间戳转日期.
     */
    public static String timeStampToDate(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || seconds.equals("null")) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.parseLong(seconds)));
    }

    /**
     * 获取两个时间的执行间隔: 运行时长.
     */
    public static String getdurationTime(String startDateTime, String endDateTime) {
        if (startDateTime == null || endDateTime == null) {
            return "- h " + "- m " + "- s";
        }

        SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin = new Date();
        Date end = new Date();
        try {
            begin = dfs.parse(startDateTime);
            end = dfs.parse(endDateTime);
        } catch (ParseException exp) {
            exp.printStackTrace();
        }

        long between = (end.getTime() - begin.getTime()) / 1000;//除以1000是为了转换成秒
        if (between >= 0) {
            long day1 = between / (24 * 3600);
            long hour1 = day1 * 24 + between % (24 * 3600) / 3600;
            long minute1 = between % 3600 / 60;
            long second1 = between % 60;
            return hour1 + " h " + minute1 + " m " + second1 + " s";
        } else {
            return "- h " + "- m " + "- s";
        }

    }

    /**
     * 获取n天日起.
     */
    public static String getSpecifiedDayBefore(String specifiedDay, String dateFormat, int days) {
        Calendar cal = Calendar.getInstance();
        try {
            Date date = new SimpleDateFormat(dateFormat).parse(specifiedDay);
            cal.setTime(date);
        } catch (ParseException exception) {
            exception.printStackTrace();
        }

        int day = cal.get(Calendar.DATE);
        cal.set(Calendar.DATE, day - days);
        String dayBefore = new SimpleDateFormat(dateFormat).format(cal.getTime());
        return dayBefore;
    }

    /**
     * 时间段内的全部日期.
     */
    public static List<String> findDates(String startDate, String endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date begin = new Date();
        Date end = new Date();
        try {
            begin = sdf.parse(startDate);
            end = sdf.parse(endDate);
        } catch (ParseException exp) {
            exp.printStackTrace();
        }

        List<String> days = new ArrayList<>();
        days.add(sdf.format(begin));
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(begin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(end);
        // 测试此日期是否在指定日期之后
        while (end.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            days.add(sdf.format(calBegin.getTime()));
        }
        return days;
    }

    /**
     * local时间转换成UTC时间.
     */
    public static Date localToUtc(String localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date localDate = new Date();
        try {
            localDate = sdf.parse(localTime);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        long localTimeInMillis = localDate.getTime();
        // long时间转换成Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        // 取得时间偏移量
        int zoneOffset = calendar.get(Calendar.ZONE_OFFSET);
        // 取得夏令时差
        int dstOffset = calendar.get(Calendar.DST_OFFSET);
        // 从本地时间里扣除这些差量，即可以取得UTC时间
        calendar.add(Calendar.MILLISECOND, (zoneOffset + dstOffset));
        Date utcDate = new Date(calendar.getTimeInMillis());
        return utcDate;
    }

    /**
     * 获取两个日期相差的时间.
     */
    public static String getDatePoor(Date endDate, Date nowDate) {
        long nd = 1000L * 24 * 60 * 60;
        long nh = 1000L * 60 * 60;
        long nm = 1000L * 60;
        long ns = 1000L;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟" + sec + "秒";
    }

    /**
     * 字符串日期转long.
     */
    public static long getDate(String time, String fotmat) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(fotmat);
        LocalDateTime parse = LocalDateTime.parse(time, dtf);
        return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 字符串日期转long.
     */
    public static long getDayNum(String time) {
        String timeTmp = time.replaceAll("天", "").replaceAll("小时", "").replaceAll("分钟", "").replaceAll("秒", "");
        return Long.parseLong(timeTmp);
    }

    /**
     * *天*小时*分*秒 转换成秒.
     */
    public static long getMilsOfDate(String time) {
        String timeTmp = time.replaceAll("天", ":").replaceAll("小时", ":").replaceAll("分钟", ":").replaceAll("秒", ":");
        String[] timeSplit = timeTmp.split(":");
        long seconds = 0;
        if (timeSplit.length >= 4) {
            seconds = Long.parseLong(timeSplit[0]) * 60 * 60 * 60
                + Long.parseLong(timeSplit[1]) * 60 * 60
                + Long.parseLong(timeSplit[2]) * 60
                + Long.parseLong(timeSplit[3]);
        }
        return seconds;
    }

    /**
     * 增加天数.
     *
     * @param date 时间.
     * @param days 增加天数.
     */
    public static Date addDays(Date date, int days) {
        return (new DateTime(date)).plusDays(days).toDate();
    }

    /**
     * 减少天数.
     *
     * @param date 时间.
     * @param days 减少天数.
     */
    public static Date minusDays(Date date, int days) {
        return (new DateTime(date)).minusDays(days).toDate();
    }

    /**
     * 减少分钟.
     *
     * @param date 时间.
     * @param min 减少分钟.
     */
    public static Date minusMinutes(Date date, int min) {
        return (new DateTime(date)).minusMinutes(min).toDate();
    }

    /**
     * 增加分钟.
     *
     * @param date 时间.
     * @param min 增加分钟.
     */
    public static Date addMinutes(Date date, int min) {
        return (new DateTime(date)).plusMinutes(min).toDate();
    }

    /**
     * 减少小时.
     *
     * @param date 时间.
     * @param hour 小时.
     */
    public static Date minusHour(Date date, int hour) {
        return (new DateTime(date)).minusHours(hour).toDate();
    }

    /**
     * 增加小时.
     *
     * @param date 时间.
     * @param hour 小时.
     */
    public static Date addHour(Date date, int hour) {
        return (new DateTime(date)).plusHours(hour).toDate();
    }

    public static String formatDateToStr(Date date, String pattern) {
        return date == null ? null : (new SimpleDateFormat(pattern)).format(date);
    }

    /**
     * 替换日期.
     */
    public static String replaceTimeEngine(String location) {
        location = location.replace("${date(Y)}", getCurrentDate("yyyy"));
        location = location.replace("${date(Ym)}", getCurrentDate("yyyyMM"));
        location = location.replace("${date(Ymd)}", getCurrentDate("yyyyMMdd"));
        location = location.replace("${date(YmdH)}", getCurrentDate("yyyyMMddHH"));
        return location;
    }

    /**
     * 替换日期.
     */
    public static String replaceTimeEngine(String location, String replaceValue) {
        if (StringUtils.isNotEmpty(replaceValue)) {
            location = location.replace("${date(Y)}", replaceValue);
            location = location.replace("${date(Ym)}", replaceValue);
            location = location.replace("${date(Y-m)}", replaceValue);
            location = location.replace("${date(Ymd)}", replaceValue);
            location = location.replace("${date(Y-m-d)}", replaceValue);
            location = location.replace("${date(YmdH)}", replaceValue);
            location = location.replace("${date(Y-m-d-H)}", replaceValue);
            return location;
        }
        location = location.replace("${date(Y)}", getCurrentDate("yyyy"));
        location = location.replace("${date(Ym)}", getCurrentDate("yyyyMM"));
        location = location.replace("${date(Y-m)}", getCurrentDate("yyyy-MM"));
        location = location.replace("${date(Ymd)}", getCurrentDate("yyyyMMdd"));
        location = location.replace("${date(Y-m-d)}", getCurrentDate("yyyy-MM-dd"));
        location = location.replace("${date(YmdH)}", getCurrentDate("yyyyMMddHH"));
        location = location.replace("${date(Y-m-d-H)}", getCurrentDate("yyyy-MM-dd-HH"));
        return location;
    }

    /**
     * 获取thedate.
     * @param taskDim 周期
     * @return
     */
    public static String getTheDate(String taskDim) {
        switch (taskDim) {
            case "HHH":
            case "DDD":
                return getMinuteBefore(0);
            default:
                return getHourBefore(0);
        }
    }


    /**
     * 获取当前时间戳 精确到秒
     * @return
     */
    public static Integer getCurrentTimestampSecond(){
        return (int) (System.currentTimeMillis() / 1000);
    }

}

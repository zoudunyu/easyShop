package com.yj.util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Date;
import java.util.TimeZone;

/**
 * 日期工具
 *
 * @author 邹敦宇
 * @version 1.0
 * @date 2022-05-06 15:50:16
 */
public class DateUtil {
    public final static String FORMAT_8 = "yyyyMMdd";
    public final static String FORMAT_8_CN = "yyyy年MM月dd日";
    public final static String FORMAT_10 = "yyyyMMddHH";
    public final static String FORMAT_10_CN = "yyyy年MM月dd日HH时";
    public final static String FORMAT_14 = "yyyyMMddHHmmss";
    public final static String FORMAT_14_CN = "yyyy年MM月dd日HH时mm分ss秒";
    public final static String FORMAT_10_ = "yyyy-MM-dd";
    public final static String FORMAT_14_ = "yyyy-MM-dd HH:mm:ss";

    /**
     * Date对象转LocalDate对象
     *
     * @param date
     * @return java.time.LocalDate
     * @author 邹敦宇
     * @date 2020-05-11 16:05:03
     **/
    public static LocalDate dateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * Date对象转LocalDateTime对象
     *
     * @param date
     * @return java.time.LocalDateTime
     * @author 邹敦宇
     * @date 2020-05-11 16:04:41
     **/
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * LocalDate对象转Date对象
     *
     * @param localDate
     * @return java.time.LocalDate
     * @author 邹敦宇
     * @date 2020-05-11 16:05:03
     **/
    public static Date localDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime对象转Date对象
     *
     * @param localDateTime
     * @return java.time.LocalDateTime
     * @author 邹敦宇
     * @date 2020-05-11 16:04:41
     **/
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 字符串转Date
     *
     * @param str
     * @param pattern
     * @return java.util.Date
     * @author 邹敦宇
     * @date 2019-10-23 15:16:26
     **/
    public static Date parseDate(String str, String pattern) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        if (pattern.contains("HH") || pattern.contains("mm") || pattern.contains("ss")) {
            return localDateTimeToDate(LocalDateTime.parse(str, df));
        }
        return localDateToDate(LocalDate.parse(str, df));
    }

    /**
     * Date转字符串
     *
     * @param date
     * @param pattern
     * @return java.lang.String
     * @author 邹敦宇
     * @date 2019-10-23 15:16:29
     **/
    public static String format(Date date, String pattern) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return localDateTime.format(df);
    }

    /**
     * 获取指定格式化的日期
     *
     * @param date
     * @param pattern yyyy-MM-dd HH:mm:ss
     * @return java.util.Date
     * @author 邹敦宇
     * @date 2019-04-13 10:42:33
     **/
    public static Date formatToDate(Date date, String pattern) {
        return parseDate(format(date, pattern), "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取周第一天
     *
     * @param date
     * @return java.util.Date
     * @author 邹敦宇
     * @date 2019-04-13 10:38:59
     **/
    public static Date getStartDayOfWeek(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        TemporalField fieldIso = WeekFields.ISO.dayOfWeek();
        localDate = localDate.with(fieldIso, 1);
        return localDateToDate(localDate);
    }

    /**
     * 获取周最后一天
     *
     * @param date
     * @return java.util.Date
     * @author 邹敦宇
     * @date 2019-04-13 10:34:38
     **/
    public static Date getEndDayOfWeek(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        TemporalField fieldIso = WeekFields.ISO.dayOfWeek();
        localDate = localDate.with(fieldIso, 7);
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
    }

    /**
     * 获取月第一天
     *
     * @param date
     * @return java.util.Date
     * @author 邹敦宇
     * @date 2019-04-13 10:34:38
     **/
    public static Date getStartDayOfMonth(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        localDate = localDate.with(TemporalAdjusters.firstDayOfMonth());
        return localDateToDate(localDate);
    }

    /**
     * 获取月最后一天
     *
     * @param date
     * @return java.util.Date
     * @author 邹敦宇
     * @date 2019-04-13 10:34:38
     **/
    public static Date getEndDayOfMonth(Date date) {
        LocalDate localDate = dateToLocalDate(date);
        localDate = localDate.with(TemporalAdjusters.lastDayOfMonth());
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).plusDays(1L).minusNanos(1L).toInstant());
    }

    /**
     * 日期加
     *
     * @param date
     * @param amountToAdd
     * @param unit        ChronoUnit.DAYS
     * @return java.util.Date
     * @author 邹敦宇
     * @date 2019-04-15 17:46:16
     **/
    public static Date add(Date date, long amountToAdd, TemporalUnit unit) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime newLocalDateTime = localDateTime.plus(amountToAdd, unit);
        return localDateTimeToDate(newLocalDateTime);
    }

    /**
     * 日期减
     *
     * @param date
     * @param amountTosubtract
     * @param unit             ChronoUnit.DAYS
     * @return java.util.Date
     * @author 邹敦宇
     * @date 2019-04-15 17:46:16
     **/
    public static Date subtract(Date date, long amountTosubtract, TemporalUnit unit) {
        LocalDateTime localDateTime = dateToLocalDateTime(date);
        LocalDateTime newLocalDateTime = localDateTime.minus(amountTosubtract, unit);
        return localDateTimeToDate(newLocalDateTime);
    }

    /**
     * 转换时区,
     * <p>
     * 注意: 仅是把值按时区改变, date里的实际时区还是之前的
     * </p>
     *
     * @param date
     * @param oldZone
     * @param newZone
     * @return java.util.Date
     * @author 邹敦宇
     * @date 2019-06-06 14:40:42
     **/
    public static Date changeTimeZone(Date date, TimeZone oldZone, TimeZone newZone) {
        int timeOffset = oldZone.getRawOffset() - newZone.getRawOffset();
        return new Date(date.getTime() - timeOffset);
    }

    /**
     * 转换为UTC时区,
     * <p>
     * 注意: 仅是把值按时区改变, date里的实际时区还是之前的
     * </p>
     *
     * @param date
     * @return java.util.Date
     * @author 邹敦宇
     * @date 2019-06-06 14:45:02
     **/
    public static Date toUtcTimeZone(Date date) {
        return changeTimeZone(date, TimeZone.getDefault(), TimeZone.getTimeZone("UTC"));
    }

    /**
     * utc时间转为默认时区时间
     * <p>
     * 注意: 仅是把值按时区改变, date里的实际时区还是之前的
     * </p>
     *
     * @param date
     * @return java.util.Date
     * @author 邹敦宇
     * @date 2019-06-13 17:34:10
     **/
    public static Date utcToDefaultTimeZone(Date date) {
        return changeTimeZone(date, TimeZone.getTimeZone("UTC"),
                TimeZone.getDefault());
    }

    /**
     * 获取加减时间用的时间间隔
     *
     * @param name
     * @return java.time.temporal.TemporalUnit
     * @author 邹敦宇
     * @date 2020-03-05 19:22:26
     **/
    public static TemporalUnit getChronoUnit(String name) {
        TemporalUnit temporalUnit;
        switch (name) {
            case "seconds":
                temporalUnit = ChronoUnit.SECONDS;
                break;
            case "minutes":
                temporalUnit = ChronoUnit.MINUTES;
                break;
            case "hours":
                temporalUnit = ChronoUnit.HOURS;
                break;
            case "days":
                temporalUnit = ChronoUnit.DAYS;
                break;
            case "weeks":
                temporalUnit = ChronoUnit.WEEKS;
                break;
            case "months":
                temporalUnit = ChronoUnit.MONTHS;
                break;
            case "years":
                temporalUnit = ChronoUnit.YEARS;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + name);
        }
        return temporalUnit;
    }

    /**
     * 获取指定格式时间
     *
     * @param localDateTime
     * @param fmt
     * @return
     */
    public static String localDateTimeToString(LocalDateTime localDateTime, String fmt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(fmt);
        return localDateTime.format(formatter);
    }

    /**
     * 根据时间字符串获取日期时间对象
     *
     * @param timeStr
     * @param fmt
     * @return
     */
    public static LocalDateTime stringToLocalDateTime(String timeStr, String fmt) {
        Date date = parseDate(timeStr, fmt);
        return dateToLocalDateTime(date);
    }

    public static void main(String[] args) {
        Date now = new Date();
        System.out.println(formatToDate(now, "yyyy-MM-dd 11:12:13"));
        System.out.println(getStartDayOfWeek(now));
        System.out.println(getEndDayOfWeek(now));
        System.out.println(getStartDayOfMonth(now));
        System.out.println(getEndDayOfMonth(now));
        System.out.println(add(now, 1, ChronoUnit.DAYS));
        System.out.println(subtract(now, 1, ChronoUnit.HOURS));
        System.out.println(parseDate("2019-05-14", "yyyy-MM-dd"));
        System.out.println(toUtcTimeZone(new Date()));
    }
}

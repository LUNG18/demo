package com.puzhong.admin.utils;


import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 日期处理
 */
public class DateUtils {

    /**
     * 时间格式(yyyy-MM-dd)
     */
    public static final String DATE_PATTERN = "yyyy-MM-dd";

    /**
     * 时间格式(yyyy-MM-dd HH:mm)
     */
    public static final String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm";

    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public static final String DATE_TIME_PATTERN_ALL = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIME_FORMAT = "yyyyMMddHHmmss";

    public static final String DATE_TIME_FORMAT2 = "yyyyMMdd";

    public static final String DATE_TIMESTAMP_FORMAT = "yyyyMMddHHmmssSSS";
    private static final String datePattern = "^\\d{4}(\\-)\\d{1,2}(\\-)\\d{1,2}$";
    private static final String datePattern1 = "^\\d{4}(\\/)\\d{1,2}(\\/)\\d{1,2}$";
    private static final String datePattern2 = "^\\d{4}(\\.)\\d{1,2}(\\.)\\d{1,2}$";
    private static final String datePattern3 = "^\\d{8}$";

    /**
     * @param dateStr
     * @return
     */
    public static String format(String dateStr) {

        if (StringUtils.hasText(dateStr)) {
            Pattern p = Pattern.compile(datePattern);
            Matcher matcher = p.matcher(dateStr);
            if (matcher.matches()) {
                return parseAndFormat(dateStr, "yyyy-MM-dd", "yyyyMMdd");
            }

            Pattern p1 = Pattern.compile(datePattern1);
            Matcher matcher1 = p1.matcher(dateStr);
            if (matcher1.matches()) {
                return parseAndFormat(dateStr, "yyyy/MM/dd", "yyyyMMdd");
            }

            Pattern p2 = Pattern.compile(datePattern2);
            Matcher matcher2 = p2.matcher(dateStr);
            if (matcher2.matches()) {
                return parseAndFormat(dateStr, "yyyy.MM.dd", "yyyyMMdd");
            }

            Pattern p3 = Pattern.compile(datePattern3);
            Matcher matcher3 = p3.matcher(dateStr);
            if (matcher3.matches()) {
                return dateStr;
            }
        }
        return null;

    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date 日期
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date) {
        return format(date, DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if (date != null) {
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 日期解析
     *
     * @param date    日期
     * @param pattern 格式，如：DateUtils.DATE_TIME_PATTERN
     * @return 返回Date
     */
    public static Date parse(String date, String pattern) {
        try {
            return new SimpleDateFormat(pattern).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String parseAndFormat(String dataStr, String oldPattern, String newPattern) {
        String result = "";
        if (StringUtils.hasText(dataStr) && isValidDate(dataStr, oldPattern)) {
            result = format(parse(dataStr, oldPattern), newPattern);
        }
        return result;
    }

    /**
     * 校验日期是否合法
     */
    public static boolean isValidDate(String s, String pattern) {
        return parse(s, pattern) != null;
    }

    /**
     * 格式化日期
     *
     * @return
     */
    public static Date parseDateTime(String date) {
        return parse(date, "yyyyMMddHHmmss");
    }

    public static String formatDate(Date date, String pattern) {
        if (StringUtils.isEmpty(pattern)) {
            pattern =  "yyyy-MM-dd";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay() {
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    /**
     * 获取YYYY-MM-DD格式
     *
     * @return
     */
    public static String getDay(Date date) {
        return formatDate(date, "yyyy-MM-dd");
    }

    public static String getTime(Date date) {
        return format(date, DATE_TIME_FORMAT);
    }

    /**
     * yyyyMMddHHmmss
     *
     * @return
     */
    public static String getTime() {
        return format(new Date(), DATE_TIME_FORMAT);
    }

    /**
     * 时间戳
     *
     * @return
     */
    public static Long getLongTime() {
        return System.currentTimeMillis();
    }

    public static String getTime2() {
        return format(new Date(), DATE_TIME_FORMAT2);
    }

    /**
     * 得到n天之后的日期
     *
     * @param days
     * @return
     */
    public static String getAfterDayDate(String days) {
        int daysInt = Integer.parseInt(days);

        Calendar canlendar = Calendar.getInstance(); // java.util包
        canlendar.add(Calendar.DATE, daysInt); // 日期减 如果不够减会将月变动
        Date date = canlendar.getTime();

        SimpleDateFormat sdfd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = sdfd.format(date);

        return dateStr;
    }

    /**
     * 获取的当前小时
     *
     * @return
     */
    public static int getHour() {
        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.HOUR);
    }

    /**
     * 是否是晚上
     *
     * @return
     */
    public static boolean isNight() {
        int hour = getHour();

        return hour > 18 || hour < 6;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static String getCurrentTimestamp() {
        return formatDate(new Date(), DATE_TIMESTAMP_FORMAT);
    }

}

package com.madison.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author Madison
 * @Date 2022/1/25 上午11:21
 * @Description
 */
public class DateUtils {

    //一天的毫秒数
    private static final long ONE_DAY_MILLISECONDS = 1 * 24 * 60 * 60 * 1000;
    //默认日期格式
    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    /**
     * @Author Madison
     * @Description: 传入两个时间，返回之间日期数组(包含首尾)
     * @DateTime: 2022/1/25 上午11:30
     * @Params: [start, end, dateFormat]
     * @Return java.lang.String[]
     */
    public static String[] betweenDaysStrArr(Date start, Date end, String dateFormat) {
        int diff = diffDates(start, end);
        String[] arr = new String[diff];
        SimpleDateFormat sdf = new SimpleDateFormat(StringUtils.isBlank(dateFormat) ? DEFAULT_DATE_FORMAT : dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start.after(end) ? end : start);
        for (int i = 0; i < diff; i++) {
            if (i != 0) {
                calendar.add(Calendar.DAY_OF_YEAR, 1);
            }
            arr[i] = sdf.format(calendar.getTime());
        }
        return arr;
    }

    /**
     * @Author Madison
     * @Description: 获取两个日期之差天数，包含最后一天
     * @DateTime: 2022/1/25 下午1:58
     * @Params: [start, end]
     * @Return int
     */
    public static int diffDates(Date start, Date end) {
        long diffMilSecs = end.getTime() - start.getTime();
        if (diffMilSecs < 0) {
            diffMilSecs = -diffMilSecs;
        }
        return (int) (diffMilSecs / ONE_DAY_MILLISECONDS) + 1;
    }

    /**
     * @Author Madison
     * @Description: 格式化日期 => 字符串，默认yyyy-MM-dd
     * @DateTime: 2022/1/25 下午2:08
     * @Params: [date, format]
     * @Return java.lang.String
     */
    public static String formateDate2Str(Date date, String format) {
        return date == null ? "" :
                new SimpleDateFormat(StringUtils.isBlank(format) ? DEFAULT_DATE_FORMAT : format).format(date);
    }

    /**
     * @Author Madison
     * @Description: 字符串 => 日期，默认yyyy-MM-dd
     * @DateTime: 2022/1/25 下午2:11
     * @Params: [str, format]
     * @Return java.util.Date
     */
    public static Date formateStr2Date(String str, String format) throws ParseException {
        return StringUtils.isBlank(str) ? null :
                new SimpleDateFormat(StringUtils.isBlank(format) ? DEFAULT_DATE_FORMAT : format).parse(str);
    }
}

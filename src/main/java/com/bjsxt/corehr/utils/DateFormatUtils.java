package com.bjsxt.corehr.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @className: DateFormatUtils
 * @description: 格式化日期工具类
 * @author: RenDeYou
 * @date: 2021/4/20 17:04
 */
public class DateFormatUtils {
    private DateFormatUtils() {
    }

    /**
     * 获取当前日期
     */
    public static Date getNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(new Date());
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取指定格式的指定时间
     */
    public static Date getSpecifiedDate(String strDate, String str) {
        if (null == strDate || StringUtils.isBlank(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 获取指定格式的指定时间
     */
    public static Date getSpecifiedDate(Date date, String str) {
        if (null == date || StringUtils.isBlank(str)) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(str);
        String strDate = sdf.format(date);
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
}

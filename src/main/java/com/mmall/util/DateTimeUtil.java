package com.mmall.util;


import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * @author jiangxl
 * @description
 * @date 2018-06-04 17:39
 **/
public class DateTimeUtil {

    public static final String STANDARD_FORMAT = "yyyy-MM-dd HH:mm:ss";

    //joda-time
    //str->date
    public static Date strToDate(String dateTimeStr, String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern(formatStr);
        DateTime dateTime = dateTimeFormatter.parseDateTime(dateTimeStr);
        return dateTime.toDate();
    }
    //date->str

    public static String dateToStr(Date date, String formatStr) {
        if (date == null) {
            return StringUtils.EMPTY;
        }
        DateTime dateTime = new DateTime(date);
        return dateTime.toString(formatStr);

    }

    public static Date strToDate(String dateTimeStr) {

        return strToDate(dateTimeStr, STANDARD_FORMAT);
    }
    //date->str

    public static String dateToStr(Date date) {

        return dateToStr(date, STANDARD_FORMAT);

    }

    public static void main(String[] args) {
        System.out.println(dateToStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
        System.out.println(strToDate("2018-06-04 00:00:00", "yyyy-MM-dd HH:mm:ss"));

    }
}

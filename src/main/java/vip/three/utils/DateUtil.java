package vip.three.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // 字符串转 util data
    public static Date stringToUtilDate(String dateStr) {
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    // util 转 sql
    public static java.sql.Date utilDateToSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    // util 转 string
    public static String utilDateToString(Date date) {
        return sdf.format(date);
    }
}

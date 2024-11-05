package vip.dengwj.person;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date 2024/11/5 22:16
 * @author 朴睦
 * @description 日期转换工具类
 */
public class DateUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // 字符串转为 util date
    public static java.util.Date stringToUtilDate(String date) {
        Date parse = null;
        try {
            parse = sdf.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return parse;
    }

    // util date 转换为 sql date
    public static java.sql.Date utilDateToSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    // util date 转换为字符串
    public static String utilDateToString(java.util.Date date) {
        return sdf.format(date);
    }
}

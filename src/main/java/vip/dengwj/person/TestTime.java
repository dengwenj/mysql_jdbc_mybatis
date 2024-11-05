package vip.dengwj.person;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestTime {
    public static void main(String[] args) throws Exception {
        String date = "2024-11-05";

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 转换成 util data
        Date parse = simpleDateFormat.parse(date);
        System.out.println("parse -> " + parse); // parse -> Tue Nov 05 00:00:00 CST 2024

        String format = simpleDateFormat.format(parse);
        System.out.println("format -> " + format); // 2024-11-05

        // 转换成 sql date
        java.sql.Date sqlDate = new java.sql.Date(parse.getTime());
        System.out.println(sqlDate);
    }
}

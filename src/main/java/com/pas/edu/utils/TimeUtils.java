package com.pas.edu.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    static SimpleDateFormat sdf;

    static {
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 时间戳转时间字符串
     */
    public static String timestamp2DateString(long timeStamp) throws ParseException {
        System.out.print("当前时间字符串为：" + sdf.format(System.currentTimeMillis()) + "\n");
        return sdf.format(timeStamp);
    }

    /**
     * 时间字符串转成时间戳
     */
    public static long dateString2Timestamp(String timeStamp) throws ParseException {
        Date date = sdf.parse(timeStamp);
        System.out.print("当前时间戳为：" + date.getTime() + "\n");
        return date.getTime();
    }


    /**
     * 时间戳转时间Date
     */
    public static Date timestamp2Date(long timeStamp) throws ParseException {
        Date date = sdf.parse(sdf.format(timeStamp));
        System.out.print("当前时间为：" + date + "\n");
        return date;
    }

    public static long date2Timestamp(Date date) {
        Date mDate = new Date();
        System.out.print("当前时间戳：" + mDate.getTime() + "\n");
        System.out.print("System获得时间戳 ：" + System.currentTimeMillis()+"\n");
        return mDate.getTime();
    }


}

package com.freddy.kulachat.utils;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class DateUtil {

    public static final String TIME_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 格式化时间
     *
     * @param date   需要被处理的日期,距离1970的long
     * @param format 最终返回的日期字符串的格式串
     * @return
     */
    public static String formatDate(long date, String format) {
        @SuppressLint("SimpleDateFormat") DateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}

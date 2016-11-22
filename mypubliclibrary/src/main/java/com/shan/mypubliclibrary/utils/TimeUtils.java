package com.shan.mypubliclibrary.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间工具类
 * Created by chenjunshan on 2016/9/7.
 */

public class TimeUtils {
    /**
     * 获取系统时间
     *
     * @param format
     * @return
     */
    public static String getTime(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date curDate = new Date(System.currentTimeMillis());// 获取当前时间
        return formatter.format(curDate);
    }
}

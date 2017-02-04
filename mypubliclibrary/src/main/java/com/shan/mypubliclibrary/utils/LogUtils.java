package com.shan.mypubliclibrary.utils;

import android.util.Log;

/**
 * 打印日志
 * Created by chenjunshan on 2016/7/5.
 */
public class LogUtils {
    public static boolean isLog = true;

    /**
     * info的日志
     *
     * @param info
     */
    public static void i(String info) {
        if (!isLog)
            return;
        Log.i("info", info);
    }

    /**
     * debug的日志
     *
     * @param debug
     */
    public static void d(String debug) {
        if (!isLog)
            return;
        Log.d("debug", debug);
    }

    /**
     * error的日志
     *
     * @param error
     */
    public static void e(String error) {
        if (!isLog)
            return;
        Log.e("error", error);
    }

    /**
     * warn的日志
     *
     * @param warn
     */
    public static void w(String warn) {
        if (!isLog)
            return;
        Log.w("warn", warn);
    }

    /**
     * verbose模式,打印最详细的日志
     *
     * @param verbose
     */
    public static void v(String verbose) {
        if (!isLog)
            return;
        Log.v("verbose", verbose);
    }
}

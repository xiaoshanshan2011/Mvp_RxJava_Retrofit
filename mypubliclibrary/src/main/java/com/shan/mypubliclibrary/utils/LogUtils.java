package com.shan.mypubliclibrary.utils;

import android.util.Log;

/**
 * 打印日志
 * Created by chenjunshan on 2016/7/5.
 */
public class LogUtils {
    /**
     * info的日志
     *
     * @param info
     */
    public static void i(String info) {
        Log.i("info", info);
    }

    /**
     * debug的日志
     *
     * @param debug
     */
    public static void d(String debug) {
        Log.d("debug", debug);
    }

    /**
     * error的日志
     *
     * @param error
     */
    public static void e(String error) {
        Log.e("error", error);
    }

    /**
     * warn的日志
     *
     * @param warn
     */
    public static void w(String warn) {
        Log.w("warn", warn);
    }

    /**
     * verbose模式,打印最详细的日志
     *
     * @param verbose
     */
    public static void v(String verbose) {
        Log.v("verbose", verbose);
    }
}

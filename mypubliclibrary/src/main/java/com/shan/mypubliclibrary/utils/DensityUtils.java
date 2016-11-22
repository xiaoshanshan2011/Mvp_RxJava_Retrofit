package com.shan.mypubliclibrary.utils;

import android.util.TypedValue;

import com.shan.mypubliclibrary.App;

/**
 * 单位转换工具类
 * Created by chenjunshan on 2016/7/31.
 */

public class DensityUtils {
    /**
     * dp转px
     *
     * @return
     */
    public static int dp2px(float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, App.getApp().getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @return
     */
    public static int sp2px(float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, App.getApp().getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @return
     */
    public static float px2dp(float pxVal) {
        final float scale = App.getApp().getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param pxVal
     * @return
     */
    public static float px2sp(float pxVal) {
        return (pxVal / App.getApp().getResources().getDisplayMetrics().scaledDensity);
    }
}

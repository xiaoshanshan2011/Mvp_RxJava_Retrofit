package com.shan.mypubliclibrary.manager;

import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by 陈俊山 on 2016/1/21.
 */
public class StatusBar {
    private static Activity mActivity;

    /**
     * 设置状态栏的颜色
     *
     * @param activity
     * @param res      需要设置的颜色
     */
    public static void showStatusBar(Activity activity, int res) {
        mActivity = activity;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        // 创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(mActivity);
        // 激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        // 设置一个样式背景给状态栏
        tintManager.setStatusBarTintResource(res);
    }

    private static void setTranslucentStatus(boolean on) {
        Window win = mActivity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}

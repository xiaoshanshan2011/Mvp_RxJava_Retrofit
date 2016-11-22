package com.shan.mvp_rxjava_retrofit;

import com.shan.mypubliclibrary.App;

/**
 * Created by 陈俊山 on 2016/7/5.
 */
public class MyApp extends App {
    private static MyApp instance;

    public static MyApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        init();
    }

    /**
     * MyApp初始化操作
     */
    private void init() {
        instance = (MyApp) getApplicationContext();
    }
}

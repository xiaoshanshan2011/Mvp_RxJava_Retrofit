package com.shan.mvp_rxjava_retrofit;

import com.shan.amaplibrary.location.LocationManager;
import com.shan.mypubliclibrary.App;
import com.shan.sharelibrary.ShareUtils;

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
        ShareUtils.initShare(this);
    }

    /**
     * MyApp初始化操作
     */
    private void init() {
        instance = (MyApp) getApplicationContext();
    }

    private LocationManager mLocationManager = null;

    public LocationManager getLocationManager() {
        if (mLocationManager == null) {
            mLocationManager = new LocationManager(this);
        }
        return mLocationManager;
    }

    public void destroyLocation() {
        mLocationManager.destroyLocation();
        mLocationManager = null;
    }
}

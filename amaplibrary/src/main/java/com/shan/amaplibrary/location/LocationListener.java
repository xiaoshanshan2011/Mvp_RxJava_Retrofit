package com.shan.amaplibrary.location;

import com.amap.api.location.AMapLocation;

/**
 * Created by root on 17-2-6.
 */

public interface LocationListener {
    //定位成功
    void locationSuccess(AMapLocation location);
    //定位失败
    void locationFailure();
}

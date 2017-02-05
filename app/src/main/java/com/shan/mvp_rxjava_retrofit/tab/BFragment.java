package com.shan.mvp_rxjava_retrofit.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.shan.mvp_rxjava_retrofit.MyApp;
import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentBBinding;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class BFragment extends BaseFragment<FragmentBBinding, Object> {
    @Override
    public int bindLayout() {
        return R.layout.fragment_b;
    }

    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //解析定位结果
                }
            }
        }
    };

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);
        mBinding.mapView.onCreate(savedInstanceState);// 此方法必须重写
        AMap aMap = mBinding.mapView.getMap();

        //初始化定位
        mLocationClient = new AMapLocationClient(MyApp.getInstance());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //启动定位
        mLocationClient.startLocation();
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("周边");
        titleBinding.btnLeft.setVisibility(View.INVISIBLE);
    }
}

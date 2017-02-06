package com.shan.mvp_rxjava_retrofit.tab;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.LocationSource;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.shan.amaplibrary.location.LocationListener;
import com.shan.mvp_rxjava_retrofit.MyApp;
import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentBBinding;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;
import com.shan.mypubliclibrary.utils.ToastUtils;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class BFragment extends BaseFragment<FragmentBBinding, Object> implements LocationListener,LocationSource,AMap.OnMapClickListener {
    @Override
    public int bindLayout() {
        return R.layout.fragment_b;
    }

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);
        initMap(savedInstanceState);
        initEvent();
    }

    /**
     * 初始化地图
     */
    private void initMap(Bundle savedInstanceState) {
        mBinding.mapView.onCreate(savedInstanceState);// 此方法必须重写
        AMap mAMap = mBinding.mapView.getMap();
        mAMap.getUiSettings().setRotateGesturesEnabled(false);
        mAMap.moveCamera(CameraUpdateFactory.zoomBy(6));
        mAMap.setLocationSource(this);// 设置定位监听
        mAMap.setOnMapClickListener(this);
        mAMap.getUiSettings().setMyLocationButtonEnabled(true);// 设置默认定位按钮是否显示
        // 自定义系统定位蓝点
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        // 自定义定位蓝点图标
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_location));
        // 自定义精度范围的圆形边框颜色
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));
        // 自定义精度范围的圆形边框宽度
        myLocationStyle.strokeWidth(0);
        // 设置圆形的填充颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));
        // 将自定义的 myLocationStyle 对象添加到地图上
        mAMap.setMyLocationStyle(myLocationStyle);
        mAMap.setMyLocationEnabled(true);// 设置为true表示显示定位层并可触发定位，false表示隐藏定位层并不可触发定位，默认是false
        // 设置定位的类型为定位模式 ，可以由定位、跟随或地图根据面向方向旋转几种
        mAMap.setMyLocationType(AMap.LOCATION_TYPE_LOCATE);
    }

    private void initEvent() {

    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("周边");
        titleBinding.btnLeft.setVisibility(View.GONE);
        titleBinding.tvTitle.setTextSize(15);
        Drawable drawable = getActivity().getResources().getDrawable(R.mipmap.ic_title_location);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        titleBinding.tvTitle.setCompoundDrawables(drawable, null, null, null);
    }

    @Override
    public void onStart() {
        super.onStart();
        MyApp.getInstance().getLocationManager().startLocation(this);
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
        }
    }

    @Override
    public void locationSuccess(AMapLocation location) {
        setTitle(location.getPoiName());
        mListener.onLocationChanged(location);// 显示系统小蓝点
    }

    @Override
    public void locationFailure() {
        ToastUtils.toast("定位失败");
    }

    @Override
    public void onResume() {
        super.onResume();
        mBinding.mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBinding.mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding.mapView.onDestroy();
    }

    private OnLocationChangedListener mListener;

    @Override
    public void activate(OnLocationChangedListener listener) {
        mListener = listener;
    }

    @Override
    public void deactivate() {
        mListener = null;
    }

    @Override
    public void onMapClick(LatLng latLng) {

    }
}

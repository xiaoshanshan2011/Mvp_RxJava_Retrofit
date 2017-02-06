package com.shan.mvp_rxjava_retrofit.tab;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.shan.amaplibrary.location.LocationListener;
import com.shan.mvp_rxjava_retrofit.MyApp;
import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentBBinding;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;
import com.shan.mypubliclibrary.utils.ToastUtils;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class BFragment extends BaseFragment<FragmentBBinding, Object> implements LocationListener {
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
        AMap aMap = mBinding.mapView.getMap();
    }

    private void initEvent() {
        mBinding.btnLocation.setOnClickListener(this);
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
            case R.id.btn_location:
                MyApp.getInstance().getLocationManager().startLocation(this);
                break;
        }
    }

    @Override
    public void locationSuccess(AMapLocation location) {
        setTitle(location.getPoiName() + "附近");
    }

    @Override
    public void locationFailure() {
        ToastUtils.toast("定位失败");
    }
}

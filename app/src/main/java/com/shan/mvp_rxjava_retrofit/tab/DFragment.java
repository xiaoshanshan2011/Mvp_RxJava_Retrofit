package com.shan.mvp_rxjava_retrofit.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentDBinding;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class DFragment extends BaseFragment<FragmentDBinding, Object> {
    @Override
    public int bindLayout() {
        return R.layout.fragment_d;
    }

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("个人");
        titleBinding.btnLeft.setVisibility(View.GONE);
        titleBinding.btnRight.setVisibility(View.VISIBLE);
        titleBinding.btnRight.setImageResource(R.mipmap.ic_setting);
    }
}

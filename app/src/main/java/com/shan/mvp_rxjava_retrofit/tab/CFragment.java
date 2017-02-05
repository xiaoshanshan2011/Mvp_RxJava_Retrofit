package com.shan.mvp_rxjava_retrofit.tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentCBinding;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class CFragment extends BaseFragment<FragmentCBinding, Object> {
    @Override
    public int bindLayout() {
        return R.layout.fragment_c;
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("消息");
        titleBinding.btnLeft.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);
    }
}

package com.shan.mvp_rxjava_retrofit.tab;

import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentDBinding;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class CFragment extends BaseFragment<FragmentDBinding, Object> {
    @Override
    public int bindLayout() {
        return R.layout.fragment_b;
    }

    @Override
    public void initOnCreate() {
        super.initOnCreate();
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("圈子");
        titleBinding.btnLeft.setVisibility(View.INVISIBLE);
    }
}

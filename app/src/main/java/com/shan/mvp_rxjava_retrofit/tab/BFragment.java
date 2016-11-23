package com.shan.mvp_rxjava_retrofit.tab;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentBBinding;
import com.shan.mypubliclibrary.ui.fragment.LibFragment;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class BFragment extends LibFragment<FragmentBBinding,Object> {
    @Override
    public int bindLayout() {
        return R.layout.fragment_b;
    }

    @Override
    public void initOnCreate() {
        super.initOnCreate();
        mBinding.textView3.setText("123212312");
    }
}

package com.shan.mvp_rxjava_retrofit.tab;

import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.bean.TestBean;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentABinding;
import com.shan.mvp_rxjava_retrofit.databinding.ItemBinding;
import com.shan.mypubliclibrary.adapter.CommonAdapter;
import com.shan.mypubliclibrary.ui.fragment.BaseFragment;
import com.shan.mypubliclibrary.utils.ImageCacheUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class AFragment extends BaseFragment<FragmentABinding, Object> {
    @Override
    public int bindLayout() {
        return R.layout.fragment_a;
    }

    @Override
    public void initOnCreate() {
        super.initOnCreate();
        List<TestBean> list = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            list.add(new TestBean("ssssss" + i));
        }

        mBinding.lisView.setAdapter(new CommonAdapter<ItemBinding, TestBean>(getActivity(), R.layout.item, list) {
            @Override
            protected void getItem(ItemBinding binding, TestBean bean, int position) {
                binding.textView4.setText(bean.getName());
            }
        });

        ImageCacheUtils.loadImage(getActivity(), "http://t1.niutuku.com/960/38/38-82994.jpg", mBinding.imageView);
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("框架之美");
        titleBinding.btnLeft.setVisibility(View.INVISIBLE);
        titleBinding.btnRight.setVisibility(View.INVISIBLE);
    }
}

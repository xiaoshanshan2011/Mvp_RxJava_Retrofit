package com.shan.mvp_rxjava_retrofit.tab;

import android.view.Gravity;
import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentBBinding;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;
import com.shan.mypubliclibrary.utils.ImageCacheUtils;
import com.shan.mypubliclibrary.widget.CommonDialog;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class BFragment extends BaseFragment<FragmentBBinding, Object> {
    @Override
    public int bindLayout() {
        return R.layout.fragment_b;
    }

    @Override
    public void initOnCreate() {
        super.initOnCreate();
        mBinding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CommonDialog dialog = new CommonDialog.Builder(getActivity())
                        .setResId(R.layout.dialog_test)
                        .setGravity(Gravity.CENTER)
                        .setWidth(0.8f)
                        .setAnimResId(R.style.dialog_in_out)
                        .build();
                dialog.show();
            }
        });

        mBinding.circleImg.setBorderWidth(10);
        mBinding.circleImg.setBorderColor(getActivity().getResources().getColor(R.color.gray));
        ImageCacheUtils.loadImage(getActivity(),"http://img2.touxiang.cn/file/20161011/e2556c24beea824f82ce4dddc0f5f311.jpg",mBinding.circleImg);
    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("周边");
        titleBinding.btnLeft.setVisibility(View.INVISIBLE);
    }
}

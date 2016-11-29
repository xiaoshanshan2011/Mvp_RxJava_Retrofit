package com.shan.mvp_rxjava_retrofit.tab;

import android.view.Gravity;
import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.DialogTestBinding;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentBBinding;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;
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
                DialogTestBinding binding = (DialogTestBinding) dialog.show();
            }
        });
    }
}

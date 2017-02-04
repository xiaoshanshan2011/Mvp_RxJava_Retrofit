package com.shan.mvp_rxjava_retrofit.tab;

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
        setTitle("圈子");
        titleBinding.btnLeft.setVisibility(View.INVISIBLE);
    }

    @Override
    public void initOnCreate() {
        super.initOnCreate();

        mBinding.textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*new DownloadFile(getActivity()) {
                    @Override
                    protected void onProgress(float fraction) {
                        Log.i(TAG, "onProgress: " + fraction);
                    }

                    @Override
                    protected void onFinish() {
                        Log.i(TAG, "onFinish: ");
                    }
                }.start("http://img2.niutuku.com/desk/1208/1405/ntk-1405-4033.jpg");*/
            }
        });
    }
}

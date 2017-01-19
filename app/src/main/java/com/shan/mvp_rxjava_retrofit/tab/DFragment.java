package com.shan.mvp_rxjava_retrofit.tab;

import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.bean.PieBean;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentDBinding;
import com.shan.mvp_rxjava_retrofit.ui.fragment.BaseFragment;
import com.shan.mvp_rxjava_retrofit.widget.customview.CheckView;
import com.shan.mypubliclibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 陈俊山 on 2016/8/31.
 */

public class DFragment extends BaseFragment<FragmentDBinding, Object> {
    @Override
    public int bindLayout() {
        return R.layout.fragment_d;
    }

    @Override
    public void initOnCreate() {
        super.initOnCreate();

        /**************************************PieView**************************************/
        List<PieBean> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new PieBean("haha" + i, i));
        }
        /*PieView pieView = new PieView(getActivity());
        pieView.setPieBeens(list);
        pieView.setStartAngel(10f);
        LinearLayout linearLayout = (LinearLayout) mBinding.getRoot();
        linearLayout.addView(pieView);*/
        mBinding.pieview.setStartAngel(10f);
        mBinding.pieview.setPieBeens(list);

        /**************************************CheckView**************************************/
        mBinding.btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.checkview.start();
            }
        });
        mBinding.btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBinding.checkview.stop();
            }
        });
        mBinding.checkview.setOnCheckViewListener(new CheckView.OnCheckViewListener() {
            @Override
            public void startFinish() {
                ToastUtils.toast("startFinish");
            }

            @Override
            public void stopFinish() {
                ToastUtils.toast("stopFinish");
            }
        });

        /**************************************ButtonView01**************************************/

    }

    @Override
    public void initTitleBar() {
        super.initTitleBar();
        setTitle("自定义View");
        titleBinding.btnLeft.setVisibility(View.INVISIBLE);
    }
}

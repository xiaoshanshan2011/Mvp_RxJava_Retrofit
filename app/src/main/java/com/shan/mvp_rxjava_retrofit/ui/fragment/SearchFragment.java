package com.shan.mvp_rxjava_retrofit.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.FragmentSearchLayoutBinding;

/**
 * Created by chenjunshan on 17-2-5.
 */

public class SearchFragment extends BaseFragment<FragmentSearchLayoutBinding, Object> {
    @Override
    public int bindLayout() {
        return R.layout.fragment_search_layout;
    }

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);

    }
}

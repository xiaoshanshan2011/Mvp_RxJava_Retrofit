package com.shan.mvp_rxjava_retrofit.tab;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.ActivityMainBinding;
import com.shan.mvp_rxjava_retrofit.ui.activity.BaseActivity;
import com.shan.mypubliclibrary.listener.TitleBarListener;
import com.shan.mypubliclibrary.manager.TabManager;

public class MainActivity extends BaseActivity<ActivityMainBinding, Object> implements TitleBarListener {

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initOnCreate(@Nullable Bundle savedInstanceState) {
        super.initOnCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        TabManager tabManager = new TabManager(this, binding.tabhost, TabConstant.MAIN_FRAGMENT, TabConstant.MAIN_IAMGEVIEW, TabConstant.MAIN_TEXTVIEW);
        tabManager.initTab(R.id.fl_content);
    }

    @Override
    public void setTitleBarTitle(int position) {
        setTitle(TabConstant.MAIN_TEXTVIEW[position]);
    }
}

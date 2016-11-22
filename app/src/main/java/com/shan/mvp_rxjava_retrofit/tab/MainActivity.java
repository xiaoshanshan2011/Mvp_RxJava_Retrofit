package com.shan.mvp_rxjava_retrofit.tab;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mypubliclibrary.databinding.ActivityMainBinding;
import com.shan.mypubliclibrary.listener.TitleBarListener;
import com.shan.mypubliclibrary.manager.TabManager;

public class MainActivity extends FragmentActivity implements TitleBarListener {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        TabManager tabManager = new TabManager(this, binding.tabhost, TabConstant.MAIN_FRAGMENT, TabConstant.MAIN_IAMGEVIEW, TabConstant.MAIN_TEXTVIEW);
        tabManager.initTab(R.id.fl_content);
    }

    @Override
    public void setTitleBarTitle(int position) {
        setTitle(TabConstant.MAIN_TEXTVIEW[position]);
    }
}

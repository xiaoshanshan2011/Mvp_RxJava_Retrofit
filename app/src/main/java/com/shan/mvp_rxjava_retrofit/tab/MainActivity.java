package com.shan.mvp_rxjava_retrofit.tab;

import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.ActivityMainBinding;
import com.shan.mvp_rxjava_retrofit.ui.activity.BaseActivity;
import com.shan.mypubliclibrary.listener.TitleBarListener;
import com.shan.mypubliclibrary.manager.StatusBar;
import com.shan.mypubliclibrary.manager.TabManager;

public class MainActivity extends FragmentActivity implements TitleBarListener{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //禁止横竖屏切换
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏颜色
        StatusBar.showStatusBar(this, com.shan.mypubliclibrary.R.color.color_0062fd);

        iniTab();
    }

    private void iniTab() {
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        TabManager tabManager = new TabManager(this, binding.tabhost, TabConstant.MAIN_FRAGMENT, TabConstant.MAIN_IAMGEVIEW, TabConstant.MAIN_TEXTVIEW);
        tabManager.initTab(R.id.fl_content);
    }

    @Override
    public void setTitleBarTitle(int position) {

    }
}

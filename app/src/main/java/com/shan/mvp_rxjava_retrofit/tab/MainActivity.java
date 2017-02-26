package com.shan.mvp_rxjava_retrofit.tab;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.shan.mvp_rxjava_retrofit.MyApp;
import com.shan.mvp_rxjava_retrofit.R;
import com.shan.mvp_rxjava_retrofit.databinding.ActivityMainBinding;
import com.shan.mypubliclibrary.listener.TitleBarListener;
import com.shan.mypubliclibrary.manager.StatusBar;
import com.shan.mypubliclibrary.manager.TabManager;
import com.umeng.socialize.UMShareAPI;

public class MainActivity extends FragmentActivity implements TitleBarListener {

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyApp.getInstance().destroyLocation();
    }

    /*private CommonDialog dialog = null;
    private Hint1LayoutBinding mHint1LayoutBinding = null;
    public void toastLocation(AMapLocation location) {
        if (dialog == null) {
            dialog = new CommonDialog.Builder(this)
                    .setResId(R.layout.hint1_layout)
                    .setShape(CommonDialog.CIRCLE)
                    .build();
            mHint1LayoutBinding = (Hint1LayoutBinding) dialog.getBinding();
            mHint1LayoutBinding.btnOk.setOnClickListener(this);
            mHint1LayoutBinding.btnCancel.setOnClickListener(this);
        }
        mHint1LayoutBinding.tvMsg.setText("你的当前位置实在" + location.getCity() + "，是否要切换到" + location.getCity() + "？");
        dialog.show();
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //分享或授权回调
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }
}

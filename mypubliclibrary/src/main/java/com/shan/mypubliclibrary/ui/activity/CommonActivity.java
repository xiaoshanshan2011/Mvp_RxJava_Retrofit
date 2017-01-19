package com.shan.mypubliclibrary.ui.activity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.shan.mypubliclibrary.R;
import com.shan.mypubliclibrary.manager.StatusBar;


/**
 * Created by 陈俊山 on 16/8/30.
 */

public class CommonActivity extends FragmentActivity {

    public static final String FRAGMENT_CLASS = "fragment_class";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        //禁止横竖屏切换
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //设置状态栏颜色
        StatusBar.showStatusBar(this, R.color.color_ff9c0d0d);

        try {
            Class fragmentClass = (Class) getIntent().getSerializableExtra(FRAGMENT_CLASS);
            if (fragmentClass == null)
                return;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            Fragment fragment = null;
            fragment = (Fragment) fragmentClass.newInstance();
            if (fragment == null)
                return;
            transaction.replace(R.id.frame_common, fragment, fragmentClass.getName());
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

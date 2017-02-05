package com.shan.mypubliclibrary.manager;

import android.databinding.DataBindingUtil;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;

import com.shan.mypubliclibrary.R;
import com.shan.mypubliclibrary.databinding.MainTabItemLayoutBinding;
import com.shan.mypubliclibrary.listener.TitleBarListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Tab管理
 * Created by 陈俊山 on 2016/8/31.
 */

public class TabManager {
    private FragmentTabHost mTabHost;
    private FragmentActivity activity;
    private Class fragments[];
    private int images[];
    private String texts[];
    private TitleBarListener mTitleBarListener;

    public TabManager(FragmentActivity activity, FragmentTabHost mTabHost, Class fragments[], int images[], String texts[]) {
        this.activity = activity;
        this.fragments = fragments;
        this.images = images;
        this.texts = texts;
        this.mTabHost = mTabHost;//
        this.mTitleBarListener = (TitleBarListener) activity;
    }

    /**
     * @param fl_content 内容区域的id（例如：R.id.fl_content）
     */
    public void initTab(int fl_content) {
        // 实例化TabHost对象，得到TabHost
        //mTabHost = (FragmentTabHost) activity.findViewById(android.R.id.tabhost);
        mTabHost.setup(activity, activity.getSupportFragmentManager(), fl_content);
        // 得到fragment的个数
        int count = fragments.length;

        for (int i = 0; i < count; i++) {
            // 为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(texts[i]).setIndicator(getTabItemView(i));
            // 将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragments[i], null);
            // 设置Tab按钮的背景
            //mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.main_tab_item_bg);
            mTabHost.getTabWidget().setDividerDrawable(R.color.white);
        }
        mTabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                for (int i = 0; i < texts.length; i++) {
                    if (texts[i].equals(s)) {
                        mTitleBarListener.setTitleBarTitle(i);
                        list.get(i).checkBox.setChecked(true);
                        list.get(i).textview.setTextColor(activity.getResources().getColor(R.color.color_0072fd));
                    } else {
                        list.get(i).checkBox.setChecked(false);
                        list.get(i).textview.setTextColor(activity.getResources().getColor(R.color.light_black));
                    }
                }
            }
        });
    }

    private List<MainTabItemLayoutBinding> list = new ArrayList<>();

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int i) {
        MainTabItemLayoutBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), R.layout.main_tab_item_layout, null, false);
        list.add(binding);
        binding.checkBox.setBackgroundResource(images[i]);
        binding.textview.setText(texts[i]);
        if (i == 0) {
            binding.checkBox.setChecked(true);
            binding.textview.setTextColor(activity.getResources().getColor(R.color.color_0072fd));
        }
        return binding.getRoot();
    }
}

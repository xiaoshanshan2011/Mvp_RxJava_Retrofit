package com.shan.mvp_rxjava_retrofit.tab;

import com.shan.mvp_rxjava_retrofit.R;

/**
 * Tab常量值
 * Created by 陈俊山 on 2016/8/31.
 */

public class TabConstant {
    public static Class MAIN_FRAGMENT[] = {AFragment.class, BFragment.class, CFragment.class, DFragment.class};

    public static int MAIN_IAMGEVIEW[] = {R.drawable.main_tab1_selector, R.drawable.main_tab2_selector, R.drawable.main_tab3_selector, R.drawable.main_tab4_selector};

    public static String MAIN_TEXTVIEW[] = {"首页", "周边", "消息", "个人"};
}

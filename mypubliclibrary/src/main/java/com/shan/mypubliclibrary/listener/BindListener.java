package com.shan.mypubliclibrary.listener;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * 绑定Activity,Frgment Layout
 * Created by 陈俊山 on 2016/8/30.
 */

public interface BindListener {
    /*绑定布局*/
    int bindLayout();

    /*绑定Item布局*/
    int bindItemLayout();

    /*初始化操作*/
    void initOnCreate(@Nullable Bundle savedInstanceState);

    /*初始化TitleBar*/
    void initTitleBar();

    /*设置TitleBar状态*/
    void setTitleBarVisibility(int visibility);

    /*TitleBar右边点击事件*/
    void onClickLeft(View view);

    /*TitleBar左边点击事件*/
    void onClickRight(View view);

    /*关闭刷新*/
    void closeRefresh();

}

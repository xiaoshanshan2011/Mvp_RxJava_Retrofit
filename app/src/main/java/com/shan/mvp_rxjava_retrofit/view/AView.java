package com.shan.mvp_rxjava_retrofit.view;

import com.shan.mvp_rxjava_retrofit.bean.MovieBean;

import java.util.List;

/**
 * Created by root on 2016/11/22
 */

public interface AView {
    void onSuccess(MovieBean movieBean);

    void onFailure(Throwable e);

    void loadViewPager();

    void initType();

    void commodity(List<MovieBean.ShowapiResBodyBean.DatalistBean> datalist);
}
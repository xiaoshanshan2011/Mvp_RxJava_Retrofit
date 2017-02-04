package com.shan.mvp_rxjava_retrofit.presenter;


import android.content.Context;

import com.shan.mvp_rxjava_retrofit.bean.MovieBean;
import com.shan.mvp_rxjava_retrofit.net.HttpRequestBuilder;
import com.shan.mvp_rxjava_retrofit.net.SubscriberCallBack;
import com.shan.mvp_rxjava_retrofit.view.AView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 2016/11/22
 */

public class APresenterImpl extends PresenterImpl implements APresenter {
    private AView aView;
    private Context context;

    public APresenterImpl(AView aView, Context context) {
        this.aView = aView;
        this.context = context;
    }


    @Override
    public void getMovieData() {
        Map<String, String> map = new HashMap<>();
        map.put("showapi_appid", "4670");
        map.put("showapi_timestamp", "20170119182007");
        map.put("showapi_sign", "e708f42df8d757fc54c55e1f411c1fa7");
        SubscriberCallBack<MovieBean> subscriber = new SubscriberCallBack<MovieBean>(context, this) {
            @Override
            protected void onSuccess(MovieBean movieBean) {
                aView.loadHeadView("http://img.pconline.com.cn/images/upload/upc/tx/photoblog/1410/21/c4/39944979_39944979_1413878416125_mthumb.jpg");
                aView.onSuccess(movieBean);
            }

            @Override
            protected void onFailure(Throwable e) {
                aView.onFailure(e);
            }
        };
        startRequest(HttpRequestBuilder.httpService.movie(map), subscriber);
    }
}
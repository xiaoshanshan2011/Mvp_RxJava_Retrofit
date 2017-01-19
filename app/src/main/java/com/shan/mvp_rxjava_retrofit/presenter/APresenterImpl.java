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
        map.put("showapi_timestamp", "20170111123341");
        map.put("showapi_sign", "eb2cb15495e1b90099c11b3e53bcbb02");
        SubscriberCallBack<MovieBean> subscriber = new SubscriberCallBack<MovieBean>(context, this) {
            @Override
            protected void onSuccess(MovieBean movieBean) {
                aView.loadHeadView("http://s2.doyo.cn/img/53/84/20049e9e78eb500000ab.jpg");
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
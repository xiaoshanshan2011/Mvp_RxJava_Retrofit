package com.shan.mvp_rxjava_retrofit.net;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chenjunshan on 2016/8/19.
 */

public class HttpRequestBuilder {
    public static final String BASE_URL = "https://route.showapi.com/";
    private static final int DEFAULT_TIMEOUT = 5;//默认5秒超时
    private static final HttpRequestBuilder INSTANCE = new HttpRequestBuilder();

    private Retrofit retrofit;
    public static HttpService httpService;

    private HttpRequestBuilder() {
        retrofit = new Retrofit.Builder()
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        httpService = retrofit.create(HttpService.class);
    }

    private static OkHttpClient getOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HttpInterceptor())
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request()
                                .newBuilder()
                                .addHeader("PRAGMA-APPID", "12345678")
                                .addHeader("User-Agent", "xiaoshanshan")
                                .build();
                        return chain.proceed(request);
                    }
                });
        return builder.build();
    }

    public static HttpRequestBuilder getInstance() {
        return INSTANCE;
    }

    public <T> Subscription execute(Observable observable, Subscriber<T> subscriber) {
        return observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public <T> Subscription execute(Observable observable, Subscriber<T> subscriber, Func1 func1) {
        return observable.map(func1)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}

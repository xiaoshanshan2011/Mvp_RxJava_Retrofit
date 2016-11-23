package com.shan.mvp_rxjava_retrofit.net;

import com.shan.mvp_rxjava_retrofit.bean.MovieBean;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by chenjunshan on 2016/8/18.
 */

public interface HttpService {
    //电影排行
    @FormUrlEncoded
    @POST("578-6")
    Observable<MovieBean> movie(@FieldMap Map<String, String> params);

    //电影排行
    @GET("578-6")
    Observable<MovieBean> movie(@Query("showapi_appid") String showapi_appid, @Query("showapi_timestamp") String showapi_timestamp, @Query("showapi_sign") String showapi_sign);

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url);
}

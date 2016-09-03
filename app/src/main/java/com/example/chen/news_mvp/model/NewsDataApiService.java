package com.example.chen.news_mvp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cdc on 16-9-3.
 */
public interface NewsDataApiService {
    @GET("news/{type}/page/{pageIndex}")
    Call<NewsBean> getNewsData(@Path("type")String type, @Path("pageIndex")String pageIndex);
}

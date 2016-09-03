package com.example.chen.news_mvp.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by cdc on 16-9-4.
 */
public interface NewsContentDataApiService {
    @GET("{index}")
    Call<NewsContentBean> getNewsContent(@Path("index")String index);
}

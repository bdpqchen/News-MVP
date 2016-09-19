package com.example.chen.news_mvp.model;

import android.util.Log;

import com.example.chen.news_mvp.presenter.ConnectNetwork;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cdc on 16-9-3.
 */
public class Load  {

    public void loadNewsData(final ConnectNetwork connectNetwork, String type, String page){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://open.twtstudio.com/api/v1/")
                .build();

        ApiService newsDataApiService = retrofit.create(ApiService.class);
        Call<NewsBean> call = newsDataApiService.getNewsData(type, page);
        call.enqueue(new Callback<NewsBean>() {
            @Override
            public void onResponse(Call<NewsBean> call, Response<NewsBean> response) {
                if(response.isSuccessful()){
                    NewsBean newsBean = response.body();
                    connectNetwork.getNewsData(newsBean);
                }else{
                    connectNetwork.onDataError();
                }
            }

            @Override
            public void onFailure(Call<NewsBean> call, Throwable t) {
                connectNetwork.onLoadNewsError();
            }
        });
    }

    public void loadNewsContent(final ConnectNetwork connectNetwork, String index){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://open.twtstudio.com/api/v1/news/")
                .build();

        ApiService newsContentDataApiService = retrofit.create(ApiService.class);
        Call<NewsContentBean> call = newsContentDataApiService.getNewsContent(index);
        call.enqueue(new Callback<NewsContentBean>() {
            @Override
            public void onResponse(Call<NewsContentBean> call, Response<NewsContentBean> response) {
                if(response.isSuccessful()){
                    NewsContentBean newsContentBean = response.body();
                    connectNetwork.getNewsContentData(newsContentBean);
                }else{
                    connectNetwork.onDataError();
                }
            }

            @Override
            public void onFailure(Call<NewsContentBean> call, Throwable t) {
                connectNetwork.onLoadNewsError();
            }
        });
    }
}

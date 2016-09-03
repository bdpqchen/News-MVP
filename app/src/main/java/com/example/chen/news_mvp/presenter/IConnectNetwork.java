package com.example.chen.news_mvp.presenter;

import com.example.chen.news_mvp.model.NewsBean;
import com.example.chen.news_mvp.model.NewsContentBean;

/**
 * Created by cdc on 16-9-3.
 */
public interface IConnectNetwork {

    public void getNewsData(NewsBean newsBean);

    public void getNewsContentData(NewsContentBean newsContentBean);

    void onLoadNewsError();

    void onLoadContentError();

    void onDataError();

}

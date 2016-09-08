package com.example.chen.news_mvp.view;

import com.example.chen.news_mvp.model.NewsContentBean;

/**
 * Created by cdc on 16-9-4.
 */
public interface INewsContent {
    public void getContent(NewsContentBean.Data dataBean);
    public void onError();

}

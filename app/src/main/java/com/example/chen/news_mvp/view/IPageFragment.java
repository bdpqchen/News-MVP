package com.example.chen.news_mvp.view;

import com.example.chen.news_mvp.model.NewsBean;

import java.util.List;

/**
 * Created by cdc on 16-9-3.
 */
public interface IPageFragment {
//    public void onRefresh();
    public void onLoading(List<NewsBean.DataBean>dataBeanList);
    public void onDataError();
    public void onError();



}

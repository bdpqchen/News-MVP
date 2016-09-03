package com.example.chen.news_mvp.presenter;

import android.util.Log;

import com.example.chen.news_mvp.model.Load;
import com.example.chen.news_mvp.model.NewsBean;
import com.example.chen.news_mvp.model.NewsContentBean;
import com.example.chen.news_mvp.view.INewsContent;
import com.example.chen.news_mvp.view.IPageFragment;
import com.example.chen.news_mvp.view.NewsContentActivity;
import com.example.chen.news_mvp.view.PageFragment;

import java.util.List;

/**
 * Created by cdc on 16-9-3.
 */
public class ConnectNetwork implements IConnectNetwork {

    private Load load = new Load();
    private IPageFragment iPageFragment;
    private INewsContent iNewsContent;
    public void toLoadNewsData(IPageFragment pageFragment, String type, String page){
        iPageFragment = pageFragment;
        load.loadNewsData(ConnectNetwork.this, type, page);
    }

    public void toLoadContentData(INewsContent newsContent, String index){
        iNewsContent = newsContent;
        load.loadNewsContent(this, index);
    }

    @Override
    public void getNewsData(NewsBean newsBean){
        iPageFragment.onLoading(newsBean.getData());
    }

    @Override
    public void getNewsContentData(NewsContentBean newsContentBean) {
        iNewsContent.getContent(newsContentBean.getData());
    }

    @Override
    public void onLoadNewsError(){
        iPageFragment.onError();
    }

    @Override
    public void onLoadContentError(){

    }

    @Override
    public void onDataError(){
        iPageFragment.onDataError();
    }


}

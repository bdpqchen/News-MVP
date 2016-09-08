package com.example.chen.news_mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.news_mvp.R;
import com.example.chen.news_mvp.model.NewsContentBean;
import com.example.chen.news_mvp.presenter.ConnectNetwork;

/**
 * Created by cdc on 16-9-3.
 */
public class NewsContentActivity extends AppCompatActivity implements INewsContent{

    private WebView webView;
    private Toolbar toolbar;
    private TextView gonggao, shengao, sheying, subject_textView, newscome;
    private TextView txt_back;
    private Bundle bundle;
    private ConnectNetwork connectNetwork = new ConnectNetwork();

    @Override
    protected void onCreate(Bundle savedinstanceState){
        super.onCreate(savedinstanceState);
        setContentView(R.layout.news_content_activity);

        Intent intent = getIntent();
        bundle = intent.getExtras();

        toolbar = (Toolbar) findViewById(R.id.toolBar);
        gonggao = (TextView) findViewById(R.id.gonggao);
        shengao = (TextView) findViewById(R.id.shengao);
        sheying = (TextView) findViewById(R.id.sheying);
        newscome = (TextView) findViewById(R.id.newscome_content);
        subject_textView = (TextView) findViewById(R.id.subject_content);
        subject_textView.setText(bundle.getString("subject"));
        webView = (WebView) findViewById(R.id.webview);
        txt_back = (TextView) findViewById(R.id.txt_back);
        //返回按钮事件
        txt_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        connectNetwork.toLoadContentData(this, bundle.getString("index"));
    }

    @Override
    public void getContent(NewsContentBean.Data dataBean) {
        webView.loadData(dataBean.getContent(), "text/html;charset=utf-8", null);
        newscome.setText(getString(R.string.newscome) + dataBean.getNewscome());
        if(!dataBean.getGonggao().isEmpty()){
            gonggao.setText(getString(R.string.gonggao) + dataBean.getGonggao());
        }
        if(!dataBean.getShengao().isEmpty()){
            shengao.setText(getString(R.string.shengao) + dataBean.getShengao());
        }
        if(!dataBean.getSheying().isEmpty()){
            sheying.setText(getString(R.string.sheying) + dataBean.getSheying());
        }
    }

    @Override
    public void onError() {
        Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show();
    }
}

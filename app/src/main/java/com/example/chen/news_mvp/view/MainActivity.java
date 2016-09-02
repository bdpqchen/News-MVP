package com.example.chen.news_mvp.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Window;
import android.widget.TableLayout;

import com.example.chen.news_mvp.R;

/**
 * Created by chen on 16-9-1.
 */
public class MainActivity extends AppCompatActivity {

    Toolbar mToolbar;
    TableLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        tabLayout = (TableLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);

        setSupportActionBar(mToolbar);






    }

}

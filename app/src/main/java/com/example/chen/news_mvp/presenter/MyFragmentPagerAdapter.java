package com.example.chen.news_mvp.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.chen.news_mvp.view.PageFragment;

/**
 * Created by cdc on 16-9-2.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    private final Context context;
    final int titleCount = 5;
    private final String tabTitles[]=new String[]{"天大要闻","校园公告","社团风采","院系动态","视点观察"};

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.tabIndex(position + 1);
    }

    @Override
    public int getCount() {
        return titleCount;
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }
}

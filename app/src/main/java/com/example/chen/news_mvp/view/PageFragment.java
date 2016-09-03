package com.example.chen.news_mvp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chen.news_mvp.R;
import com.example.chen.news_mvp.model.NewsBean;
import com.example.chen.news_mvp.presenter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdc on 16-9-3.
 */
public class PageFragment extends Fragment {

    public static final String PAGE_INDEX = "index";
    private int type;
    private TextView errorText;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<NewsBean.DataBean> dataBeanList;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;

    public static PageFragment tabIndex(int page){
        Bundle args = new Bundle();
        args.putInt(PAGE_INDEX,page);
        PageFragment pageFragment = new PageFragment();
        pageFragment.setArguments(args);
        return pageFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        type = getArguments().getInt(PAGE_INDEX);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.page_fragment, container, false);
        errorText = (TextView) view.findViewById(R.id.error_text);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycle_view);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setColorSchemeColors(Color.BLUE,Color.GREEN, Color.YELLOW, Color.RED);
        dataBeanList = new ArrayList<>();
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(getContext(), dataBeanList);

        return view;

    }


}

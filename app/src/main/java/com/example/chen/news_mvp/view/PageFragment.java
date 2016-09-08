package com.example.chen.news_mvp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chen.news_mvp.R;
import com.example.chen.news_mvp.model.NewsBean;
import com.example.chen.news_mvp.presenter.ConnectNetwork;
import com.example.chen.news_mvp.presenter.MyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cdc on 16-9-3.
 */
public class PageFragment extends Fragment implements IPageFragment{

    public static final String PAGE_INDEX = "index";
    private int type;
    private TextView errorText;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<NewsBean.DataBean> dataBeanList;
    private MyRecyclerViewAdapter myRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int page;
    private boolean loading = false;
    private ConnectNetwork connectNetwork = new ConnectNetwork();

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
        linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myRecyclerViewAdapter);
        page = 1;
        if(!loading){
            loading = true;
            connectNetwork.toLoadNewsData(PageFragment.this, String.valueOf(type), String.valueOf(page));
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!loading){
                    loading = true;
                    dataBeanList.removeAll(dataBeanList);
                    page = 1;
                    connectNetwork.toLoadNewsData(PageFragment.this, type + "", page + "");
                }
            }
        });

        //上拉加载更多
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalcount = linearLayoutManager.getItemCount();
                int lastcount = linearLayoutManager.findLastVisibleItemPosition();
                if(lastcount + 1 >= totalcount && dy>0 && !loading){
                    page++;
                    loading =true;
                    connectNetwork.toLoadNewsData(PageFragment.this, type + "", page + "");
                }
            }
        });
        return view;

    }

    private void onErrorBehavior(){
        loading = false;
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.INVISIBLE);
    }

    private void onOkBehavior(){
        loading = false;
        swipeRefreshLayout.setRefreshing(false);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onLoading(List<NewsBean.DataBean> dataBeanList) {
        this.dataBeanList.addAll(dataBeanList);
        if(!dataBeanList.isEmpty()) {
            myRecyclerViewAdapter.notifyDataSetChanged();
            onOkBehavior();
        }else{
            Toast.makeText(getActivity(), getString(R.string.error_text),Toast.LENGTH_SHORT).show();
            onErrorBehavior();
        }
    }

    @Override
    public void onDataError() {
        onErrorBehavior();
        errorText.setVisibility(View.VISIBLE);
//        Toast.makeText(getActivity(),getString(R.string.network_error),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError() {
        onErrorBehavior();
        Toast.makeText(getActivity(),getString(R.string.network_error),Toast.LENGTH_SHORT).show();
    }


}

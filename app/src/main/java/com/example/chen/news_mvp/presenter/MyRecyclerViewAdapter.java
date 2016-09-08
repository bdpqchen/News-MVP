package com.example.chen.news_mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.chen.news_mvp.R;
import com.example.chen.news_mvp.model.NewsBean;
import com.example.chen.news_mvp.view.NewsContentActivity;

import java.util.List;

/**
 * Created by cdc on 16-9-3.
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final Context context;
//    private final LayoutInflater inflater;
    private List<NewsBean.DataBean> dataBeanList;
    private final int TYPE_FOOTER = 0;
    private final int TYPE_NAOMAL = 1;

    public MyRecyclerViewAdapter(Context context, List<NewsBean.DataBean>dataBeanList){
        this.context = context;
        this.dataBeanList = dataBeanList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_NAOMAL){
            return new NormalViewHolder(LayoutInflater.from(context).inflate(R.layout.item_normal, parent, false));
        }else if (viewType == TYPE_FOOTER){
            return new FooterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_footer, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        final int pos = position;
        System.out.println(dataBeanList.get(position).getSubject());
        if (viewHolder instanceof NormalViewHolder) {
            NormalViewHolder holder = (NormalViewHolder) viewHolder;
            final String subject = dataBeanList.get(position).getSubject();
            holder.tv.setText(subject);
            holder.read.setText(dataBeanList.get(position).getVisitcount() + context.getString(R.string.visitcount));
            holder.commented.setText(dataBeanList.get(position).getComments() + context.getString(R.string.comments));
            if (!dataBeanList.get(position).getPic().equals("")) {
                Glide.with(context).load(dataBeanList.get(position).getPic()).into(holder.imageView);
            } else {
                //无图重新定义ImageView为0
                RelativeLayout.LayoutParams relativeParams = (RelativeLayout.LayoutParams) holder.imageView.getLayoutParams();
                relativeParams.width = 0;
                holder.imageView.setLayoutParams(relativeParams);
            }
            //设置点击事件
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, NewsContentActivity.class);
                    Bundle intentBundle = new Bundle();
                    intentBundle.putString("index", String.valueOf(dataBeanList.get(pos).getIndex()));
                    intentBundle.putString("subject", subject);
                    intent.putExtras(intentBundle);
                    context.startActivity(intent);
                }
            });

            //长按事件
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataBeanList.size();
    }

    @Override
    public int getItemViewType(int position){
        if(position + 1 < dataBeanList.size()){
            return TYPE_NAOMAL;
        }else{
            return TYPE_FOOTER;
        }
    }

    class NormalViewHolder extends RecyclerView.ViewHolder {
        TextView tv, read, commented;
        ImageView imageView;

        public NormalViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.item_title);
            read = (TextView) itemView.findViewById(R.id.news_read);
            commented = (TextView) itemView.findViewById(R.id.news_commented);
            imageView = (ImageView) itemView.findViewById(R.id.pic);
        }


    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}

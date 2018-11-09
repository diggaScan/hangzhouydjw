package com.sunland.hangzhounews.config;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunland.hangzhounews.R;
import com.sunland.hangzhounews.bean.GeneralNewsInfo;

import java.util.List;

public class Rv_NewsList_Adapter extends RecyclerView.Adapter<Rv_NewsList_Adapter.MyViewHolder> {

    private Context context;
    private List<GeneralNewsInfo> dataSet;
    private LayoutInflater inflater;

    public Rv_NewsList_Adapter(Context context, List<GeneralNewsInfo> dataSet) {
        super();
        this.context = context;
        this.dataSet = dataSet;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public Rv_NewsList_Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.rv_news_list_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Rv_NewsList_Adapter.MyViewHolder myViewHolder, int i) {
        GeneralNewsInfo info = dataSet.get(i);
        myViewHolder.tv_title.setText(info.getTitle());
        myViewHolder.tv_add_time.setText(info.getAddTime());

    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title;
        TextView tv_add_time;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.news_title);
            tv_add_time = itemView.findViewById(R.id.addTime);
        }
    }
}

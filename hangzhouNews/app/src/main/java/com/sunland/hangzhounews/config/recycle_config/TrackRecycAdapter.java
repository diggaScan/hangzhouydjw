package com.sunland.hangzhounews.config.recycle_config;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.sunland.hangzhounews.R;

import java.util.List;

public class TrackRecycAdapter extends RecyclerView.Adapter<TrackRecycAdapter.MyViewHolder> {

    private Context mContext;
    private List<String> titles;
    private List<String> urls;

    private List<String> dates;
    private OnItemSelectedListener onItemSelectedListener;
    public TrackRecycAdapter(Context context, List<String> titles, List<String> urls){
        this.mContext=context;
        this.titles=titles;
        this.urls=urls;
    }
    public TrackRecycAdapter(Context context, List<String> titles, List<String> urls, List<String> dates){
        this.mContext=context;
        this.titles=titles;
        this.urls=urls;
        this.dates=dates;
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.track_recycle_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,  int position) {
        final int item_position=position;
        holder.title.setText(titles.get(position));
        holder.item_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemSelectedListener!=null){
                    onItemSelectedListener.onClick(item_position);
                }
            }
        });
        if(dates!=null){
            holder.tv_date.setVisibility(View.VISIBLE);
            holder.tv_date.setText(dates.get(position));
        }
        holder.right_image.setVisibility(View.VISIBLE);
        int num=(position+1)%5;
        switch (num){
            case 1:
//                holder.right_image.setImageResource(R.drawable.news1);
                break;
            case 2:
//                holder.right_image.setImageResource(R.drawable.news2);
                break;
            case 3:
//                holder.right_image.setImageResource(R.drawable.news4);
                break;
            case 4:
//                holder.right_image.setImageResource(R.drawable.news5);
                break;
            case 5:
//                holder.right_image.setImageResource(R.drawable.new3);
                break;
        }


    }
    public void setOnItemSelectedItem(OnItemSelectedListener l){
        this.onItemSelectedListener=l;
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView title;
        private RelativeLayout item_container;
        private TextView tv_date;
        private ImageView right_image;
        public MyViewHolder(View view){
            super(view);
            item_container=view.findViewById(R.id.item_container);
            title=view.findViewById(R.id.title);
            tv_date=view.findViewById(R.id.source);
            right_image=view.findViewById(R.id.right_image);
        }
    }



}

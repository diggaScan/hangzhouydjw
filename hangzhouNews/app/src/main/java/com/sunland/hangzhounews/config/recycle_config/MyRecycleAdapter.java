package com.sunland.hangzhounews.config.recycle_config;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.sunland.hangzhounews.utils.CharacterStyleHelper;
import com.sunland.hangzhounews.R;
import com.sunland.hangzhounews.utils.WindowInfoUtils;

import java.util.List;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {

    private final int TEXT_NEWS=0;
    private final int ONE_IMAGE=1;
    private final int IMAGE_GALLERY=2;
    private final int VIDEO_CLIP=3;

    private OnItemSelectedListener onItemSelectedListener;
    private List<String> news_titles;
    private List<String> news_source;
    private List<List<Integer>> news_images;
    private List<Integer> isVideos;

    private Context mContext;

    public MyRecycleAdapter(Context context, List<String> titles, List<String> source,
                            List<List<Integer>> images, List<Integer> isVideos) {
        super();
        mContext=context;
        news_titles=titles;
        news_source=source;
        news_images=images;
        this.isVideos=isVideos;
    }

    public void setOnItemSelectedItem(OnItemSelectedListener l){
        this.onItemSelectedListener=l;
    }
    @Override
    public int getItemViewType(int position) {
        if (isVideos.get(position)!=0) {
            return VIDEO_CLIP;
        }else{
            if (news_images.get(position) == null) {
                return TEXT_NEWS;
            }
            int images_num=news_images.get(position).size();
            if(images_num==0){
                return TEXT_NEWS;
            }else if(images_num<3&&images_num>=1){
                return ONE_IMAGE;
            }else {
                return IMAGE_GALLERY;
            }
        }

    }

    @Override
    public MyRecycleAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mContext).inflate(R.layout.recycle_view_item,parent,false);
        return new MyViewHolder(view,viewType);

    }

    @Override
    public void onBindViewHolder(MyRecycleAdapter.MyViewHolder holder, int position) {
        int viewType=holder.getViewType();
        final int item_position=position;
        int text_size= WindowInfoUtils.dp2px(mContext,20);
        CharacterStyleHelper.setNewsTitleText( mContext,holder.tv_title,news_titles.get(position),text_size,R.color.news_title_color,R.color.selector_dt);
        holder.tv_source.setText(news_source.get(position));
        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemSelectedListener!=null){
                    onItemSelectedListener.onClick(item_position);
                }
            }
        });
        switch (viewType){
            case TEXT_NEWS:
                break;
            case ONE_IMAGE:
                holder.left_image.setVisibility(View.VISIBLE);
                holder.left_image.setImageResource(news_images.get(position).get(0));
                break;
            case IMAGE_GALLERY:
                holder.images.setVisibility(View.VISIBLE);
                holder.image_one.setImageResource(news_images.get(position).get(0));
                holder.image_two.setImageResource(news_images.get(position).get(1));
                holder.image_three.setImageResource(news_images.get(position).get(2));
                break;
            case VIDEO_CLIP:
                holder.video_clip.setVisibility(View.VISIBLE);
                holder.video_clip.setImageResource(isVideos.get(position));
                break;
        }

    }

    @Override
    public int getItemCount() {
        return news_titles.size();
    }

    public  static class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView left_image;
        private TextView tv_title;
        private TextView tv_source;
        private LinearLayout images;
        private ImageView image_one;
        private ImageView image_two;
        private ImageView image_three;
        private RelativeLayout container;
        private ImageView video_clip;

        private int viewType;
        public MyViewHolder(View view, int viewType){
            super(view);
            container=view.findViewById(R.id.item_container);
            left_image=view.findViewById(R.id.right_image);
            tv_title=view.findViewById(R.id.title);
            tv_source=view.findViewById(R.id.source);
            images=view.findViewById(R.id.images);
            image_one=view.findViewById(R.id.image_one);
            image_two=view.findViewById(R.id.image_two);
            image_three=view.findViewById(R.id.image_three);
            video_clip=view.findViewById(R.id.video_clip);
            this.viewType=viewType;
        }

        public int getViewType() {
            return viewType;
        }
    }


}

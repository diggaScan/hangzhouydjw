package com.sunland.lawreference.recyclerViewConfig;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunland.lawreference.R;

import java.util.List;

public class RvZfbzAdapter extends RecyclerView.Adapter<RvZfbzAdapter.MyViewHolder> {

    private Context context;
    private List<String> titles;
    private List<Integer> drawbles;
    private LayoutInflater inflater;
    private OnRvItemClickedListener listener;

    public RvZfbzAdapter(Context context, List<String> titles, List<Integer> drawables) {
        super();
        this.context = context;
        this.titles = titles;
        this.drawbles = drawables;
        inflater = LayoutInflater.from(context);
    }

    public void setListener(OnRvItemClickedListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public RvZfbzAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.rv_zfbz_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvZfbzAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(drawbles.get(i)));
        myViewHolder.textView.setText(titles.get(i));
        myViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null){
                    listener.onItemClicked(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
            imageView = itemView.findViewById(R.id.image);
            relativeLayout=itemView.findViewById(R.id.item_container);

        }
    }

}
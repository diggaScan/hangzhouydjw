package com.sunland.lawreference.recyclerViewConfig;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sunland.lawreference.R;
import com.sunland.lawreference.db.LawReferenceBean;

import java.text.SimpleDateFormat;
import java.util.List;

public class RvAcCollectAdapter extends RecyclerView.Adapter<RvAcCollectAdapter.MyViewholder> {
    private Context context;
    private List<LawReferenceBean> dataset;
    private LayoutInflater inflater;
    private OnRvItemClickedListener listener;
    private SimpleDateFormat format;
    public RvAcCollectAdapter(Context context, List<LawReferenceBean> dataset) {
        super();
        this.context = context;
        this.dataset = dataset;
        inflater = LayoutInflater.from(context);
        format=new SimpleDateFormat("yyyy-MM-dd HH:mm");
    }

    @NonNull
    @Override
    public RvAcCollectAdapter.MyViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.rv_ac_collect_item, viewGroup, false);
        return new MyViewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvAcCollectAdapter.MyViewholder myViewholder, final int i) {
        myViewholder.tv_timestamp.setText(format.format(dataset.get(i).timeStamp));
        myViewholder.tv_title.setText(dataset.get(i).title);
        myViewholder.rl_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener!=null)
                    listener.onItemClicked(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public void setListener(OnRvItemClickedListener listener) {
        this.listener = listener;
    }

    class MyViewholder extends RecyclerView.ViewHolder {

        public TextView tv_title;
        public TextView tv_timestamp;
        public RelativeLayout rl_container;

        public MyViewholder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.title);
            tv_timestamp = itemView.findViewById(R.id.timestamp);
            rl_container=itemView.findViewById(R.id.rl_container);
        }
    }
}

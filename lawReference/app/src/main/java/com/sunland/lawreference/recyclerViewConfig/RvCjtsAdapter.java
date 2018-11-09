package com.sunland.lawreference.recyclerViewConfig;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sunland.lawreference.R;
import com.sunland.lawreference.fragments.CJTSItem;

import java.util.List;

public class RvCjtsAdapter extends RecyclerView.Adapter<RvCjtsAdapter.MyViewHolder> {

    private Context context;
    private List<CJTSItem> list;
    private LayoutInflater inflater;
    private OnRvItemClickedListener listener;

    public RvCjtsAdapter(Context context, List<CJTSItem> list) {
        super();
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RvCjtsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.rv_cjts_item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RvCjtsAdapter.MyViewHolder myViewHolder, final int i) {
        myViewHolder.textView.setText(list.get(i).fileNameCN);
        myViewHolder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClicked(i);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setListener(OnRvItemClickedListener listener) {
        this.listener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.regulation);

        }
    }


}

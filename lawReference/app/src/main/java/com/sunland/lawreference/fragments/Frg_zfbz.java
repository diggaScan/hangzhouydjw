package com.sunland.lawreference.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sunland.lawreference.R;
import com.sunland.lawreference.activities.Ac_info_detail;
import com.sunland.lawreference.activities.Ac_main;
import com.sunland.lawreference.db.DataModel;
import com.sunland.lawreference.recyclerViewConfig.OnRvItemClickedListener;
import com.sunland.lawreference.recyclerViewConfig.RvZfbzAdapter;
import com.sunland.lawreference.recyclerViewConfig.Rv_Item_decoration;

import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Frg_zfbz extends Fragment {


    @BindView(R.id.recycler_view)
    public RecyclerView rv_zfbz;

    RvZfbzAdapter rvZfbzAdapter;
    public Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_cjts, container, false);
        ButterKnife.bind(this, view);
        initViews();
        return view;
    }

    public void initViews() {
        rvZfbzAdapter = new RvZfbzAdapter(context, Arrays.asList(DataModel.fileNameCN), Arrays.asList(DataModel.icon));
        LinearLayoutManager llm = new LinearLayoutManager(context);
        rv_zfbz.setLayoutManager(llm);
        rv_zfbz.setAdapter(rvZfbzAdapter);
        rv_zfbz.addItemDecoration(new Rv_Item_decoration(context));
        rvZfbzAdapter.setListener(new OnRvItemClickedListener() {
            @Override
            public void onItemClicked(int i) {
                Bundle data = new Bundle();
                data.putString("FILENAME", DataModel.fileNameEN[i]);
                data.putString("title", DataModel.fileNameCN[i]);
                ((Ac_main) context).hop2Activity(Ac_info_detail.class, data);
            }
        });
    }

}

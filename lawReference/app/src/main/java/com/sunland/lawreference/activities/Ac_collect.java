package com.sunland.lawreference.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;

import com.sunland.lawreference.R;
import com.sunland.lawreference.db.LawReferenceBean;
import com.sunland.lawreference.db.MdbOpenHelper;
import com.sunland.lawreference.recyclerViewConfig.OnRvItemClickedListener;
import com.sunland.lawreference.recyclerViewConfig.RvAcCollectAdapter;
import com.sunland.lawreference.recyclerViewConfig.Rv_Item_decoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Ac_collect extends Ac_base {
    @BindView(R.id.rv_collect)
    public RecyclerView rv_collect;
    @BindView(R.id.noCollect)
    public RelativeLayout rl_noCollect;

    private Thread thread;
    private List<LawReferenceBean> law_refers;
    private RvAcCollectAdapter adapter;

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<LawReferenceBean> list = (List<LawReferenceBean>) msg.obj;
            if (list.isEmpty()) {
                rl_noCollect.setVisibility(View.VISIBLE);
                return;
            } else {
                rl_noCollect.setVisibility(View.GONE);
                law_refers.clear();
                law_refers.addAll(list);
                adapter.notifyDataSetChanged();
            }
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_collect);
        showNavIcon(true);
        setToolbarTitle("收藏");
        initRecyclerView();
        loadData();

    }

    private void initRecyclerView() {
        law_refers = new ArrayList<>();
        adapter = new RvAcCollectAdapter(this, law_refers);
        adapter.setListener(new OnRvItemClickedListener() {
            @Override
            public void onItemClicked(int i) {

                Bundle data = new Bundle();
                data.putString("FILENAME", law_refers.get(i).filename);
                data.putString("title", law_refers.get(i).title);
                hop2Activity(Ac_info_detail.class, data);
            }
        });
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv_collect.addItemDecoration(new Rv_Item_decoration(this));
        rv_collect.setLayoutManager(llm);
        rv_collect.setAdapter(adapter);
    }

    private void loadData() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                MdbOpenHelper.createDb(Ac_collect.this);
                List<LawReferenceBean> list = MdbOpenHelper.getDb().getLawRefDao().loadAllLawRefersDesc();
                Message message = myHandler.obtainMessage();
                message.obj = list;
                myHandler.sendMessage(message);
            }
        });
        thread.start();
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (thread != null && !thread.isInterrupted()) {
            thread.interrupt();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        loadData();
    }
}

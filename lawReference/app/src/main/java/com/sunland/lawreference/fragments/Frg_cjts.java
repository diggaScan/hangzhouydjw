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
import com.sunland.lawreference.recyclerViewConfig.RvCjtsAdapter;
import com.sunland.lawreference.recyclerViewConfig.Rv_Item_decoration;

import java.util.ArrayList;
import java.util.List;

public class Frg_cjts extends Fragment {

    private RecyclerView rv_list;
    private Context context;
    private CJTSItem root;
    private List<CJTSItem> cur_items;
    private RvCjtsAdapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_cjts, container, false);
        rv_list = view.findViewById(R.id.recycler_view);
        initDataset();
        initViews();
        return view;
    }

    private void initViews() {
        cur_items = new ArrayList<>();


        cur_items.addAll(root.subItems);

        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv_list.setLayoutManager(manager);
        rv_list.addItemDecoration(new Rv_Item_decoration(context));
        adapter = new RvCjtsAdapter(context, cur_items);
        rv_list.setAdapter(adapter);
        adapter.setListener(new OnRvItemClickedListener() {
            @Override
            public void onItemClicked(int i) {
                updateDataset(i);
            }
        });
    }

    private void initDataset() {

        root = new CJTSItem();
        List<CJTSItem> list_root = new ArrayList<>();

        CJTSItem one = new CJTSItem();
        CJTSItem two = new CJTSItem();
        CJTSItem three = new CJTSItem();


        one.fileNameCN = DataModel.level[0];
        List<CJTSItem> list_one = new ArrayList<>();
        for (int i = 0; i < DataModel.level_1.length; i++) {
            CJTSItem item = new CJTSItem();
            item.superItem = one;
            item.fileNameCN = DataModel.level_1[i];
            ArrayList<CJTSItem> list_1_1 = new ArrayList<>();
            switch (i) {
                case 0:
                    for (int j = 0; j < DataModel.level_1_1.length; j++) {
                        CJTSItem item1 = new CJTSItem();
                        item1.superItem = item;
                        item1.fileNameCN = DataModel.level_1_1[j];
                        item1.fileNameEN = DataModel.level_1_1_EN[j];
                        if (j == 0) {
                            List<CJTSItem> list1_1_1 = new ArrayList<>();
                            for (int k = 0; k < DataModel.level_1_1_1.length; k++) {
                                CJTSItem item1_1 = new CJTSItem();
                                item1_1.superItem = item1;
                                item1_1.fileNameCN = DataModel.level_1_1_1[k];
                                item1_1.fileNameEN = DataModel.level_1_1_1_EN[k];
                                list1_1_1.add(item1_1);
                            }
                            item1.subItems = list1_1_1;
                        }

                        list_1_1.add(item1);
                    }
                    break;
                case 1:
                    for (int j = 0; j < DataModel.level_1_2.length; j++) {
                        CJTSItem item1 = new CJTSItem();
                        item1.superItem = item;
                        item1.fileNameCN = DataModel.level_1_2[j];
                        item1.fileNameEN = DataModel.level_1_2_EN[j];
                        list_1_1.add(item1);
                    }
                    break;
                case 2:
                    for (int j = 0; j < DataModel.level_1_3.length; j++) {
                        CJTSItem item1 = new CJTSItem();
                        item1.superItem = item;
                        item1.fileNameCN = DataModel.level_1_3[j];
                        item1.fileNameEN = DataModel.level_1_3_EN[j];
                        list_1_1.add(item1);
                    }
                    break;

            }
            item.subItems = list_1_1;

            list_one.add(item);
        }
        one.subItems = list_one;
        one.superItem = root;

        two.fileNameCN = DataModel.level[1];
        two.superItem = root;
        List<CJTSItem> list_two = new ArrayList<>();
        for (int i = 0; i < DataModel.level_2.length; i++) {
            CJTSItem item = new CJTSItem();
            item.superItem = two;
            item.fileNameCN = DataModel.level_2[i];
            item.fileNameEN = DataModel.level_2_EN[i];
            list_two.add(item);
        }
        two.subItems = list_two;


        three.fileNameCN = DataModel.level[2];
        three.superItem = root;
        List<CJTSItem> list_three = new ArrayList<>();
        for (int i = 0; i < DataModel.level_3.length; i++) {
            CJTSItem item = new CJTSItem();
            item.superItem = three;
            item.fileNameCN = DataModel.level_3[i];
            item.fileNameEN = DataModel.level_3_EN[i];
            list_three.add(item);
        }

        three.subItems = list_three;

        list_root.add(one);
        list_root.add(two);
        list_root.add(three);
        root.subItems = list_root;

    }

    public void updateDataset(int i) {


        CJTSItem clicked_item = cur_items.get(i);
        if (clicked_item.subItems == null || clicked_item.subItems.size() == 0) {
            Bundle data = new Bundle();
            data.putString("FILENAME", "remindhtml/" + clicked_item.fileNameEN);
            data.putString("title", clicked_item.fileNameCN);
            ((Ac_main) context).hop2Activity(Ac_info_detail.class, data);
        } else {
            cur_items.clear();
            cur_items.addAll(clicked_item.subItems);
            adapter.notifyDataSetChanged();
        }
    }

    public boolean onBackPressed(Context context) {
        CJTSItem item = cur_items.get(0);
        if (item.superItem.superItem != null) {
            cur_items.clear();
            List<CJTSItem> list = item.superItem.superItem.subItems;
            cur_items.addAll(list);
            adapter.notifyDataSetChanged();
            return true;
        } else {
            return false;
        }
    }


}

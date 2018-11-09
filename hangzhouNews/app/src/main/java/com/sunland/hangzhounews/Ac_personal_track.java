package com.sunland.hangzhounews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;



import java.util.ArrayList;
import java.util.List;

public class Ac_personal_track extends TabBaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("浏览记录");
        initWidget();
    }

    private void initWidget() {
        Frg_news_list testFragment1 = new Frg_news_list();
        Frg_news_list testFragment2 = new Frg_news_list();
        Bundle bundle1=new Bundle();
        Bundle bundle2=new Bundle();
//        bundle1.putInt(Frg_news_list.FLAG,Frg_news_list.SHOW_COLLECTION);
//        bundle2.putInt(Frg_news_list.FLAG,Frg_news_list.SHOW_HISTORY);
        testFragment1.setArguments(bundle1);
        testFragment2.setArguments(bundle2);
        List<String> name = new ArrayList<>();
        name.add("收藏");
        name.add("历史");
        List<Fragment> content = new ArrayList<>();
        content.add(testFragment1);
        content.add(testFragment2);
        addTabName(name).addTabContent(content).setAdapter().setUpConnection();
    }

    public static void startActivity(Activity activity){
        Intent intent=new Intent(activity,Ac_personal_track.class);
        activity.startActivity(intent);
    }


}

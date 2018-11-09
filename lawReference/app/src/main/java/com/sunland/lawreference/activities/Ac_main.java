package com.sunland.lawreference.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sunland.lawreference.FrgVpAdapter;
import com.sunland.lawreference.R;
import com.sunland.lawreference.fragments.Frg_cjts;
import com.sunland.lawreference.fragments.Frg_zfbz;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class Ac_main extends Ac_base {

    @BindView(R.id.tabs)
    public TabLayout tl_tabs;
    @BindView(R.id.vp_list)
    public ViewPager vp_list;

    public List<Fragment> list;

    private int backPressed_times = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_main);
        setToolbarTitle("执法参考");
        showNavIcon(false);
        tl_tabs.setupWithViewPager(vp_list);
        initLawList();
    }

    private void initLawList() {
        list = new ArrayList<>();
        Frg_cjts frg_cjts = new Frg_cjts();
        Frg_zfbz frg_zfbz = new Frg_zfbz();
        list.add(frg_cjts);
        list.add(frg_zfbz);
        FrgVpAdapter adapter = new FrgVpAdapter(getSupportFragmentManager(), list);
        vp_list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.law_ref_collect:
                hop2Activity(Ac_collect.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Fragment cur_frg = list.get(vp_list.getCurrentItem());
        if (cur_frg instanceof Frg_cjts) {
            boolean canBack = ((Frg_cjts) cur_frg).onBackPressed(this);
            if (!canBack) {
                quitApp();
            }
        } else {
            quitApp();
        }
    }

    public void quitApp() {
        if (backPressed_times != 1) {
            backPressed_times++;
            Toast.makeText(this, "再按一次,退出应用", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressed_times--;
                }
            }, 2500);
        } else {
            finish();
        }
    }
}

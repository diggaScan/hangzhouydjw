package com.sunland.lawreference;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.sunland.lawreference.db.DataModel;

import java.util.List;

public class FrgVpAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public FrgVpAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return DataModel.law_category[position];
    }


}

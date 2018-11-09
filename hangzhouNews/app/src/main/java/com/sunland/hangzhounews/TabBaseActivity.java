package com.sunland.hangzhounews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

import butterknife.BindView;

public class TabBaseActivity extends Ac_base {

    @BindView(R.id.xfhl_tl) public TabLayout mTabLayout;
    @BindView(R.id.xfhl_vp) public ViewPager mViewPager;

    public List<String> mTabNames;
    public List<Fragment> mTabContent;
    private Fragment mCurrentPrimaryItem = null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public TabBaseActivity addTabName(List<String> names){
        this.mTabNames=names;
        return this;
    }

    public TabBaseActivity addTabContent(List<Fragment> fragments){
        this.mTabContent=fragments;
        return this;
    }
    public TabBaseActivity setAdapter(){
        ContentAdapter contentAdapter=new ContentAdapter(getSupportFragmentManager(),mTabContent,mTabNames);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(contentAdapter);
        return this;
    }

    public void setUpConnection(){
        mTabLayout.setupWithViewPager(mViewPager);
    }



    public class ContentAdapter extends FragmentPagerAdapter {

        private FragmentManager fm;
        private List<Fragment> dataSet;
        private List<String> tabNames;


        public ContentAdapter(FragmentManager fm, List<Fragment> list, List<String> tabNames){
            super(fm);
            this.fm=fm;
            this.dataSet=list;
            this.tabNames=tabNames;
        }

        @Override
        public Fragment getItem(int position) {
            return dataSet.get(position);
        }

        @Override
        public int getCount() {
            return tabNames.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabNames.get(position);
        }

        private String makeFragmentName(int viewId, long id) {
            return "android:switcher:" + viewId + ":" + id;
        }
    }
}

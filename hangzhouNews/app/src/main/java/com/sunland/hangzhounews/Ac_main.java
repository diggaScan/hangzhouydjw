package com.sunland.hangzhounews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sunland.hangzhounews.bean.BaseRequestBean;
import com.sunland.hangzhounews.bean.NewsCategory;
import com.sunland.hangzhounews.bean.NewsKindRequestBean;
import com.sunland.hangzhounews.bean.NewsKindResponseBean;
import com.sunland.hangzhounews.bean.TerritoryInfo;
import com.sunland.hangzhounews.bean.TerritoryRequestBean;
import com.sunland.hangzhounews.bean.TerritoryResponseBean;
import com.sunland.hangzhounews.config.ContentAdapter;
import com.sunlandgroup.Global;
import com.sunlandgroup.def.bean.result.ResultBase;
import com.sunlandgroup.network.OnRequestCallback;
import com.sunlandgroup.network.RequestManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class Ac_main extends Ac_base implements OnRequestCallback {

    @BindView(R.id.track_button)
    public Button btn_trak;
    @BindView(R.id.region_picker)
    public SpinButton sb_regions;
    @BindView(R.id.index_title)
    public TextView tv_title;
    @BindView(R.id.fragments_container)
    public ViewPager vp_frg_container;
    @BindView(R.id.tab_titles)
    public TabLayout tl_categories;

    private RequestManager mRequestManager;
    private String selected_dq_code;
    private String selected_category_code;
    private List<String> dq_codes = new ArrayList<>();
    private List<String> category_codes = new ArrayList<>();
    private List<String> category_names = new ArrayList<>();

    private ContentAdapter mContentAdapter;
    private List<String> mTabNames;
    private List<Fragment> mTabContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarLayout(R.layout.toolbar_layout);
        setContentLayout(R.layout.ac_main);
        mRequestManager = new RequestManager(this, this);
        queryYdjwData(DataModel.TERRITORY_LIST_REQNAME);
        initView();
    }

    public void queryYdjwData(String reqName) {
        mRequestManager.addRequest(Global.ip, Global.port, Global.postfix, reqName, assembleRequestObj(reqName), 15000);
        mRequestManager.postRequest();
    }

    private BaseRequestBean assembleRequestObj(String reqName) {
        switch (reqName) {
            case DataModel.TERRITORY_LIST_REQNAME:
                TerritoryRequestBean requestBean = new TerritoryRequestBean();
                assembleBaseInfo(requestBean);
                return requestBean;
            case DataModel.NEWS_CATEGORY_LIST_REQNAME:
                NewsKindRequestBean newsKindRequestBean = new NewsKindRequestBean();
                assembleBaseInfo(newsKindRequestBean);
                newsKindRequestBean.setDqid(selected_dq_code);
                return newsKindRequestBean;
        }
        return null;
    }

    private void assembleBaseInfo(BaseRequestBean requestBean) {
        requestBean.setYhdm("test");
        requestBean.setImei(Global.imei);
        requestBean.setImsi(Global.imsi1);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String pda_time = simpleDateFormat.format(date);
        requestBean.setPdaTime(pda_time);
    }

    private void initView() {
        tv_title.setText("公安资讯");
        sb_regions.setHeaderTitle("地区");
        sb_regions.setOnItemSelectedListener(new SpinButton.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int position) {
                selected_dq_code = dq_codes.get(position);
                queryYdjwData(DataModel.NEWS_CATEGORY_LIST_REQNAME);
            }
        });
    }

    public void initNewsList() {

    }

    @OnClick(R.id.track_button)
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.track_button:
                Ac_personal_track.startActivity(this);
        }
    }

    public RequestManager getmRequestManager() {
        return mRequestManager;
    }

    @Override
    public <T> void onRequestFinish(String reqId, String reqName, T bean) {
        switch (reqId) {
            case DataModel.TERRITORY_LIST_REQNAME:
                TerritoryResponseBean responseBean = (TerritoryResponseBean) bean;
                if (responseBean != null) {
                    if (!responseBean.getCode().equals("0")) {
                        List<TerritoryInfo> list = responseBean.getTerritoryInfo();
                        if (list == null || list.isEmpty()) {
                            Toast.makeText(this, "地址列表返回为空", Toast.LENGTH_SHORT).show();
                        } else {
                            List<String> dateSet = new ArrayList<>();
                            for (TerritoryInfo info : list) {
                                dateSet.add(info.getDqmc());
                                dq_codes.add(info.getDqid());
                            }
                            sb_regions.setDataSet(dateSet);
                            sb_regions.setSelection(0);

                            String default_de_id = list.get(0).getDqid();
                            if (default_de_id != null || !default_de_id.isEmpty()) {
                                selected_dq_code = default_de_id;
                                queryYdjwData(DataModel.NEWS_CATEGORY_LIST_REQNAME);//当获得地区id后立即访问新闻类别接口.
                            } else {
//                                Toast.makeText(this, "无法获取数据", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else {
                        Toast.makeText(this, "服务异常，无法获取数据", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                } else {
                    Toast.makeText(this, "数据接入错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;

            case DataModel.NEWS_CATEGORY_LIST_REQNAME:
                NewsKindResponseBean newsKindResponseBean = (NewsKindResponseBean) bean;
                if (newsKindResponseBean != null) {
                    if (!newsKindResponseBean.getCode().equals("0")) {
                        List<NewsCategory> list = newsKindResponseBean.getNewsCategory();
                        if (list == null || list.isEmpty()) {
                            Toast.makeText(this, "新闻类别列表返回为空", Toast.LENGTH_SHORT).show();
                        } else {
                            for (NewsCategory info : list) {
                                category_codes.add(info.getLbid());
                                category_names.add(info.getLbmc());
                            }
                            initNewsList();
                        }
                    } else {
                        Toast.makeText(this, "服务异常，无法获取数据", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "数据接入错误", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public <T extends ResultBase> Class<?> getBeanClass(String reqId, String reqName) {
        switch (reqName) {
            case DataModel.TERRITORY_LIST_REQNAME:
                return TerritoryResponseBean.class;
            case DataModel.NEWS_CATEGORY_LIST_REQNAME:
                return NewsKindResponseBean.class;
        }
        return null;
    }
}

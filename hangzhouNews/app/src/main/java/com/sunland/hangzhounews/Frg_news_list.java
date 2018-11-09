package com.sunland.hangzhounews;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sunland.hangzhounews.bean.GeneralNewsInfo;
import com.sunland.hangzhounews.bean.NewsListRequestBean;
import com.sunland.hangzhounews.bean.NewsListResponseBean;
import com.sunland.hangzhounews.config.Rv_Item_decoration;
import com.sunland.hangzhounews.config.Rv_NewsList_Adapter;
import com.sunlandgroup.Global;
import com.sunlandgroup.def.bean.result.ResultBase;
import com.sunlandgroup.network.OnRequestCallback;
import com.sunlandgroup.network.RequestManager;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Frg_news_list extends Fragment implements OnRequestCallback {

    public static final String FLAG = "Frg_news_list";

    @BindView(R.id.recycle)
    public RecyclerView rv_news_list;
    private RequestManager mRequestManager;
    private String dqid;
    private String lbid;
    private Context context;
    private Rv_NewsList_Adapter adapter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_news_list, container, false);
        ButterKnife.bind(this, view);
        mRequestManager = ((Ac_main) context).getmRequestManager();
        queryYdjwData();
        return view;
    }

    public void setQueryParams(String dqid, String lbid) {
        this.dqid = dqid;
        this.lbid = lbid;
    }

    private void queryYdjwData() {
        mRequestManager.addRequest(Global.ip, Global.port, Global.postfix, DataModel.NEWS_LIST_REQNAME
                , assembleRequestObj(DataModel.NEWS_LIST_REQNAME), 15000);
        mRequestManager.postRequest();
    }

    private void initNewsList(List<GeneralNewsInfo> list) {
        adapter = new Rv_NewsList_Adapter(context, list);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        rv_news_list.setAdapter(adapter);
        rv_news_list.setLayoutManager(manager);
        rv_news_list.addItemDecoration(new Rv_Item_decoration(context));

    }

    private NewsListRequestBean assembleRequestObj(String reqName) {
        NewsListRequestBean requestBean = new NewsListRequestBean();
        requestBean.setYhdm("test");
        requestBean.setImei(Global.imei);
        requestBean.setImsi(Global.imsi1);
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String pda_time = simpleDateFormat.format(date);
        requestBean.setPdaTime(pda_time);

        requestBean.setDqid(dqid);
        requestBean.setLbid(lbid);
        return requestBean;
    }

    @Override
    public <T> void onRequestFinish(String reqId, String reqName, T bean) {
        NewsListResponseBean responseBean = (NewsListResponseBean) bean;
        if (responseBean != null) {
            if (responseBean.getCode().equals("0")) {
                List<GeneralNewsInfo> list = responseBean.getGeneralNewsInfo();
                if (list == null || list.isEmpty()) {
                    Toast.makeText(context, "新闻列表返回为空", Toast.LENGTH_SHORT).show();
                } else {
                    initNewsList(list);
                }
            } else {
                Toast.makeText(context, "服务异常，无法获取数据", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(context, "数据接入错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public <T extends ResultBase> Class<?> getBeanClass(String reqId, String reqName) {
        return NewsListResponseBean.class;
    }
}

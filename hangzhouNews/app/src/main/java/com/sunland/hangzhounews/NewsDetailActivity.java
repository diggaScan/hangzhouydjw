package com.sunland.hangzhounews;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.sunland.hangzhounews.dbHelper.MyDatabase;
import com.sunland.hangzhounews.dbHelper.OpenDbHelper;
import com.sunland.hangzhounews.dbHelper.browser_history.History;
import com.sunland.hangzhounews.dbHelper.news_collection.News;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewsDetailActivity extends Ac_base {

    public static final String FLAG = "NewsDetailActivity";

    public boolean isCollected = false;

    @BindView(R.id.news_detail) public WebView wv_news;
    @BindView(R.id.collect_button)public ImageView btn_collect;
    @BindView(R.id.progress_bar)public ProgressBar loading_progress;
    @BindView(R.id.downLoad)public FloatingActionButton fab_download;

    private String news_title;
    private volatile String news_url;

    private String current_url;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent();
        initWidget();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra(FLAG);
            current_url = bundle.getString("url");
            isCollected(current_url);
        }
    }

    public void initWidget() {
        WebSettings webSettings = wv_news.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
//        webSettings.setJavaScriptEnabled(true);

        wv_news.clearCache(false);
        Log.d("news", "initWidget: "+current_url);
        wv_news.loadUrl(current_url);
        wv_news.setWebViewClient(new MyWebViewClient());
        wv_news.setWebChromeClient(new MyChromeClient());
    }






    @OnClick({R.id.home_image, R.id.collect_button})
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.home_image:
                onBackPressed();
                break;
            case R.id.collect_button:
                if (isCollected) {
                    btn_collect.setBackground(getResources().getDrawable(R.drawable.ic_bookmark));
                    Toast.makeText(this, "取消收藏", Toast.LENGTH_SHORT).show();
                    deleteNews();
                } else {
                    btn_collect.setBackground(getResources().getDrawable(R.drawable.ic_bookmark_marked));
                    Toast.makeText(this, "已收藏", Toast.LENGTH_SHORT).show();
                    collectNews();
                }
                isCollected = !isCollected;
                break;
        }
    }

    private void deleteNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OpenDbHelper.createDb(NewsDetailActivity.this);
                MyDatabase db = OpenDbHelper.getDb();
                News news = new News();
                news.title = news_title;
                news.url = news_url;
                db.newsDAO().deleteNews(news);
            }
        }).start();
    }

    private void collectNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                OpenDbHelper.createDb(NewsDetailActivity.this);
                MyDatabase db = OpenDbHelper.getDb();
                News news = new News();
                news.title = news_title;
                news.url = news_url;
                news.timeStamp = System.currentTimeMillis();
                db.newsDAO().insert(news);
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        wv_news.clearCache(true);
        wv_news.clearFormData();
        wv_news.clearMatches();
        wv_news.clearSslPreferences();
        wv_news.clearDisappearingChildren();
        wv_news.clearHistory();
        wv_news.clearAnimation();
        wv_news.loadUrl("about:blank");
        wv_news.removeAllViews();
        wv_news.freeMemory();
        super.onDestroy();
    }

    public static void startActivity(Activity activity, Bundle bundle) {
        Intent intent = new Intent(activity, NewsDetailActivity.class);
        intent.putExtra(FLAG, bundle);
        activity.startActivity(intent);
    }

    private void isCollected(final String url) {

        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                boolean result = (Boolean) msg.obj;

                if (result) {
                    btn_collect.setBackground(getResources().getDrawable(R.drawable.ic_bookmark_marked));
                } else {
                    btn_collect.setBackground(getResources().getDrawable(R.drawable.ic_bookmark));
                }
                isCollected = result;
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                OpenDbHelper.createDb(NewsDetailActivity.this);
                MyDatabase db = OpenDbHelper.getDb();
                List<String> urls = db.newsDAO().loadAllUrls();
                boolean isCollected = urls.contains(url);
                Message msg = handler.obtainMessage();
                msg.obj = isCollected;
                handler.sendMessage(msg);
            }
        }).start();
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, final String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            news_url = url;
            // once the title has been set...
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (news_title == null) {
                    }
                    OpenDbHelper.createDb(NewsDetailActivity.this);
                    MyDatabase db = OpenDbHelper.getDb();
                    History history = new History();
                    history.url = url;
                    history.title = news_title;
                    history.timeStamp = System.currentTimeMillis();
                    db.getHistoryDao().insert(history);
                }
            }).start();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            loading_progress.setVisibility(View.GONE);
        }

        @Nullable
        @Override
        public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
            return super.shouldInterceptRequest(view, request);
        }
    }

    private class MyChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
            loading_progress.setProgress(newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
            news_title = title;
        }

        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            return super.onJsAlert(view, url, message, result);
        }

        @Override
        public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
            return super.onJsPrompt(view, url, message, defaultValue, result);
        }
    }


}

package com.sunland.lawreference.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.sunland.lawreference.R;
import com.sunland.lawreference.WindowInfoUtils;
import com.sunland.lawreference.db.LawReferenceBean;
import com.sunland.lawreference.db.MdbOpenHelper;
import com.sunland.lawreference.db.MyDatabase;

import java.util.List;

import butterknife.BindView;

public class Ac_info_detail extends Ac_base {

    @BindView(R.id.webview)
    public WebView wv_detail;
    @BindView(R.id.fab)
    public FloatingActionButton fab_collect;
    private String TAG = "info_test";
    private String fileName;
    private String titleCN;

    private boolean isCollected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentLayout(R.layout.ac_info_detail);
        showNavIcon(true);
        handleIntent();
        setToolbarTitle(titleCN);
        isCollected();
        String url = "file://" + "/android_asset/" + fileName + ".html";
        WebSettings webSettings = wv_detail.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setDefaultFontSize(15);
        wv_detail.getSettings().setJavaScriptEnabled(true);
        wv_detail.loadUrl(url);
        wv_detail.setOnTouchListener(new PageOnTouchListener());
        initView();
    }

    private void handleIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getBundleExtra("bundle");
            if (bundle != null) {
                String one = bundle.get("FILENAME").toString();
                String two = bundle.get("title").toString();
                if (!one.isEmpty()) {
                    fileName = one;
                    titleCN = two;
                }
            }
        }
    }

    private void isCollected() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MdbOpenHelper.createDb(Ac_info_detail.this);
                final List<LawReferenceBean> list = MdbOpenHelper.getDb().getLawRefDao().getRecordByTitle(titleCN);
                isCollected = !list.isEmpty();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (isCollected) {
                            fab_collect.setImageResource(R.drawable.ic_star);
                        } else {
                            fab_collect.setImageResource(R.drawable.ic_unstar_24dp);
                        }
                    }
                });
            }
        }).start();
    }

    private void initView() {
        fab_collect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        if (isCollected) {
                            MdbOpenHelper.createDb(Ac_info_detail.this);
                            LawReferenceBean bean = new LawReferenceBean();
                            bean.title = titleCN;
                            MdbOpenHelper.getDb().getLawRefDao().delete(bean);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    fab_collect.setImageResource(R.drawable.ic_unstar_24dp);
                                    isCollected = false;
                                    Toast.makeText(Ac_info_detail.this, "已从收藏夹中移除", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            MdbOpenHelper.createDb(Ac_info_detail.this);
                            LawReferenceBean bean = new LawReferenceBean();
                            bean.title = titleCN;
                            bean.filename = fileName;
                            bean.timeStamp = System.currentTimeMillis();
                            MyDatabase mdb = MdbOpenHelper.getDb();
                            mdb.getLawRefDao().insert(bean);
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    fab_collect.setImageResource(R.drawable.ic_star);
                                    isCollected = true;
                                    Toast.makeText(Ac_info_detail.this, "已添加到收藏夹中", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                }).start();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (wv_detail != null) {
            wv_detail.destroy();
        }
    }

    class PageOnTouchListener implements View.OnTouchListener {
        float oldDist;
        private int mode = 0;
        private WebView webview;
        private float down_point_y;
        private boolean isShow = true;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            if (view instanceof WebView) {
                webview = (WebView) view;

                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        down_point_y = event.getY();
                        mode = 1;
                        break;
                    case MotionEvent.ACTION_UP:
                        mode = 0;
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        mode -= 1;
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        oldDist = spacing(event);
                        mode += 1;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (event.getY() - down_point_y > WindowInfoUtils.dp2px(Ac_info_detail.this,10) && !isShow) {
                            fab_collect.setVisibility(View.VISIBLE);
                            fab_collect.startAnimation(AnimationUtils.loadAnimation(Ac_info_detail.this, R.anim.fab_show_up_anim));
                            isShow = true;
                        } else if (event.getY() - down_point_y < -WindowInfoUtils.dp2px(Ac_info_detail.this,10) && isShow) {
                            fab_collect.startAnimation(AnimationUtils.loadAnimation(Ac_info_detail.this, R.anim.fab_fade_away_anim));
                            fab_collect.setVisibility(View.GONE);
                            isShow = false;

                        }
                        if (mode >= 2) {
                            float newDist = spacing(event);
                            if (newDist > oldDist + 50) {
                                webview.zoomIn();
                                oldDist = newDist;
                            }
                            if (newDist < oldDist - 50) {
                                webview.zoomOut();
                                oldDist = newDist;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
            return false;
        }

        private float spacing(MotionEvent event) {
            float x = event.getX(0) - event.getX(1);
            float y = event.getY(0) - event.getY(1);
            return (float) Math.sqrt(x * x + y * y);
        }
    }


}

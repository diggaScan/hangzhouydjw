package com.sunland.lawreference.activities;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sunland.lawreference.R;
import com.sunland.lawreference.WindowInfoUtils;

import butterknife.ButterKnife;

public class Ac_base extends AppCompatActivity {
    private Toolbar toolbar;
    private FrameLayout container;
    private ImageView nav_back;
    private TextView tb_title;
    public LinearLayout root;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ac_base);


        toolbar = findViewById(R.id.toolbar);
        container = findViewById(R.id.container);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.setStatusBarColor(Color.TRANSPARENT);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            TypedArray actionbarSizeTypedArray = obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
            float actionBarHeight = actionbarSizeTypedArray.getDimension(0, 0);
            actionbarSizeTypedArray.recycle();
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    WindowInfoUtils.getStatusBarHeight(this) + (int) actionBarHeight);
            toolbar.setLayoutParams(lp);
        }
        nav_back = findViewById(R.id.nav_back);
        root = findViewById(R.id.root);
        nav_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        tb_title = findViewById(R.id.toolbar_title);
        setSupportActionBar(toolbar);
    }

    public void setContentLayout(int layout) {
        getLayoutInflater().inflate(layout, container, true);
        ButterKnife.bind(this);
    }

    public void setToolbarTitle(String title) {
        tb_title.setText(title);
    }

    public void showNavIcon(boolean isShow) {
        if (isShow)
            nav_back.setVisibility(View.VISIBLE);
        else
            nav_back.setVisibility(View.GONE);
    }


    public void hop2Activity(Class<? extends Ac_base> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    public void hop2Activity(Class<? extends Ac_base> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("bundle", bundle);
        startActivity(intent);
    }



}

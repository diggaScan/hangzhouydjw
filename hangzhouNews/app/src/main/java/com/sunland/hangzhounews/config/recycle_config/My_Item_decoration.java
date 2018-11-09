package com.sunland.hangzhounews.config.recycle_config;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.sunland.hangzhounews.R;


public class My_Item_decoration extends RecyclerView.ItemDecoration {
    private int offset;
    private Paint paint;

    public My_Item_decoration(Context context) {
        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.progress_gray));
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(2);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getChildAdapterPosition(view) != 0) {
            outRect.top = 1;
            offset = 1;
        }
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childrenCount = parent.getChildCount();
        for (int i = 0; i < childrenCount; i++) {
            View view = parent.getChildAt(i);
            if (parent.getChildAdapterPosition(view) == 0) {
                continue;
            }
            float top = view.getTop() - offset;
            float left = parent.getLeft();
            float bottom = view.getTop();
            float right = parent.getWidth() - parent.getPaddingRight();
            c.drawRect(left, top, right, bottom, paint);
        }

    }
}

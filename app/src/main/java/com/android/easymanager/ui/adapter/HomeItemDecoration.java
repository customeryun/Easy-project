package com.android.easymanager.ui.adapter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.easymanager.utils.DisplayUtils;

/**
 * Created by PC-xiaoming on 2019/4/29.
 */

public class HomeItemDecoration extends RecyclerView.ItemDecoration{

    private int mWidth;
    private int mHeight;
    private int mItemHeight;
    private Paint mPaint;
    private Context mContext;

    public HomeItemDecoration(Context context){
        this.mContext = context;
        this.mWidth = context.getResources().getDisplayMetrics().widthPixels;
        this.mHeight = context.getResources().getDisplayMetrics().heightPixels;
        this.mItemHeight = DisplayUtils.dip2px(mContext,10);
        this.mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        this.mPaint.setColor(0xd000000);
    }


    @Override
    public void onDraw(Canvas canvas, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(canvas, parent, state);
        //画矩形框
        int left = parent.getPaddingLeft();
        int right = mWidth - parent.getPaddingRight();
        int childCount = parent.getChildCount();
        for (int i=0;i<childCount;i++){
            View view = parent.getChildAt(i);
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
            int top = view.getBottom()+params.bottomMargin;
            int bottom = top+mItemHeight;
            canvas.drawRect(left,top,right,bottom,mPaint);
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(0,0,0,mItemHeight);
    }
}

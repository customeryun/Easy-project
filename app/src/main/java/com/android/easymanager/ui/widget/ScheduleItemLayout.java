package com.android.easymanager.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import com.android.easymanager.R;

public class ScheduleItemLayout extends LinearLayout {
    private static final String TAG = "ScheduleLayout";
    private List<String> mData;
    private Context mContext;
    private LinearLayout parent;
    private LinearLayout mItemParent;
    private View itemView;
    private Button button;
    private String[] items = {"汉语言课程", "Electronic Science and Technology C…", "中国绘画"};

    public ScheduleItemLayout(Context context) {
        this(context, null);
        Log.d(TAG, "ScheduleLayout: 1");
    }

    public ScheduleItemLayout(Context context, AttributeSet attrs) {
        this(context, null, -1);
        Log.d(TAG, "ScheduleLayout: 2");
    }

    public ScheduleItemLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.d(TAG, "ScheduleLayout:  3 init");
        //加载视图
        mContext = context;
        parent = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.item_home_xinghceng_main_layout, this, true);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        Log.d(TAG, "onFinishInflate: ");
        mItemParent = parent.findViewById(R.id.rv_layout);
        setData(Arrays.asList(items));
        setOnclickListener();
    }

    /**
     * 填充数据数据接口
     */
    public void setData(List<String> data) {
        this.mData = data;
        Log.d(TAG, "setData: " + mData.size());
        if (mData != null && mData.size() > 0) {
            for (String title : mData) {
                addItem(title);
            }
        }
    }

    public void addItem(String title) {
        Log.d(TAG, "addItem: ");
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_home_sub_xinghceng_layout, this, false);
        TextView tvTitle = itemView.findViewById(R.id.home_xingcheng_title);
        tvTitle.setText(title);
        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height = 100;
        mItemParent.addView(itemView, params);
    }

    public void setOnclickListener() {
        int count = mItemParent.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = mItemParent.getChildAt(i);
            final int index = i;
            view.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "onClick: " + index);
                    Toast.makeText(mContext, "点击了" + index, Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}

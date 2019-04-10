package com.android.easymanager.ui.widget.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.easymanager.R;

public class ItemView extends LinearLayout {
    boolean isShowTopLine,isShowBottomLine;
    boolean isShowLeftIcon;
    boolean isShowRightArrow;
    ImageView leftIcon;
    TextView leftTitle,rightDesc;
    ImageView rightArrow;
    View topLine,bottomLine;

    public ItemView(Context context) {
        this(context, null);
    }

    public ItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ItemView);

        LayoutInflater.from(context).inflate(R.layout.user_settings_item_layout,this);

        isShowTopLine = array.getBoolean(R.styleable.ItemView_show_top_line, false);//得到是否显示底部上划线属性
        isShowBottomLine = array.getBoolean(R.styleable.ItemView_show_bottom_line, true);//得到是否显示底部下划线属性
        isShowLeftIcon = array.getBoolean(R.styleable.ItemView_show_left_icon, false);//得到是否显示左侧图标属性标识
        isShowRightArrow = array.getBoolean(R.styleable.ItemView_show_right_arrow, true);//得到是否显示右侧图标属性标识

        leftIcon = (ImageView)findViewById(R.id.left_icon);
        leftIcon.setBackground(array.getDrawable(R.styleable.ItemView_left_icon));//设置左侧图标
        leftTitle = (TextView)findViewById(R.id.left_text);
        leftTitle.setText(array.getString(R.styleable.ItemView_left_text));//设置左侧标题文字
        leftIcon.setVisibility(isShowLeftIcon ? View.VISIBLE : View.GONE);//设置左侧箭头图标是否显示
        rightDesc = (TextView)findViewById(R.id.right_text);
        rightDesc.setText(array.getString(R.styleable.ItemView_right_text));//设置右侧文字描述
        topLine = (View)findViewById(R.id.top_line);
        topLine.setVisibility(isShowTopLine ? View.VISIBLE : View.GONE);
        bottomLine = (View)findViewById(R.id.bottom_line);
        bottomLine.setVisibility(isShowBottomLine ? View.VISIBLE : View.GONE);//设置底部图标是否显示
        rightArrow = (ImageView)findViewById(R.id.right_arrow);
        rightArrow.setVisibility(isShowRightArrow ? View.VISIBLE : View.INVISIBLE);//设置右侧箭头图标是否显示

        array.recycle();
    }
}

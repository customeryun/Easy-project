package com.android.easymanager.ui.widget.settings;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.easymanager.R;

public class SettingsItemView extends LinearLayout {
    boolean isShowTopLine,isShowBottomLine;
//    boolean isShowLeftIcon;
    boolean isShowRightArrow;
    boolean isShowRightText;
//    ImageView leftIcon;
    TextView leftTitle,rightDesc;
    ImageView rightArrow;
    View topLine,bottomLine;

    public SettingsItemView(Context context) {
        this(context, null);
    }

    public SettingsItemView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingsItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttrs(context, attrs);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.SettingsItemView);
        LayoutInflater.from(context).inflate(R.layout.settings_item_view_layout,this);

        isShowTopLine = array.getBoolean(R.styleable.SettingsItemView_item_show_top_line, false);
        isShowBottomLine = array.getBoolean(R.styleable.SettingsItemView_item_show_bottom_line, true);
//        isShowLeftIcon = array.getBoolean(R.styleable.ItemView_show_left_icon, false);
        isShowRightArrow = array.getBoolean(R.styleable.SettingsItemView_item_show_right_arrow, true);
        isShowRightText = array.getBoolean(R.styleable.SettingsItemView_item_show_right_text, false);

//        leftIcon = (ImageView)findViewById(R.id.left_icon);
//        leftIcon.setBackground(array.getDrawable(R.styleable.ItemView_left_icon));//设置左侧图标
        leftTitle = (TextView)findViewById(R.id.left_text);
        leftTitle.setText(array.getString(R.styleable.SettingsItemView_item_left_text));//设置左侧标题文字
        leftTitle.setTextColor(array.getColor(R.styleable.SettingsItemView_left_text_color, Color.parseColor("#333333")));
//        leftIcon.setVisibility(isShowLeftIcon ? View.VISIBLE : View.GONE);//设置左侧箭头图标是否显示
        rightDesc = (TextView)findViewById(R.id.right_text);
        rightDesc.setText(array.getString(R.styleable.SettingsItemView_item_right_text));//设置右侧文字描述
        rightDesc.setTextColor(array.getColor(R.styleable.SettingsItemView_right_text_color, Color.parseColor("#333333")));
        rightDesc.setVisibility(isShowRightText ? View.VISIBLE : View.INVISIBLE);
        topLine = (View)findViewById(R.id.top_line);
        topLine.setVisibility(isShowTopLine ? View.VISIBLE : View.GONE);
        bottomLine = (View)findViewById(R.id.bottom_line);
        bottomLine.setVisibility(isShowBottomLine ? View.VISIBLE : View.GONE);//设置底部图标是否显示
        rightArrow = (ImageView)findViewById(R.id.right_arrow);
        rightArrow.setVisibility(isShowRightArrow ? View.VISIBLE : View.GONE);//设置右侧箭头图标是否显示

        array.recycle();
    }
}

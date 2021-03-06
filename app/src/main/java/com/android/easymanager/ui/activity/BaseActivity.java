package com.android.easymanager.ui.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.easymanager.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.event.LoginStateChangeEvent;

/**
 * Created by PC-xiaoming on 2019/4/5.
 */

public abstract class BaseActivity extends AppCompatActivity{

    protected Context mContext;

    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.iv_title)
    TextView iv_title;
    @BindView(R.id.actionbar)
    LinearLayout actionbar;
    @BindView(R.id.iv_add)
    ImageView iv_add;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        mContext = this;
        init();
        setBackListener();
        JMessageClient.registerEventReceiver(this);
    }

    public abstract int getLayout();

    public abstract void init();

    protected void setTitle(String title){
        iv_title.setText(title);
    }

    protected void setBackVisible(boolean visible){
        iv_back.setVisibility(visible? View.VISIBLE:View.INVISIBLE);
    }

    protected void setTitleVisible(boolean visible){
        iv_title.setVisibility(visible? View.VISIBLE:View.INVISIBLE);
    }

    protected void setActionbarVisible(boolean visible){
        actionbar.setVisibility(visible? View.VISIBLE:View.GONE);
    }

    protected void setBackListener(){
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    protected void setAddIconRes(int resId){
        iv_add.setImageResource(resId);
    }

    protected void setAddIconVisible(boolean visible){
        iv_add.setVisibility(visible?View.VISIBLE:View.GONE);
    }

    protected void setAddIconListener(View.OnClickListener listener){
        iv_add.setOnClickListener(listener);
    }

    public void onEventMainThread(LoginStateChangeEvent event) {

    }

}

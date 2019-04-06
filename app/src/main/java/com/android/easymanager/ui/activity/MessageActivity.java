package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.android.easymanager.R;

public class MessageActivity extends BaseActivity{

    @Override
    public int getLayout() {
        return R.layout.message_manager_layout;
    }

    @Override
    public void init() {
        setTitle("我的消息");

    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,MessageActivity.class);
        context.startActivity(intent);
    }
}

package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.android.easymanager.R;
import com.android.easymanager.ui.activity.BaseActivity;

public class ContactActivity extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.contact_manager_layout;
    }

    @Override
    public void init() {
        setTitle("我的好友");

    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,ContactActivity.class);
        context.startActivity(intent);
    }
}

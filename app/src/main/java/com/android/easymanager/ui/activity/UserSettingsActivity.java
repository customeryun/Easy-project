package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.android.easymanager.R;

public class UserSettingsActivity extends BaseActivity{

    @Override
    public int getLayout() {
        return R.layout.user_settings;
    }

    @Override
    public void init() {
        setTitle("设置");

    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,UserSettingsActivity.class);
        context.startActivity(intent);
    }
}

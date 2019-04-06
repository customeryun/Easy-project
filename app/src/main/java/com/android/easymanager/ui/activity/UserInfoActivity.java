package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.android.easymanager.R;

public class UserInfoActivity extends BaseActivity{

    @Override
    public int getLayout() {
        return R.layout.user_info;
    }

    @Override
    public void init() {
        setTitle("我的二维码");

    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,UserInfoActivity.class);
        context.startActivity(intent);
    }
}

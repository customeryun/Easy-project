package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.android.easymanager.R;

public class PasswordForgetActivity extends BaseActivity{


    @Override
    public int getLayout() {
        return R.layout.password_forget_layout;
    }

    @Override
    public void init() {
        setTitle("忘记密码");
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,PasswordForgetActivity.class);
        context.startActivity(intent);
    }

}

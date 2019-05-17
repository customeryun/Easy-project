package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.android.easymanager.R;

public class LoginPasswordForgetActivity extends BaseActivity{


    @Override
    public int getLayout() {
        return R.layout.login_password_forget_layout;
    }

    @Override
    public void init() {
        setTitle("忘记密码");
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,LoginPasswordForgetActivity.class);
        context.startActivity(intent);
    }

}

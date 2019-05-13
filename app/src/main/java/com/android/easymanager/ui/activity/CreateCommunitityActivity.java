package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import com.android.easymanager.R;

public class CreateCommunitityActivity extends BaseActivity{

    @Override
    public int getLayout() {
        return R.layout.communitity_create_layout;
    }

    @Override
    public void init() {
        setTitle("发布动态");
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,CreateCommunitityActivity.class);
        context.startActivity(intent);
    }
}

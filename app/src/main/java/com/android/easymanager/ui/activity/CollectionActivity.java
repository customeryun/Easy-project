package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.android.easymanager.R;

public class CollectionActivity extends BaseActivity{

    @Override
    public int getLayout() {
        return R.layout.collection_manager_layout;
    }

    @Override
    public void init() {
        setTitle("我的收藏");

    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,CollectionActivity.class);
        context.startActivity(intent);
    }
}

package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import com.android.easymanager.R;

public class SchoolAnnouncementDetailActivity extends BaseActivity{

    @Override
    public int getLayout() {
        return R.layout.activity_school_announcement_detail;
    }

    @Override
    public void init() {
        setTitle("公告详情");
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,SchoolAnnouncementDetailActivity.class);
        context.startActivity(intent);
    }
}

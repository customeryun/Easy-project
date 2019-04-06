package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;

import com.android.easymanager.R;

/**
 * Created by PC-xiaoming on 2019/4/6.
 */

public class StudentLeaveActivity extends BaseActivity{

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,StudentLeaveActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.student_leave_layout;
    }

    @Override
    public void init() {
        setTitle("请假");
    }
}

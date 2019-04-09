package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import com.android.easymanager.R;

public class ApprovalHistoryActivity extends BaseActivity {
    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, ApprovalHistoryActivity.class));
    }

    public int getLayout() {
        return R.layout.me_approval_history_layout;
    }

    public void init() {
        setTitle("请假记录");
    }
}

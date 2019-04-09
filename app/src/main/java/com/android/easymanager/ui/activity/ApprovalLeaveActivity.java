package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import com.android.easymanager.R;

public class ApprovalLeaveActivity extends BaseActivity {
    public static void launchActivity(Context context) {
        context.startActivity(new Intent(context, ApprovalLeaveActivity.class));
    }

    public int getLayout() {
        return R.layout.approval_leave_layout;
    }

    public void init() {
        setTitle("某某提交的假期");
    }
}

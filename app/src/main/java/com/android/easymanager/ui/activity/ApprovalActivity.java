package com.android.easymanager.ui.activity;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;

import com.android.easymanager.R;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PC-xiaoming on 2019/4/6.
 */

public class ApprovalActivity extends BaseActivity{

    @BindView(R.id.me_approval)
    FrameLayout me_approval;
    @BindView(R.id.me_send)
    FrameLayout me_send;
    @BindView(R.id.c_send_me)
    FrameLayout c_send_me;

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,ApprovalActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.approval_layout;
    }

    @Override
    public void init() {
        setTitle("审批");
    }

    @OnClick({R.id.me_approval,R.id.me_send,R.id.c_send_me})
    public void onClick(View view){
        switch (view.getId()){
            case  R.id.me_approval:
                MeApprovalActivity.launchActivity(mContext);
                break;
            case R.id.me_send:
                break;
            case R.id.c_send_me:
                break;
        }
    }
}

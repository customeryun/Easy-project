package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.android.easymanager.R;

import butterknife.BindView;
import butterknife.OnClick;

public class RequestFriendActivity extends BaseActivity{
    @BindView(R.id.send_request)
    Button btn_request;

    @Override
    public int getLayout() {
        return R.layout.request_for_friend_layout;
    }

    @Override
    public void init() {
        setTitle("好友申请");
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,RequestFriendActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.send_request})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_request:

        }
    }

}

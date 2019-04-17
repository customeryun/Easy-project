package com.android.easymanager.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.easymanager.R;
import com.android.easymanager.model.InfoModel;

public class SearchFriendInfoActivity extends /*Base*/Activity implements View.OnClickListener {

    private TextView mTv_userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_friend_info);

        initView();
        initData();
    }

    private void initView() {

        mTv_userName = (TextView) findViewById(R.id.tv_userName);

        findViewById(R.id.btn_addFriend).setOnClickListener(this);
    }

    private void initData() {
        InfoModel instance = InfoModel.getInstance();
        mTv_userName.setText(instance.getUserName());
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_addFriend:
                //添加好友界面
                intent = new Intent(SearchFriendInfoActivity.this, VerificationActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}

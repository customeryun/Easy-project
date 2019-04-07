package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.android.easymanager.R;

public class MessageActivity extends BaseActivity{

    @Override
    public int getLayout() {
        return R.layout.message_manager_layout;
    }

    @Override
    public void init() {
        setTitle("我的消息");
        setAddIconVisible(true);
        setAddIconRes(android.R.drawable.ic_menu_add);
        setAddIconListener(mAddOnClickListener);
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,MessageActivity.class);
        context.startActivity(intent);
    }

    View.OnClickListener mAddOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {

        }
    };

}

package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.ContactDetailAdapter;
import com.android.easymanager.ui.bean.ListItemEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PasswordModifyActivity extends BaseActivity{


    @Override
    public int getLayout() {
        return R.layout.password_modify_layout;
    }

    @Override
    public void init() {
        setTitle("更改密码");
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,PasswordModifyActivity.class);
        context.startActivity(intent);
    }

}

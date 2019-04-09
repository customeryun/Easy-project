package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.ContactEasyAdapter;
import com.android.easymanager.ui.bean.Contact;
import com.android.easymanager.ui.bean.ContactGroupEntry;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TeacherChildActivity extends BaseActivity implements ContactEasyAdapter.RvOnItemListener{

    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;

    ContactGroupEntry mEntry;

    @Override
    public int getLayout() {
        return R.layout.message_manager_layout;
    }

    @Override
    public void init() {
        mEntry = (ContactGroupEntry)getIntent().getSerializableExtra("data");
        setTitle(mEntry.getName());

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initRecycleView();
    }

    public static void launchActivity(Context context, ContactGroupEntry entry){
        Intent intent = new Intent(context,TeacherChildActivity.class);
        intent.putExtra("data",entry);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        ContactEasyAdapter adapter = new ContactEasyAdapter(mContext, buildItems(),this,false);

        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public List<Contact> buildItems() {
        List<Contact> managerEntries = new ArrayList<>();
        managerEntries.add(new Contact("张三","主任"));
        managerEntries.add(new Contact("李四","校长"));
        managerEntries.add(new Contact("王五","助理"));
        managerEntries.add(new Contact("赵六","副主任"));
        return managerEntries;
    }

    @Override
    public void onItemClick(int position, Contact entry) {
        Toast.makeText(mContext,"**position=="+position,Toast.LENGTH_LONG).show();
    }

}

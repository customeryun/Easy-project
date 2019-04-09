package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.ContactEasyAdapter;
import com.android.easymanager.ui.adapter.StudentItemAdapter;
import com.android.easymanager.ui.bean.Contact;
import com.android.easymanager.ui.bean.ContactGroupEntry;
import com.android.easymanager.ui.bean.StudentItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StudentChildActivity extends BaseActivity implements StudentItemAdapter.RvOnItemListener{

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
        Intent intent = new Intent(context,StudentChildActivity.class);
        intent.putExtra("data",entry);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        StudentItemAdapter adapter = new StudentItemAdapter(mContext, buildItems(),this);

        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public List<StudentItem> buildItems() {
        List<StudentItem> managerEntries = new ArrayList<>();
        managerEntries.add(new StudentItem(0,"张三","男","中国", "20190409001"));
        managerEntries.add(new StudentItem(0,"李四","男","美国", "20190409021"));
        managerEntries.add(new StudentItem(0,"王五","女","中国", "20190409001"));
        managerEntries.add(new StudentItem(0,"赵六","男","美国", "20190409021"));
        managerEntries.add(new StudentItem(0,"张三","男","中国", "20190409001"));
        managerEntries.add(new StudentItem(0,"李七","男","美国", "20190409021"));
        return managerEntries;
    }

    @Override
    public void onItemClick(int position, StudentItem entry) {
        Toast.makeText(mContext,"**position=="+position,Toast.LENGTH_LONG).show();
    }

}

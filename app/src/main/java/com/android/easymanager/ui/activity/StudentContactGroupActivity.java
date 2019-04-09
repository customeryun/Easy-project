package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.Toast;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.StudentGroupAdapter;
import com.android.easymanager.ui.bean.ContactGroupEntry;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class StudentContactGroupActivity extends BaseActivity implements StudentGroupAdapter.RvOnItemListener{

    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;

    @Override
    public int getLayout() {
        return R.layout.contact_group_layout;
    }

    @Override
    public void init() {
        setTitle("学生通讯录");
        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,StudentContactGroupActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        StudentGroupAdapter adapter = new StudentGroupAdapter(mContext, buildItems(),this);

        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public List<ContactGroupEntry> buildItems() {
        List<ContactGroupEntry> managerEntries = new ArrayList<>();
        managerEntries.add(new ContactGroupEntry(0,"汉语进修生1班",40));
        managerEntries.add(new ContactGroupEntry(1,"汉语进修生2班",2));
        managerEntries.add(new ContactGroupEntry(2,"石油工程学院 石油工程专业2019班",5));
        return managerEntries;
    }

    @Override
    public void onItemClick(int position, ContactGroupEntry entry) {
        Toast.makeText(mContext,"**position=="+position,Toast.LENGTH_LONG).show();
        //进入学生child列表页面
    }

}

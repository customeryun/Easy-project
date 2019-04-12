package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.BaseRecyclerAdapter;
import com.android.easymanager.ui.adapter.StudentGroupAdapter;
import com.android.easymanager.ui.bean.ContactGroupEntry;
import java.util.ArrayList;
import butterknife.BindView;

public class TeacherGroupActivity extends BaseActivity implements BaseRecyclerAdapter.OnItemClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;

    @Override
    public int getLayout() {
        return R.layout.teacher_group_layout;
    }

    @Override
    public void init() {
        setTitle("老师通讯录");
        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,TeacherGroupActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        StudentGroupAdapter adapter = new StudentGroupAdapter();
        adapter.setOnItemClickListener(this);
        adapter.addDatas(buildItems());

        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public ArrayList<ContactGroupEntry> buildItems() {
        ArrayList<ContactGroupEntry> managerEntries = new ArrayList<>();
        managerEntries.add(new ContactGroupEntry(0,"国际教育中心",3));
        managerEntries.add(new ContactGroupEntry(1,"招生办公室",2));
        managerEntries.add(new ContactGroupEntry(2,"综合办公室",5));
        return managerEntries;
    }

    @Override
    public void onItemClick(int position, Object entry) {
        Toast.makeText(mContext,"**position=="+position,Toast.LENGTH_LONG).show();
        //进入child列表页面
        TeacherChildActivity.launchActivity(mContext,(ContactGroupEntry)entry);
    }

}

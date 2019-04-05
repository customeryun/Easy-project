package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.StudentAdapter;
import com.android.easymanager.ui.bean.StudentManagerEntry;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by PC-xiaoming on 2019/4/5.
 */

public class StudentManagerActivity extends BaseActivity {

    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;

    @Override
    public int getLayout() {
        return R.layout.student_manager_layout;
    }

    @Override
    public void init() {
        setTitle("名单管理");
        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,StudentManagerActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        StudentAdapter adapter = new StudentAdapter(mContext, buildItems());
        recycle_view.setLayoutManager(new GridLayoutManager(mContext,3));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public ArrayList<StudentManagerEntry> buildItems() {
        ArrayList<StudentManagerEntry> managerEntries = new ArrayList<>();
        managerEntries.add(new StudentManagerEntry());
        managerEntries.add(new StudentManagerEntry());
        managerEntries.add(new StudentManagerEntry());
        managerEntries.add(new StudentManagerEntry());
        managerEntries.add(new StudentManagerEntry());
        managerEntries.add(new StudentManagerEntry());
        managerEntries.add(new StudentManagerEntry());
        managerEntries.add(new StudentManagerEntry());
        managerEntries.add(new StudentManagerEntry());
        managerEntries.add(new StudentManagerEntry());
        managerEntries.add(new StudentManagerEntry());
        managerEntries.add(new StudentManagerEntry());
        return managerEntries;
    }
}

package com.android.easymanager.ui.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.StudentAdapter;
import com.android.easymanager.ui.bean.StudentManagerEntry;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by PC-xiaoming on 2019/4/5.
 */

public class StudentManagerActivity extends BaseActivity implements StudentAdapter.RvOnItemListener{

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
        StudentAdapter adapter = new StudentAdapter(mContext, buildItems(),this);
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

    public void buildDialog(){
        View view = LayoutInflater.from(this).inflate(R.layout.student_manager_dialog_layout,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(this).setView(view).create();
        dialog.show();

    }

    @Override
    public void onItemClick(int position, StudentManagerEntry entry) {
        buildDialog();
    }
}

package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.ContactDetailAdapter;
import com.android.easymanager.ui.bean.ListItemEntry;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class ContactDetailActivity extends BaseActivity implements ContactDetailAdapter.RvOnItemListener{

    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;

    boolean isMe = false;

    @Override
    public int getLayout() {
        return R.layout.contact_detail_layout;
    }

    @Override
    public void init() {
        setActionbarVisible(false);
        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,ContactDetailActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        ContactDetailAdapter adapter = new ContactDetailAdapter(mContext, buildItems(),this);
        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public List<ListItemEntry> buildItems() {
        List<ListItemEntry> managerEntries = new ArrayList<>();
        managerEntries.add(new ListItemEntry("英文姓名:AAAAAAA",""));
        managerEntries.add(new ListItemEntry("国籍：中国",""));
        managerEntries.add(new ListItemEntry("班级：112",""));
        managerEntries.add(new ListItemEntry("专业：计算机",""));
        managerEntries.add(new ListItemEntry("院系：电信学院",""));
        return managerEntries;
    }

    @Override
    public void onItemClick(int position, ListItemEntry entry) {
        Toast.makeText(mContext,"**position=="+position,Toast.LENGTH_LONG).show();
    }
}

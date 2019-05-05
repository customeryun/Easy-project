package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.BaseRecyclerAdapter;
import com.android.easymanager.ui.adapter.ContactDetailAdapter;
import com.android.easymanager.ui.bean.ListItemEntry;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.OnClick;

public class MeDetailActivity extends BaseActivity implements BaseRecyclerAdapter.OnItemClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;
    @BindView(R.id.layout_head)
    RelativeLayout layout_head;
    @BindView(R.id.itemlayout_icon)
    LinearLayout layout_icon;

    @Override
    public int getLayout() {
        return R.layout.contact_detail_layout;
    }

    @Override
    public void init() {
        setActionbarVisible(true);
        setTitle("个人信息");
        layout_head.setVisibility(View.GONE);
        layout_icon.setVisibility(View.VISIBLE);
        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,MeDetailActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        ContactDetailAdapter adapter = new ContactDetailAdapter();
        adapter.addDatas(buildItems());
        adapter.setOnItemClickListener(this);
        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public ArrayList<ListItemEntry> buildItems() {
        ArrayList<ListItemEntry> managerEntries = new ArrayList<>();
        managerEntries.add(new ListItemEntry("中文姓名:","林"));
        managerEntries.add(new ListItemEntry("英文姓名:","xianya"));
        managerEntries.add(new ListItemEntry("性别:","未知"));
        managerEntries.add(new ListItemEntry("国籍：","中国"));
        managerEntries.add(new ListItemEntry("班级：","112"));
        managerEntries.add(new ListItemEntry("专业：","计算机"));
        managerEntries.add(new ListItemEntry("院系：","电信学院"));
        return managerEntries;
    }

    @Override
    public void onItemClick(int position, Object entry) {
        ListItemEntry item = (ListItemEntry)entry;
//        Toast.makeText(mContext,"**position=="+position+"***"+item.getTitle(),Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.itemlayout_icon})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.itemlayout_icon:
                UserInfoActivity.launchActivity(mContext);
        }
    }
}

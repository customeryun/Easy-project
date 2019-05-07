package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.BaseRecyclerAdapter;
import com.android.easymanager.ui.adapter.ContactDetailAdapter;
import com.android.easymanager.ui.bean.ListItemEntry;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.OnClick;

public class ContactDetailActivity extends BaseActivity implements BaseRecyclerAdapter.OnItemClickListener{

//    @BindView(R.id.recyclerView)
//    RecyclerView recycle_view;
//    @BindView(R.id.layout_bottom)
//    LinearLayout layout_bottom;
    @BindView(R.id.btn_add)
    Button btn_add;

    boolean isFrident = true;

    @Override
    public int getLayout() {
        //return R.layout.contact_detail_layout;
        return R.layout.activity_student_detail_layout;
    }

    @Override
    public void init() {
        setActionbarVisible(false);
//        initRecycleView();
        isFrident = getIntent().getBooleanExtra("isfrident",true);
//        layout_bottom.setVisibility(isFrident? View.GONE:View.VISIBLE);

    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,ContactDetailActivity.class);
        context.startActivity(intent);
    }

    public static void launchActivity(Context context,boolean isFrident){
        Intent intent = new Intent(context,ContactDetailActivity.class);
        intent.putExtra("isfrident",isFrident);
        context.startActivity(intent);
    }

//    public void initRecycleView() {
//        ContactDetailAdapter adapter = new ContactDetailAdapter();
//        adapter.addDatas(buildItems());
//        adapter.setOnItemClickListener(this);
//        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
//        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
//        recycle_view.setAdapter(adapter);
//    }
//
//    public ArrayList<ListItemEntry> buildItems() {
//        ArrayList<ListItemEntry> managerEntries = new ArrayList<>();
//        managerEntries.add(new ListItemEntry("英文姓名:AAAAAAA",""));
//        managerEntries.add(new ListItemEntry("国籍：中国",""));
//        managerEntries.add(new ListItemEntry("班级：112",""));
//        managerEntries.add(new ListItemEntry("专业：计算机",""));
//        managerEntries.add(new ListItemEntry("院系：电信学院",""));
//        return managerEntries;
//    }

    @Override
    public void onItemClick(int position, Object data) {
        ListItemEntry item = (ListItemEntry)data;
//        Toast.makeText(mContext,"**position=="+position+"***"+item.getTitle(),Toast.LENGTH_LONG).show();
    }

    @OnClick({R.id.btn_add,R.id.return_btn})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                RequestFriendActivity.launchActivity(mContext);
            case R.id.return_btn:
                finish();
        }
    }
}

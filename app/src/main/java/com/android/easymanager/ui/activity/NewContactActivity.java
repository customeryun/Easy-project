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
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class NewContactActivity extends BaseActivity implements ContactEasyAdapter.RvOnItemListener{

    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;

    @Override
    public int getLayout() {
        return R.layout.message_manager_layout;
    }

    @Override
    public void init() {
        setTitle("新的好友");
        setAddIconVisible(true);
        setAddIconRes(android.R.drawable.ic_menu_add);
        setAddIconListener(mAddOnClickListener);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,NewContactActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        ContactEasyAdapter adapter = new ContactEasyAdapter(mContext, buildItems(),this,true);

        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public List<Contact> buildItems() {
        List<Contact> managerEntries = new ArrayList<>();
        managerEntries.add(new Contact("张三","计算机01班",false));
        managerEntries.add(new Contact("李四","计算机02班",true));
        managerEntries.add(new Contact("王五","计算机01班",true));
        managerEntries.add(new Contact("赵六","计算机01班",true));
        return managerEntries;
    }

    View.OnClickListener mAddOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            ContactAddActivity.launchActivity(mContext);
        }
    };

    @Override
    public void onItemClick(int position, Contact entry) {
        Toast.makeText(mContext,"**position=="+position,Toast.LENGTH_LONG).show();
        ContactDetailActivity.launchActivity(mContext);
    }

}

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
import com.android.easymanager.ui.adapter.BaseRecyclerAdapter;
import com.android.easymanager.ui.adapter.MessageGroupAdapter;
import com.android.easymanager.ui.bean.MessageGroupEntry;
import java.util.ArrayList;
import butterknife.BindView;

public class MessageActivity extends BaseActivity implements BaseRecyclerAdapter.OnItemClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;

    @Override
    public int getLayout() {
        return R.layout.message_manager_layout;
    }

    @Override
    public void init() {
        setTitle("我的消息");
        setAddIconVisible(true);
        setAddIconRes(android.R.drawable.ic_menu_add);
        setAddIconListener(mAddOnClickListener);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,MessageActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        MessageGroupAdapter adapter = new MessageGroupAdapter();
        adapter.setOnItemClickListener(this);
        adapter.addDatas(buildItems());
        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public ArrayList<MessageGroupEntry> buildItems() {
        ArrayList<MessageGroupEntry> managerEntries = new ArrayList<>();
        managerEntries.add(new MessageGroupEntry());
        managerEntries.add(new MessageGroupEntry());
        return managerEntries;
    }

    View.OnClickListener mAddOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {

        }
    };

    @Override
    public void onItemClick(int position, Object entry) {
//        Toast.makeText(mContext,"**position=="+position,Toast.LENGTH_LONG).show();
    }

}

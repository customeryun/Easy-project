package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.easymanager.IxiaApplication;
import com.android.easymanager.R;
import com.android.easymanager.database.FriendEntry;
import com.android.easymanager.database.FriendRecommendEntry;
import com.android.easymanager.database.UserEntry;
import com.android.easymanager.ui.adapter.BaseRecyclerAdapter;
import com.android.easymanager.ui.adapter.ContactEasyAdapter;
import com.android.easymanager.ui.adapter.FriendRecommendAdapter;
import com.android.easymanager.ui.bean.Contact;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FriendRecommendActivity extends BaseActivity implements BaseRecyclerAdapter.OnItemClickListener{

    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;

    private FriendRecommendAdapter adapter;
//    private List<FriendRecommendEntry> mList;

    @Override
    public int getLayout() {
        return R.layout.activity_friend_recommend;
    }

    @Override
    public void init() {
        setTitle("新的好友");
//        setAddIconVisible(true);
//        setAddIconRes(android.R.drawable.ic_menu_add);
//        setAddIconListener(mAddOnClickListener);
//
//        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,FriendRecommendActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        adapter = new FriendRecommendAdapter(this);
        adapter.addDatas(buildItems());
        adapter.setOnItemClickListener(this);

        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public List<FriendRecommendEntry> buildItems() {
        List<FriendRecommendEntry> managerEntries = new ArrayList<>();
        UserEntry user = IxiaApplication.getUserEntry();
        if (null != user) {
            managerEntries = user.getRecommends();
            Log.i("linmei","***user.getRecommends()**"+managerEntries.size());

        } else {
            Log.e("FriendRecommendActivity", "Unexpected error: User table null");
        }
        return managerEntries;
    }

    View.OnClickListener mAddOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            ContactAddActivity.launchActivity(mContext);
        }
    };

    @Override
    public void onItemClick(int position, Object entry) {
        Toast.makeText(mContext,"**position=="+position,Toast.LENGTH_LONG).show();
        ContactDetailActivity.launchActivity(mContext);
        //待处理：根据状态不同跳转不同详情页面......
    }

}

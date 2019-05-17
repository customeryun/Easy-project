package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.BaseRecyclerAdapter;
import com.android.easymanager.ui.adapter.HomeMainAdapter;
import com.android.easymanager.ui.adapter.SchoolActionAdapter;
import com.android.easymanager.ui.bean.SocietyBean;
import com.android.easymanager.ui.fragment.HomeMainFragment;

import java.util.Arrays;

import butterknife.BindView;

public class SocietyActionListActivity extends BaseActivity implements SchoolActionAdapter.OnRvItemListener {
    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;
    public static SocietyBean mSociety;
    @Override
    public int getLayout() {
        return R.layout.activity_society_action_layout;
    }

    @Override
    public void init() {
        setTitle(mSociety.getName());
        initRecycleView();
    }

    public static void launchActivity(Context context,SocietyBean item){
        mSociety = item;
        Intent intent = new Intent(context,SocietyActionListActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        SchoolActionAdapter adapter = new SchoolActionAdapter(this, Arrays.asList(HomeMainFragment.mUrls));
//        View layout = LayoutInflater.from(mContext).inflate(R.layout.activity_society_action_head_layout,null);
//        adapter.setHeaderView(layout);
//        adapter.addDatas(buildItems());
//        adapter.setOnItemClickListener(this);
        adapter.setOnRvItemListener(this);

        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        DividerItemDecoration divider = new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
//        divider.setDrawable(getDrawable(R.drawable.society_divider_bg));
        recycle_view.addItemDecoration(divider);
        recycle_view.setAdapter(adapter);
    }

    @Override
    public void onItemClick(int position, int type) {
        if(type == SchoolActionAdapter.TYPE_NEW_DONGTAI){
            CommentActivity.launchActivity(mContext);
        }
    }
}

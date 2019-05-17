package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.BaseRecyclerAdapter;
import com.android.easymanager.ui.adapter.SchoolSocietyListAdapter;
import com.android.easymanager.ui.bean.SocietyBean;

import java.util.ArrayList;

import butterknife.BindView;

public class SchoolSocietyListActivity extends BaseActivity implements BaseRecyclerAdapter.OnItemClickListener{
    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;

    @Override
    public int getLayout() {
        return R.layout.activity_school_society_list;
    }

    @Override
    public void init() {
        setActionbarVisible(false);
        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,SchoolSocietyListActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        SchoolSocietyListAdapter adapter = new SchoolSocietyListAdapter();
        adapter.addDatas(buildItems());
        adapter.setOnItemClickListener(this);

        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        DividerItemDecoration divider = new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        divider.setDrawable(getDrawable(R.drawable.society_divider_bg));
        recycle_view.addItemDecoration(divider);
        recycle_view.setAdapter(adapter);
    }

    public ArrayList<SocietyBean> buildItems() {
        ArrayList<SocietyBean> items = new ArrayList<>();
        items.add(new SocietyBean("足球社","运动",0));
        items.add(new SocietyBean("新闻社","文艺",1));
        items.add(new SocietyBean("篮球社","运动",1));
        return items;
    }

    @Override
    public void onItemClick(int position, Object data) {
        SocietyBean item = (SocietyBean)data;
        //进入社区动态页
        SocietyActionListActivity.launchActivity(mContext,item);
    }
}

package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.BaseRecyclerAdapter;
import com.android.easymanager.ui.adapter.MySchoolSocietyAdapter;
import com.android.easymanager.ui.bean.SocietyBean;
import java.util.ArrayList;
import butterknife.BindView;

public class MySchoolSocietyActivity extends BaseActivity implements BaseRecyclerAdapter.OnItemClickListener{
    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;

    @Override
    public int getLayout() {
        return R.layout.activity_my_society_list_layout;
    }

    @Override
    public void init() {
        setTitle("社团");
        setAddIconRes(R.drawable.ic_meau_add_friend);
        setAddIconVisible(true);
        setAddIconListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //进入社团列表选择要加入的社团
                SchoolSocietyListActivity.launchActivity(mContext);
            }
        });
        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,MySchoolSocietyActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        MySchoolSocietyAdapter adapter = new MySchoolSocietyAdapter();
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
        //进入社区动态详情页
        SocietyActionListActivity.launchActivity(mContext,item);
    }
}

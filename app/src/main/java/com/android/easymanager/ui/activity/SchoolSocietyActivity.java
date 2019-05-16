package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.SchoolSocietyAdapter;
import com.android.easymanager.ui.bean.SocietyBean;
import java.util.ArrayList;
import butterknife.BindView;

public class SchoolSocietyActivity extends BaseActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recycle_view;

    @Override
    public int getLayout() {
        return R.layout.activity_school_society;
    }

    @Override
    public void init() {
        setTitle("社团");
        setAddIconRes(R.drawable.ic_meau_add_friend);
        setAddIconVisible(true);
        setAddIconListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //申请加入新社团
            }
        });
        initRecycleView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,SchoolSocietyActivity.class);
        context.startActivity(intent);
    }

    public void initRecycleView() {
        SchoolSocietyAdapter adapter = new SchoolSocietyAdapter();
        adapter.addDatas(buildItems());
//        adapter.setOnItemClickListener(this);

        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recycle_view.setAdapter(adapter);
    }

    public ArrayList<SocietyBean> buildItems() {
        ArrayList<SocietyBean> items = new ArrayList<>();
        items.add(new SocietyBean("'足球社"));
        items.add(new SocietyBean("'新闻社"));
        return items;
    }

}

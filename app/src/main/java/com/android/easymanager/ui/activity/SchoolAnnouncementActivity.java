package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.SchoolAnnouncementAdapter;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by 肖明 on 2019/4/24.
 */

public class SchoolAnnouncementActivity extends BaseActivity{

    @BindView(R.id.school_announcement_list)
    RecyclerView school_announcement_list;
    private SchoolAnnouncementAdapter mAdapter;

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,SchoolAnnouncementActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_school_announcement;
    }

    @Override
    public void init() {
        setTitle("校园公告/活动");
        initRecycleView();
    }

    public void initRecycleView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        school_announcement_list.setLayoutManager(linearLayoutManager);

        mAdapter = new SchoolAnnouncementAdapter(getData(),mContext);
        school_announcement_list.setAdapter(mAdapter);
    }

    public ArrayList<String> getData(){
        ArrayList<String> data = new ArrayList<>();
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        data.add("1");
        return data;
    }
}

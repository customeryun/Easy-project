package com.android.easymanager.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.ScheduleTaskAdapter;
import com.android.easymanager.ui.bean.ScheduleItem;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by PC-xiaoming on 2019/4/4.
 */

public class ScheduleFragment extends BaseFragment{

    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;

    public static ScheduleFragment getInstance(){
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout() {
        return R.layout.schedule_fragment_layout;
    }

    @Override
    public Object crateView() {
        return null;
    }

    @Override
    public Object createPresenter() {
        return null;
    }

    @Override
    public void init() {
        loadRecyclerView();
    }

    public void loadRecyclerView(){
        ScheduleTaskAdapter adapter = new ScheduleTaskAdapter(mContext,buildItems());
        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.setAdapter(adapter);
    }

    public ArrayList<ScheduleItem> buildItems(){
        ArrayList<ScheduleItem> items = new ArrayList<>();
        items.add(new ScheduleItem(R.drawable.ic_schedule_lession,"汉语言课程","8:10 - 12:10"));
        items.add(new ScheduleItem(R.drawable.ic_schedule_lession,"体育活动课程","8:10 - 12:10"));
        items.add(new ScheduleItem(R.drawable.ic_schedule_lession,"材料力学课程","8:10 - 12:10"));
        items.add(new ScheduleItem(R.drawable.ic_schedule_lession,"社团活动","8:10 - 12:10"));
        items.add(new ScheduleItem(R.drawable.ic_schedule_lession,"看电影","8:10 - 12:10"));
        items.add(new ScheduleItem(R.drawable.ic_schedule_lession,"翻译实践课程","8:10 - 12:10"));
        return items;
    }
}

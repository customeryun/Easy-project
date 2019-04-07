package com.android.easymanager.ui.fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.SchedulePopAdapter;
import com.android.easymanager.ui.adapter.ScheduleTaskAdapter;
import com.android.easymanager.ui.bean.ScheduleItem;
import com.example.zhouwei.library.CustomPopWindow;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PC-xiaoming on 2019/4/4.
 */

public class ScheduleFragment extends BaseFragment implements ScheduleTaskAdapter.RvOnItemListener{

    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;
    @BindView(R.id.schedule_add)
    FrameLayout schedule_add;

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
        ScheduleTaskAdapter adapter = new ScheduleTaskAdapter(mContext,buildItems(),this);
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

    @Override
    public void onItemClick(int position, ScheduleItem item) {
        if(position%2 == 0){
            buildS1Dialog();
        }else{
            buildS2Dialog();
        }
    }

    @OnClick({R.id.schedule_add})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.schedule_add:
                showPopWindow();
                break;
        }
    }

    public void buildS1Dialog(){
        View view = LayoutInflater.from(mActivity).inflate(R.layout.schedule_dialog_s1_layout,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(mActivity).setView(view).create();
        dialog.show();
    }

    public void buildS2Dialog(){
        View view = LayoutInflater.from(mActivity).inflate(R.layout.schedule_dialog_s2_layout,null,false);
        final AlertDialog dialog = new AlertDialog.Builder(mActivity).setView(view).create();
        dialog.show();
    }

    public void showPopWindow(){
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.schedule_pop_add_layout,null);
        //处理popWindow 显示内容
        buildPopWindow(contentView);
        //创建并显示popWindow
        CustomPopWindow mListPopWindow= new CustomPopWindow.PopupWindowBuilder(mActivity)
                .setView(contentView)
                .size(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create()
                .showAtLocation(contentView, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
                //.showAsDropDown(schedule_add,0,0);
    }

    public void buildPopWindow(View contentView){
        RecyclerView recyclerView = (RecyclerView) contentView.findViewById(R.id.pop_recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        SchedulePopAdapter adapter = new SchedulePopAdapter(mContext,buildPopItems());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public ArrayList<String> buildPopItems(){
        ArrayList<String> data = new ArrayList<>();
        data.add("ss");
        data.add("ss");
        data.add("ss");
        data.add("ss");
        data.add("ss");
        data.add("ss");
        data.add("ss");
        return data;
    }
}

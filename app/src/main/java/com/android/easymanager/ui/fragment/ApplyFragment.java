package com.android.easymanager.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.easymanager.R;
import com.android.easymanager.ui.activity.ApprovalActivity;
import com.android.easymanager.ui.activity.StudentLeaveActivity;
import com.android.easymanager.ui.activity.StudentManagerActivity;
import com.android.easymanager.ui.adapter.ApplyAdapter;
import com.android.easymanager.ui.bean.ApplyChildEntry;
import com.android.easymanager.ui.bean.ExpandGroupEntry;
import com.donkingliang.groupedadapter.adapter.GroupedRecyclerViewAdapter;
import com.donkingliang.groupedadapter.holder.BaseViewHolder;
import com.donkingliang.groupedadapter.layoutmanger.GroupedGridLayoutManager;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by PC-xiaoming on 2019/4/5.
 */

public class ApplyFragment extends BaseFragment {

    private static final String TAG = "ApplyFragment";

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    public static ApplyFragment getInstance() {
        ApplyFragment fragment = new ApplyFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout() {
        return R.layout.apply_fragment_layout;
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
        initRecycleView();
    }

    public void initRecycleView() {
        ApplyAdapter adapter = new ApplyAdapter(mContext, getExpandableGroups(6));
        adapter.setOnHeaderClickListener(new GroupedRecyclerViewAdapter.OnHeaderClickListener() {
            @Override
            public void onHeaderClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                      int groupPosition) {
                ApplyAdapter expandableAdapter = (ApplyAdapter) adapter;
                if (expandableAdapter.isExpand(groupPosition)) {
                    expandableAdapter.collapseGroup(groupPosition);
                } else {
                    expandableAdapter.expandGroup(groupPosition);
                }
            }
        });
        adapter.setOnChildClickListener(new GroupedRecyclerViewAdapter.OnChildClickListener() {
            @Override
            public void onChildClick(GroupedRecyclerViewAdapter adapter, BaseViewHolder holder,
                                     int groupPosition, int childPosition) {
                Toast.makeText(mContext, "子项：groupPosition = " + groupPosition
                                + ", childPosition = " + childPosition,
                        Toast.LENGTH_LONG).show();
                if (childPosition == 4) {
                    StudentLeaveActivity.launchActivity(mContext);
                } else if (childPosition == 1) {
                    ApprovalActivity.launchActivity(mContext);
                } else {
                    StudentManagerActivity.launchActivity(mContext);

                }

            }
        });
        rv_list.setAdapter(adapter);

        GroupedGridLayoutManager gridLayoutManager = new GroupedGridLayoutManager(mContext, 4, adapter);
        rv_list.setLayoutManager(gridLayoutManager);
    }

    private ArrayList<ExpandGroupEntry> getExpandableGroups(int group) {
        ArrayList<ExpandGroupEntry> groupEntries = new ArrayList<>();
        for (int i = 0; i < group; i++) {
            Log.d(TAG, "第" + i + "组");
            if (i == 0) {
                ArrayList<ApplyChildEntry> childEntries = new ArrayList<>();
                ApplyChildEntry childEntry1 = new ApplyChildEntry("名单\n管理");
                ApplyChildEntry childEntry2 = new ApplyChildEntry("课程\n表");
                ApplyChildEntry childEntry3 = new ApplyChildEntry("考试\n安排");
                ApplyChildEntry childEntry4 = new ApplyChildEntry("成绩");
                ApplyChildEntry childEntry5 = new ApplyChildEntry("请假");
                childEntries.add(childEntry1);
                childEntries.add(childEntry2);
                childEntries.add(childEntry3);
                childEntries.add(childEntry4);
                childEntries.add(childEntry5);
                ExpandGroupEntry expandGroupEntry = new ExpandGroupEntry("学习", "", true, childEntries);
                groupEntries.add(expandGroupEntry);
            } else if (i == 1) {
                ArrayList<ApplyChildEntry> childEntries = new ArrayList<>();
                ApplyChildEntry childEntry1 = new ApplyChildEntry("定位\n打卡");
                ApplyChildEntry childEntry2 = new ApplyChildEntry("社团");
                ApplyChildEntry childEntry3 = new ApplyChildEntry("活动\n预约");
                ApplyChildEntry childEntry4 = new ApplyChildEntry("社会\n实践");
                childEntries.add(childEntry1);
                childEntries.add(childEntry2);
                childEntries.add(childEntry3);
                childEntries.add(childEntry4);
                ExpandGroupEntry expandGroupEntry = new ExpandGroupEntry("活动", "", true, childEntries);
                groupEntries.add(expandGroupEntry);
            } else if (i == 2) {
                ArrayList<ApplyChildEntry> childEntries = new ArrayList<>();
                ApplyChildEntry childEntry1 = new ApplyChildEntry("选课");
                ApplyChildEntry childEntry2 = new ApplyChildEntry("我的\n课程");
                childEntries.add(childEntry1);
                childEntries.add(childEntry2);
                ExpandGroupEntry expandGroupEntry = new ExpandGroupEntry("教学", "", true, childEntries);
                groupEntries.add(expandGroupEntry);
            } else if (i == 3) {
                ArrayList<ApplyChildEntry> childEntries = new ArrayList<>();
                ApplyChildEntry childEntry1 = new ApplyChildEntry("选宿\n舍");
                ApplyChildEntry childEntry2 = new ApplyChildEntry("房间\n维护");
                childEntries.add(childEntry1);
                childEntries.add(childEntry2);
                ExpandGroupEntry expandGroupEntry = new ExpandGroupEntry("生活区", "", true, childEntries);
                groupEntries.add(expandGroupEntry);
            } else if (i == 4) {
                ArrayList<ApplyChildEntry> childEntries = new ArrayList<>();
                ApplyChildEntry childEntry1 = new ApplyChildEntry("校生\n报道");
                ApplyChildEntry childEntry2 = new ApplyChildEntry("新生\n报道");
                ApplyChildEntry childEntry3 = new ApplyChildEntry("报道\n统计");
                childEntries.add(childEntry1);
                childEntries.add(childEntry2);
                childEntries.add(childEntry3);
                ExpandGroupEntry expandGroupEntry = new ExpandGroupEntry("报道", "", true, childEntries);
                groupEntries.add(expandGroupEntry);
            } else if (i == 5) {
                ArrayList<ApplyChildEntry> childEntries = new ArrayList<>();
                ApplyChildEntry childEntry1 = new ApplyChildEntry("审批");
                ApplyChildEntry childEntry2 = new ApplyChildEntry("采购");
                ApplyChildEntry childEntry3 = new ApplyChildEntry("卡包");
                childEntries.add(childEntry1);
                childEntries.add(childEntry2);
                childEntries.add(childEntry3);
                ExpandGroupEntry expandGroupEntry = new ExpandGroupEntry("其他应用", "", true, childEntries);
                groupEntries.add(expandGroupEntry);
            }
        }
        return groupEntries;
    }
}

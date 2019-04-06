package com.android.easymanager.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.MeApprovalAdapter;
import com.android.easymanager.ui.bean.MeApprovalChildEntry;
import com.android.easymanager.ui.bean.MeApprovalGroupEntry;

import java.nio.file.attribute.DosFileAttributeView;
import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by PC-xiaoming on 2019/4/6.
 */

public class MeApprovalFragment extends BaseFragment {

    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    public static MeApprovalFragment getInstance(){
        MeApprovalFragment fragment = new MeApprovalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout() {
        return R.layout.me_approval_fragment_layout;
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
        MeApprovalAdapter approvalAdapter = new MeApprovalAdapter(mContext, buildItems());
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));
        rv_list.setAdapter(approvalAdapter);
    }

    public ArrayList<MeApprovalGroupEntry> buildItems() {
        ArrayList<MeApprovalGroupEntry> entries = new ArrayList<>();
        ArrayList<MeApprovalChildEntry> childEntries = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            childEntries.add(new MeApprovalChildEntry());
        }
        MeApprovalGroupEntry groupEntry = new MeApprovalGroupEntry("本月(6)", "", childEntries, false);
        entries.add(groupEntry);
        return entries;
    }

}

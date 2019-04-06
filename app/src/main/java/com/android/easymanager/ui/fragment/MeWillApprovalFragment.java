package com.android.easymanager.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.MeWillApprovalAdapter;
import com.android.easymanager.ui.bean.MeApprovalChildEntry;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by PC-xiaoming on 2019/4/6.
 */

public class MeWillApprovalFragment extends BaseFragment{

    @BindView(R.id.search)
    ImageView search;
    @BindView(R.id.filter)
    ImageView filter;
    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;

    public static MeWillApprovalFragment getInstance(){
        MeWillApprovalFragment fragment = new MeWillApprovalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout() {
        return R.layout.me_will_approval_fragment_layout;
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
        initRecyclerView();
    }

    public void initRecyclerView(){
        MeWillApprovalAdapter approvalAdapter = new MeWillApprovalAdapter(mContext,buildItems());
        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.addItemDecoration(new DividerItemDecoration(mContext,DividerItemDecoration.HORIZONTAL));
        recycle_view.setAdapter(approvalAdapter);
    }

    public ArrayList<MeApprovalChildEntry> buildItems(){
        ArrayList<MeApprovalChildEntry> childEntries = new ArrayList<>();
        for (int i=0;i<6;i++){
            childEntries.add(new MeApprovalChildEntry());
        }
        return childEntries;
    }
}

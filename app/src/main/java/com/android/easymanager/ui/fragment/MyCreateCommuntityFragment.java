package com.android.easymanager.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.MyCreateCommAdapter;
import com.android.easymanager.ui.bean.CommunitityBean;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;

public class MyCreateCommuntityFragment extends BaseFragment{


    @BindView(R.id.rv_list)
    RecyclerView rv_list;

    public static MyCreateCommuntityFragment getInstance(){
        MyCreateCommuntityFragment fragment = new MyCreateCommuntityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout() {
        return R.layout.activity_listview_only_layout_;
    }

    @Override
    public void init() {
        initRecycleView();
    }

    @Override
    public Object crateView() {
        return null;
    }

    @Override
    public Object createPresenter() {
        return null;
    }

    public void initRecycleView() {
        MyCreateCommAdapter mAdapter = new MyCreateCommAdapter();
        mAdapter.addDatas(buildItems());
        rv_list.setLayoutManager(new LinearLayoutManager(mContext));
        rv_list.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.HORIZONTAL));
        rv_list.setAdapter(mAdapter);
    }

    public List<CommunitityBean> buildItems() {
        List<CommunitityBean> entries = new ArrayList<>();
        entries.add(new CommunitityBean());
        entries.add(new CommunitityBean());
        entries.add(new CommunitityBean());
        return entries;
    }
}

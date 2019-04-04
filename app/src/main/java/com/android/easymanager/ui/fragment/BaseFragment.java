package com.android.easymanager.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by PC-xiaoming on 2019/4/4.
 */

public abstract class BaseFragment<BaseView,BasePresenter> extends Fragment{

    private int mLayoutRes;
    protected Context mContext;
    protected Activity mActivity;
    protected BasePresenter mPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutRes = initLayout();
        View view = inflater.inflate(mLayoutRes, container, false);
        mPresenter = createPresenter();
        this.mActivity = getActivity();
        this.mContext = getActivity().getApplicationContext();
        ButterKnife.bind(this,view);
        init();
        return view;
    }

    public abstract int initLayout();

    public abstract BaseView crateView();

    public abstract BasePresenter createPresenter();

    public abstract void init();

}

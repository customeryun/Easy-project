package com.android.easymanager.presenter;

import android.content.Context;
import android.view.View;

import com.android.easymanager.contract.HomeContract;
import com.android.easymanager.model.HomeLoadModel;
import com.android.easymanager.model.HomeModelImpl;

/**
 * Created by PC-xiaoming on 2019/4/4.
 */

public class HomePresenter implements HomeContract.Presenter{

    private HomeLoadModel mHomeLoadModel;
    private Context mContext;
    private HomeContract.View mView;

    public HomePresenter(HomeContract.View view,Context context){
        this.mHomeLoadModel = new HomeModelImpl();
        this.mView = view;
        this.mContext = context;
    }

    @Override
    public void start(int type) {
        mView.showLoad();
        mHomeLoadModel.load(new OnLoadListener() {
            @Override
            public void onSuccess(Object success) {
                mView.hideLoad();
                mView.onSuccess();
            }

            @Override
            public void onError(String state, String message) {
                mView.onError();
            }

            @Override
            public void networkError() {
                mView.networkError();
            }
        });
    }

    @Override
    public void showEdit() {
        mView.updateEditLayout(View.VISIBLE);
    }

    @Override
    public void hideEdit() {
        mView.updateEditLayout(View.GONE);
    }

    @Override
    public void start() {

    }
}

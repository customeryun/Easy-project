package com.android.easymanager.contract;

import com.android.easymanager.base.BasePresenter;
import com.android.easymanager.base.BaseView;

/**
 * Created by PC-xiaoming on 2019/4/3.
 */

public interface HomeContract {

    interface View extends BaseView{
     //定义view交互接口
     void loadData();

     void networkError();

     void onError();

     void onSuccess();

    }

    interface  Presenter extends BasePresenter{
        //定义访问逻辑
        void start(int type);
    }
}

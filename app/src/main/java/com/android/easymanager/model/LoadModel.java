package com.android.easymanager.model;

import com.android.easymanager.presenter.OnLoadListener;

/**
 * Created by PC-xiaoming on 2019/4/4.
 */

public interface LoadModel {
    //加载数据回调接口
    void load(OnLoadListener listener);
}

package com.android.easymanager.model;

import android.content.Context;

import com.android.easymanager.presenter.OnLoadListener;

/**
 * Created by PC-xiaoming on 2019/4/4.
 */

public interface HomeLoadModel extends LoadModel{

    /**
     * 更具类型加载不同数据
     */
    void load(OnLoadListener loadListener, Context context,int type);
}

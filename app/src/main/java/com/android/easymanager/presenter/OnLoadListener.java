package com.android.easymanager.presenter;

/**
 * Created by PC-xiaoming on 2019/4/3.
 */

public interface OnLoadListener<T> {
    /**
     * 成功时回调消息
     */
    void onSuccess(T success);

    /**
     * 失败时回调消息
     */
    void onError(String state,String message);

    /**
     * 网络异常
     */
    void networkError();
}

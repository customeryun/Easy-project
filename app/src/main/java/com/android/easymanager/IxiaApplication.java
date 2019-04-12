package com.android.easymanager;

import android.app.Application;

import com.android.easymanager.ui.receiver.NotificationClickEventReceiver;

import cn.jpush.im.android.api.JMessageClient;

public class IxiaApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化极光推送
        JMessageClient.init(getApplicationContext(),true);
        JMessageClient.setDebugMode(true);
        //设置Notification的模式
        JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_WITH_SOUND | JMessageClient.FLAG_NOTIFY_WITH_LED | JMessageClient.FLAG_NOTIFY_WITH_VIBRATE);
        //注册Notification点击的接收器
        //new NotificationClickEventReceiver(getApplicationContext());
    }


}

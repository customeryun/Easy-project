package com.android.easymanager;

import android.content.Context;
import com.android.easymanager.ui.receiver.NotificationClickEventReceiver;
import com.android.easymanager.utils.SharePreferenceManager;
import cn.jpush.im.android.api.JMessageClient;

public class IxiaApplication extends com.activeandroid.app.Application{

    public static Context context;
    public static long registerOrLogin = 1;
    private static final String JCHAT_CONFIGS = "JChat_configs";

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        //初始化极光推送
        JMessageClient.init(getApplicationContext(),true);
        JMessageClient.setDebugMode(true);
        SharePreferenceManager.init(getApplicationContext(), JCHAT_CONFIGS);
        //设置Notification的模式
        JMessageClient.setNotificationFlag(JMessageClient.FLAG_NOTIFY_WITH_SOUND | JMessageClient.FLAG_NOTIFY_WITH_LED | JMessageClient.FLAG_NOTIFY_WITH_VIBRATE);
        //注册Notification点击的接收器
        //new NotificationClickEventReceiver(getApplicationContext());
    }


}

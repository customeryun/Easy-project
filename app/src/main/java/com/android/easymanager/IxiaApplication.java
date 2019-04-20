package com.android.easymanager;

import android.content.Context;
import com.android.easymanager.database.UserEntry;
import com.android.easymanager.ui.receiver.NotificationClickEventReceiver;
import com.android.easymanager.utils.SharePreferenceManager;
import java.util.ArrayList;
import java.util.List;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;

public class IxiaApplication extends com.activeandroid.app.Application{
    public static final String CONV_TITLE = "conv_title";

    public static Context context;
    public static long registerOrLogin = 1;
    private static final String JCHAT_CONFIGS = "JChat_configs";

    public static final int RESULT_BUTTON = 2;

    public static List<UserInfo> mFriendInfoList = new ArrayList<>();
    public static List<String> forAddFriend = new ArrayList<>();

    public static final int RESULT_CODE_FRIEND_INFO = 17;
    public static final int RESULT_CODE_EDIT_NOTENAME = 29;
    public static final String NOTENAME = "notename";
    public static final String GROUP_ID = "groupId";
    public static final String TARGET_ID = "targetId";
    public static final String TARGET_APP_KEY = "targetAppKey";

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

    public static UserEntry getUserEntry() {
        return UserEntry.getUser(JMessageClient.getMyInfo().getUserName(), JMessageClient.getMyInfo().getAppKey());
    }

}

package com.android.easymanager;

import android.content.Context;
import com.android.easymanager.database.UserEntry;
import com.android.easymanager.ui.receiver.NotificationClickEventReceiver;
import com.android.easymanager.utils.SharePreferenceManager;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import cn.jpush.im.android.api.model.Message;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import me.shaohui.shareutil.ShareConfig;
import me.shaohui.shareutil.ShareManager;

public class IxiaApplication extends com.activeandroid.app.Application{
    public static final String CONV_TITLE = "conv_title";

    public static final int IMAGE_MESSAGE = 1;
    public static final int TAKE_PHOTO_MESSAGE = 2;
    public static final int TAKE_LOCATION = 3;
    public static final int FILE_MESSAGE = 4;
    public static final int TACK_VIDEO = 5;
    public static final int TACK_VOICE = 6;
    public static final int BUSINESS_CARD = 7;
//    public static final int REQUEST_CODE_SEND_FILE = 26;

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
    public static final String DRAFT = "draft";

    public static Map<Long, Boolean> isAtMe = new HashMap<>();
    public static Map<Long, Boolean> isAtall = new HashMap<>();
    public static List<Message> ids = new ArrayList<>();

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
        initImageLoader();

        // init
        ShareConfig config = ShareConfig.instance()
                .qqId("QQ_ID")
                .wxId("WX_ID")
                .weiboId("WEIBO_ID")
                // 下面两个，如果不需要登录功能，可不填写
                .weiboRedirectUrl("REDIRECT_URL")
                .wxSecret("WX_ID");
        ShareManager.init(config);
    }

    public static UserEntry getUserEntry() {
        return UserEntry.getUser(JMessageClient.getMyInfo().getUserName(), JMessageClient.getMyInfo().getAppKey());
    }

    private void initImageLoader() {
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);

    }
}

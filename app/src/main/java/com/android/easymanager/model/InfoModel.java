package com.android.easymanager.model;

import android.graphics.Bitmap;
import java.io.File;
import cn.jpush.im.android.api.model.UserInfo;

public class InfoModel {
    public UserInfo friendInfo;
    public Bitmap bitmap;

    private static InfoModel mInfoModel = new InfoModel();

    public static InfoModel getInstance() {
        return mInfoModel;
    }

    public Long getUid () {
        return friendInfo.getUserID();
    }

    public void setBitmap(Bitmap avatar) {
        bitmap = avatar;
    }

    public Bitmap getAvatar() {
        return bitmap;
    }

    public boolean isFriend() {
        return friendInfo.isFriend();
    }

    public String getUserName() {
        return friendInfo.getUserName();
    }

    public String getAvatarPath() {
        File avatarFile = friendInfo.getAvatarFile();
        if (avatarFile != null) {
            return avatarFile.getPath();
        }
        return null;
    }

    public String getAppKey() {
        return friendInfo.getAppKey();
    }

    public String getCity() {
        return friendInfo.getRegion();
    }

}

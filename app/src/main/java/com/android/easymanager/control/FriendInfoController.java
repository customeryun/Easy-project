package com.android.easymanager.control;

import android.content.Intent;
import android.view.View;
import com.android.easymanager.R;
import com.android.easymanager.ui.activity.FriendInfoActivity;
import com.android.easymanager.ui.activity.FriendSettingActivity;
import com.android.easymanager.view.FriendInfoView;
import cn.jpush.im.android.api.model.UserInfo;

public class FriendInfoController implements View.OnClickListener {
    private FriendInfoActivity mContext;
    private UserInfo friendInfo;

    public FriendInfoController(FriendInfoView friendInfoView, FriendInfoActivity context) {
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_goToChat:
                mContext.startChatActivity();
                break;
            case R.id.iv_friendPhoto:
//                mContext.startBrowserAvatar();
                break;
            case R.id.jmui_commit_btn:
                Intent intent = new Intent(mContext, FriendSettingActivity.class);
                if(friendInfo == null) return;
                intent.putExtra("userName", friendInfo.getUserName());
                intent.putExtra("noteName", friendInfo.getNotename());
                mContext.startActivity(intent);
                break;
            case R.id.return_btn:
                mContext.finish();
                break;
            default:
                break;
        }
    }

    public void setFriendInfo(UserInfo info) {
        friendInfo = info;
    }

}

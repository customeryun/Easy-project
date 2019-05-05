package com.android.easymanager.ui.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.activeandroid.query.Update;
import com.android.easymanager.IxiaApplication;
import com.android.easymanager.R;
import com.android.easymanager.control.FriendInfoController;
import com.android.easymanager.database.FriendEntry;
import com.android.easymanager.ui.bean.Event;
import cn.jpush.im.android.eventbus.EventBus;
import com.android.easymanager.ui.bean.EventType;
import com.android.easymanager.utils.DialogCreator;
import com.android.easymanager.utils.ToastUtil;
import com.android.easymanager.view.FriendInfoView;
import butterknife.BindView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.Conversation;
import cn.jpush.im.android.api.model.GroupInfo;
import cn.jpush.im.android.api.model.UserInfo;

public class FriendInfoActivity extends /*Base*/Activity {
    private String mTargetId;
    private String mTargetAppKey;
    private boolean mIsFromContact;
    private UserInfo mUserInfo;
    private FriendInfoController mFriendInfoController;
    private long mGroupId;
    private String mTitle;
    private boolean mIsGetAvatar = false;
    private boolean mIsAddFriend = false;
    private boolean mIsFromSearch = false;
    private boolean mFromGroup = false;
    private String mUserID;
    FriendInfoView mFriendInfoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_info);
        mFriendInfoView = (FriendInfoView) findViewById(R.id.friend_info_view);
        mTargetId = getIntent().getStringExtra(IxiaApplication.TARGET_ID);
        mTargetAppKey = getIntent().getStringExtra(IxiaApplication.TARGET_APP_KEY);
        mUserID = getIntent().getStringExtra("targetId");
        if (mTargetAppKey == null) {
            mTargetAppKey = JMessageClient.getMyInfo().getAppKey();
        }
        mFriendInfoView.initModel(this);
        mFriendInfoController = new FriendInfoController(mFriendInfoView, this);
        mFriendInfoView.setListeners(mFriendInfoController);
        mFriendInfoView.setOnChangeListener(mFriendInfoController);
        mIsFromContact = getIntent().getBooleanExtra("fromContact", false);
        mIsFromSearch = getIntent().getBooleanExtra("fromSearch", false);
        mIsAddFriend = getIntent().getBooleanExtra("addFriend", false);
        mFromGroup = getIntent().getBooleanExtra("group_grid", false);

        //从通讯录中点击过来
        if (mIsFromContact || mIsFromSearch || mFromGroup || mIsAddFriend) {
            updateUserInfo();
        } else {
            mGroupId = getIntent().getLongExtra(IxiaApplication.GROUP_ID, 0);
            Conversation conv;
            if (mGroupId == 0) {
                conv = JMessageClient.getSingleConversation(mTargetId, mTargetAppKey);
                mUserInfo = (UserInfo) conv.getTargetInfo();
            } else {
                conv = JMessageClient.getGroupConversation(mGroupId);
                GroupInfo groupInfo = (GroupInfo) conv.getTargetInfo();
                mUserInfo = groupInfo.getGroupMemberInfo(mTargetId, mTargetAppKey);
            }

            //先从Conversation里获得UserInfo展示出来
            mFriendInfoView.initInfo(mUserInfo);
            updateUserInfo();
        }
    }

    private void updateUserInfo() {
        final Dialog dialog = DialogCreator.createLoadingDialog(FriendInfoActivity.this,
                FriendInfoActivity.this.getString(R.string.jmui_loading));
        dialog.show();
        if (TextUtils.isEmpty(mTargetId) && !TextUtils.isEmpty(mUserID)) {
            mTargetId = mUserID;
        }
        JMessageClient.getUserInfo(mTargetId, mTargetAppKey, new GetUserInfoCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                dialog.dismiss();
                if (responseCode == 0) {
                    //拉取好友信息时候要更新数据库中的nickName.因为如果对方修改了nickName我们是无法感知的.如果不在拉取信息
                    //时候更新数据库的话会影响到搜索好友的nickName, 注意要在没有备注名并且有昵称时候去更新.因为备注名优先级更高
//                    new Update(FriendEntry.class).set("DisplayName=?", info.getDisplayName()).where("Username=?", mTargetId).execute();
//                    new Update(FriendEntry.class).set("NickName=?", info.getNickname()).where("Username=?", mTargetId).execute();
//                    new Update(FriendEntry.class).set("NoteName=?", info.getNotename()).where("Username=?", mTargetId).execute();

                    if (info.getAvatarFile() != null) {
                        new Update(FriendEntry.class).set("Avatar=?", info.getAvatarFile().getAbsolutePath()).where("Username=?", mTargetId).execute();
                    }
                    mUserInfo = info;
                    mFriendInfoController.setFriendInfo(info);
//                    mTitle = info.getNotename();
//                    if (TextUtils.isEmpty(mTitle)) {
//                        mTitle = info.getNickname();
//                    }
                    mTitle = info.getUserName();
                    mFriendInfoView.initInfo(info);
                } else {
                   // HandleResponseCode.onHandle(FriendInfoActivity.this, responseCode, false);
                    ToastUtil.shortToast(FriendInfoActivity.this,"updateUserInfo 失败：responseCode=="+responseCode+"**responseMessage=="+responseMessage);
                }
            }
        });
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public String getUserName() {
        return mUserInfo.getUserName();
    }

    public String getTargetAppKey() {
        return mTargetAppKey;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_CANCELED) {
            return;
        }
        if (resultCode == IxiaApplication.RESULT_CODE_EDIT_NOTENAME) {
            mTitle = data.getStringExtra(IxiaApplication.NOTENAME);
            FriendEntry friend = FriendEntry.getFriend(IxiaApplication.getUserEntry(), mTargetId, mTargetAppKey);
            if (null != friend) {
                friend.displayName = mTitle;
                friend.save();
            }
        }
    }

    //将获得的最新的昵称返回到聊天界面
    @Override
    public void onBackPressed() {
//        Intent intent = new Intent();
//        intent.putExtra(IxiaApplication.CONV_TITLE, mTitle);
//        setResult(IxiaApplication.RESULT_CODE_FRIEND_INFO, intent);
        finish();
        super.onBackPressed();
    }

    public void startChatActivity() {
        if (mIsFromContact || mIsAddFriend || mIsFromSearch || mFromGroup) {
            Intent intent = new Intent(this, ChatActivity.class);
            if(mUserInfo == null) return;
            String title = mUserInfo.getNotename();
            if (TextUtils.isEmpty(title)) {
                title = mUserInfo.getNickname();
                if (TextUtils.isEmpty(title)) {
                    title = mUserInfo.getUserName();
                }
            }
            intent.putExtra(IxiaApplication.CONV_TITLE, title);
            intent.putExtra(IxiaApplication.TARGET_ID, mUserInfo.getUserName());
            intent.putExtra(IxiaApplication.TARGET_APP_KEY, mUserInfo.getAppKey());
            startActivity(intent);
        } else {
            if (mGroupId != 0) {
                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(IxiaApplication.TARGET_ID, mTargetId);
                intent.putExtra(IxiaApplication.TARGET_APP_KEY, mTargetAppKey);
                intent.setClass(this, ChatActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent();
                intent.putExtra("returnChatActivity", true);
                intent.putExtra(IxiaApplication.CONV_TITLE, mTitle);
                setResult(IxiaApplication.RESULT_CODE_FRIEND_INFO, intent);
            }
        }
        Conversation conv = JMessageClient.getSingleConversation(mTargetId, mTargetAppKey);
        //如果会话为空，使用EventBus通知会话列表添加新会话
        if (conv == null) {
            conv = Conversation.createSingleConversation(mTargetId, mTargetAppKey);
            EventBus.getDefault().post(new Event.Builder()
                    .setType(EventType.createConversation)
                    .setConversation(conv)
                    .build());
        }
        finish();

    }

}

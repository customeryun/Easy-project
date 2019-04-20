package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.android.easymanager.IxiaApplication;
import com.android.easymanager.R;
import com.android.easymanager.control.ContactsController;
import com.android.easymanager.database.FriendInvitation;
import com.android.easymanager.database.FriendRecommendEntry;
import com.android.easymanager.database.UserEntry;
import com.android.easymanager.ui.widget.contact.DividerItemDecoration;
import com.android.easymanager.ui.widget.contact.LetterView;
import butterknife.BindView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.event.ContactNotifyEvent;
import cn.jpush.im.android.api.model.UserInfo;

public class ContactActivity extends BaseActivity {

    @BindView(R.id.contact_list)
    RecyclerView contactList;
    @BindView(R.id.letter_view)
    LetterView letterView;

    private LinearLayoutManager layoutManager;
    private ContactsController mContactsController;

    @Override
    public int getLayout() {
        return R.layout.contact_manager_layout;
    }

    @Override
    public void init() {
        setTitle("校园通讯录");
        setAddIconVisible(true);
        setAddIconRes(android.R.drawable.ic_menu_add);
        setAddIconListener(mAddOnClickListener);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        layoutManager = new LinearLayoutManager(this);
        contactList.setLayoutManager(layoutManager);
        contactList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mContactsController = new ContactsController(contactList,mContext);
        mContactsController.initContacts();

        letterView.setCharacterListener(new LetterView.CharacterClickListener() {
            @Override
            public void clickCharacter(String character) {
                int position = mContactsController.getScrollPosition(character);
                Log.i("linmei","***scroll**character=="+character+"***position=="+position);
                layoutManager.scrollToPositionWithOffset(position, 0);
            }

            @Override
            public void clickArrow() {
                layoutManager.scrollToPositionWithOffset(0, 0);
            }
        });
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,ContactActivity.class);
        context.startActivity(intent);
    }

    View.OnClickListener mAddOnClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            ContactAddActivity.launchActivity(mContext);
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        mContactsController.refreshContact();
    }

    @Override
    public void onDestroy() {
        JMessageClient.unRegisterEventReceiver(this);//注销消息接收
        super.onDestroy();
    }

    public void onEvent(ContactNotifyEvent event) {
        Log.i("linmei","***ContactNotifyEvent**event.getType()=="+event.getType());
        final UserEntry user = IxiaApplication.getUserEntry();
        final String reason = event.getReason();
        final String username = event.getFromUsername();
        final String appKey = event.getfromUserAppKey();
        if (event.getType() == ContactNotifyEvent.Type.invite_received) {
            //如果同一个人申请多次,则只会出现一次;当点击进验证消息界面后,同一个人再次申请则可以收到
            if (IxiaApplication.forAddFriend.size() > 0) {
                for (String forAdd : IxiaApplication.forAddFriend) {
                    if (forAdd.equals(username)) {
                        return;
                    } else {
                        IxiaApplication.forAddFriend.add(username);
                    }
                }
            } else {
                IxiaApplication.forAddFriend.add(username);
            }
            JMessageClient.getUserInfo(username, appKey, new GetUserInfoCallback() {
                @Override
                public void gotResult(int status, String desc, UserInfo userInfo) {
                    if (status == 0) {
                        String name = userInfo.getUserName();
                        FriendRecommendEntry entry = FriendRecommendEntry.getEntry(user, username, appKey);
                        if (null == entry) {
                            if (null != userInfo.getAvatar()) {
                                String path = userInfo.getAvatarFile().getPath();
                                entry = new FriendRecommendEntry(userInfo.getUserID(), username, userInfo.getNotename(), userInfo.getNickname(), appKey, path,
                                        name, reason, FriendInvitation.INVITED.getValue(), user, 0);
                            } else {
                                entry = new FriendRecommendEntry(userInfo.getUserID(), username, userInfo.getNotename(), userInfo.getNickname(), appKey, null,
                                        username, reason, FriendInvitation.INVITED.getValue(), user, 0);
                            }
                        } else {
                            entry.state = FriendInvitation.INVITED.getValue();
                            entry.reason = reason;
                        }
                        entry.save();
                        //收到好友请求数字 +1
                        //int showNum = SharePreferenceManager.getCachedNewFriendNum() + 1;
                        //mContactsView.showNewFriends(showNum);
                        //SharePreferenceManager.setCachedNewFriendNum(showNum);
                    }
                }
            });
        }
    }

}

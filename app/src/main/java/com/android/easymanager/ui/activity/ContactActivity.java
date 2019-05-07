package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import com.android.easymanager.model.Constant;
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

    PopupWindow mMenuPopWindow;

    @Override
    public int getLayout() {
        return R.layout.contact_manager_layout;
    }

    @Override
    public void init() {
        setTitle("我的好友");
        setAddIconVisible(true);
        setAddIconRes(R.drawable.ic_meau_add_friend);
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
//                Log.i("linmei","***scroll**character=="+character+"***position=="+position);
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
            //ContactAddActivity.launchActivity(mContext);
            showPopWindow();
        }
    };

    public void showPopWindow() {
        View mMenuView = getLayoutInflater().inflate(R.layout.drop_down_menu, null);
        LinearLayout menuItem_add_friend = (LinearLayout)mMenuView.findViewById(R.id.menuitem_add_friend);
        LinearLayout menuItem_scan = (LinearLayout)mMenuView.findViewById(R.id.menuitem_scan);
        mMenuPopWindow = new PopupWindow(mMenuView, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        mMenuPopWindow.setTouchable(true);
        mMenuPopWindow.setOutsideTouchable(true);
        mMenuPopWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        if (mMenuPopWindow.isShowing()) {
            mMenuPopWindow.dismiss();
        } else {
            mMenuPopWindow.showAsDropDown(findViewById(R.id.iv_add), -200, 20);
        }

        menuItem_add_friend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactAddActivity.launchActivity(mContext);
            }
        });

        menuItem_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//扫描二维码
                Intent intent = new Intent(mContext, CommonScanActivity.class);
                intent.putExtra(Constant.REQUEST_SCAN_MODE, Constant.REQUEST_SCAN_MODE_QRCODE_MODE);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mContactsController.refreshContact();

        if(mMenuPopWindow!=null && mMenuPopWindow.isShowing()){
            mMenuPopWindow.dismiss();
        }
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

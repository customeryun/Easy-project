package com.android.easymanager.control;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.activeandroid.ActiveAndroid;
import com.android.easymanager.IxiaApplication;
import com.android.easymanager.R;
import com.android.easymanager.database.FriendEntry;
import com.android.easymanager.database.UserEntry;
import com.android.easymanager.ui.activity.FriendRecommendActivity;
import com.android.easymanager.ui.activity.StudentContactGroupActivity;
import com.android.easymanager.ui.activity.TeacherGroupActivity;
import com.android.easymanager.ui.adapter.ContactListAdapter;
import com.android.easymanager.ui.widget.contact.ContactComparator;
import com.android.easymanager.ui.widget.contact.Utils;
import com.android.easymanager.ui.widget.settings.ItemView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoListCallback;
import cn.jpush.im.android.api.model.UserInfo;

public class ContactsController implements View.OnClickListener{

    private List<FriendEntry> mList = new ArrayList<>();
    private ContactListAdapter mAdapter;
    private List<FriendEntry> forDelete = new ArrayList<>();
    RecyclerView mContactsView;
    Context mContext;
    public ContactsController(RecyclerView mContactsView, Context context) {
        this.mContactsView = mContactsView;
        this.mContext = context;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.search_view://查找
//                intent.setClass(mContext, SearchContactsActivity.class);
//                mContext.startActivity(intent);
                break;
            case R.id.contact_new:
                FriendRecommendActivity.launchActivity(mContext);
                break;
            case R.id.contact_teacher:
                TeacherGroupActivity.launchActivity(mContext);
                break;
            case R.id.contact_student:
                StudentContactGroupActivity.launchActivity(mContext);
                break;
            default:
                break;
        }
    }

    public void initContacts() {
        final UserEntry user = UserEntry.getUser(JMessageClient.getMyInfo().getUserName(),
                JMessageClient.getMyInfo().getAppKey());
     //   mContactsView.showLoadingHeader();
        ContactManager.getFriendList(new GetUserInfoListCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, List<UserInfo> userInfoList) {
//                mContactsView.dismissLoadingHeader();
                Log.i("linmei","****responseCode=="+responseCode+"**responseMessage=="+responseMessage);
                if (responseCode == 0) {
                    if (userInfoList.size() != 0) {
//                        mContactsView.dismissLine();
                        ActiveAndroid.beginTransaction();
                        try {
                            for (UserInfo userInfo : userInfoList) {
                                String userName = userInfo.getUserName();
                                String letter;
                                if (!TextUtils.isEmpty(userName.trim())) {
                                    String sortString = Utils.getPingYin(userName).substring(0,1).toUpperCase();
                                    if (sortString.matches("[A-Z]")) {
                                        letter = sortString.toUpperCase();
                                    } else {
                                        letter = "#";
                                    }
                                } else {
                                    letter = "#";
                                }
                                //避免重复请求时导致数据重复A
                                FriendEntry friend = FriendEntry.getFriend(user, userInfo.getUserName(), userInfo.getAppKey());
                                if (null == friend) {
                                    if (TextUtils.isEmpty(userInfo.getAvatar())) {
                                        friend = new FriendEntry(userInfo.getUserID(), userInfo.getUserName(), userInfo.getNotename(), userInfo.getNickname(), userInfo.getAppKey(),
                                                null, userName, letter, user);
                                    } else {
                                        friend = new FriendEntry(userInfo.getUserID(), userInfo.getUserName(), userInfo.getNotename(), userInfo.getNickname(), userInfo.getAppKey(),
                                                userInfo.getAvatarFile().getAbsolutePath(), userName, letter, user);
                                    }
                                    friend.save();
                                    mList.add(friend);
                                }
                                forDelete.add(friend);
                            }
                            ActiveAndroid.setTransactionSuccessful();
                        } finally {
                            ActiveAndroid.endTransaction();
                        }
                    } else {
                        //mContactsView.showLine();
                    }
                    //其他端删除好友后,登陆时把数据库中的也删掉
                    List<FriendEntry> friends = IxiaApplication.getUserEntry().getFriends();
                    friends.removeAll(forDelete);
                    for (FriendEntry del : friends) {
                        del.delete();
                        mList.remove(del);
                    }
//                    Collections.sort(mList, new PinyinComparator());
                    Collections.sort(mList, new ContactComparator());

                    mAdapter = new ContactListAdapter(mContext);
                    mAdapter.addDatas(mList);
                    View layout = LayoutInflater.from(mContext).inflate(R.layout.contact_manager_head_layout,null);
                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    ((EditText)layout.findViewById(R.id.search_view)).setOnClickListener(ContactsController.this);
                    ((ItemView)layout.findViewById(R.id.contact_new)).setOnClickListener(ContactsController.this);
                    ((ItemView)layout.findViewById(R.id.contact_teacher)).setOnClickListener(ContactsController.this);
                    ((ItemView)layout.findViewById(R.id.contact_student)).setOnClickListener(ContactsController.this);
                    layout.setLayoutParams(params);
                    mAdapter.setHeaderView(layout);
                    mContactsView.setAdapter(mAdapter);
                }
            }
        });
    }

    public void refreshContact() {
        final UserEntry user = UserEntry.getUser(JMessageClient.getMyInfo().getUserName(),
                JMessageClient.getMyInfo().getAppKey());
        mList = user.getFriends();
        Collections.sort(mList, new ContactComparator());
        mAdapter = new ContactListAdapter(mContext);
        mAdapter.addDatas(mList);
        View layout = LayoutInflater.from(mContext).inflate(R.layout.contact_manager_head_layout,null);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        ((EditText)layout.findViewById(R.id.search_view)).setOnClickListener(ContactsController.this);
        ((ItemView)layout.findViewById(R.id.contact_new)).setOnClickListener(ContactsController.this);
        ((ItemView)layout.findViewById(R.id.contact_teacher)).setOnClickListener(ContactsController.this);
        ((ItemView)layout.findViewById(R.id.contact_student)).setOnClickListener(ContactsController.this);
        layout.setLayoutParams(params);
        mAdapter.setHeaderView(layout);
        mContactsView.setAdapter(mAdapter);
    }

    public int getScrollPosition(String letter){
        View mHeadView = mAdapter.getHeaderView();
        int position = mAdapter.getScrollPosition(letter);
        return (mHeadView == null || position == -1) ? position : position+1;
    }
}

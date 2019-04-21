package com.android.easymanager.ui.activity;

import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import com.android.easymanager.IxiaApplication;
import com.android.easymanager.R;
import com.android.easymanager.database.FriendEntry;
import com.android.easymanager.database.FriendRecommendEntry;
import com.android.easymanager.utils.DialogCreator;
import com.android.easymanager.utils.ToastUtil;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

public class FriendSettingActivity extends BaseActivity {

    @BindView(R.id.btn_deleteFriend)
    Button mBtn_deleteFriend;

    private Dialog mDialog;
    private UserInfo mFriendInfo;

    @Override
    public int getLayout() {
        return R.layout.activity_friend_setting;
    }

    @Override
    public void init() {
        setTitle("设置");

        final Dialog dialog = DialogCreator.createLoadingDialog(FriendSettingActivity.this,
                FriendSettingActivity.this.getString(R.string.jmui_loading));
        dialog.show();

        JMessageClient.getUserInfo(getIntent().getStringExtra("userName"), new GetUserInfoCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                dialog.dismiss();
                if (responseCode == 0) {
                    mFriendInfo = info;
                    if (info.isFriend()) {
                        mBtn_deleteFriend.setEnabled(true);
                    }else {
                        mBtn_deleteFriend.setEnabled(false);
                    }
                }
            }
        });

    }

    @OnClick({R.id.btn_deleteFriend})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_deleteFriend:
                View.OnClickListener listener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (v.getId()) {
                            case R.id.jmui_cancel_btn:
                                mDialog.dismiss();
                                break;
                            case R.id.jmui_commit_btn:
                                final Dialog dialog = DialogCreator.createLoadingDialog(FriendSettingActivity.this, getString(R.string.processing));
                                dialog.show();
                                mFriendInfo.removeFromFriendList(new BasicCallback() {
                                    @Override
                                    public void gotResult(int responseCode, String responseMessage) {
                                        dialog.dismiss();
                                        if (responseCode == 0) {
                                            //将好友删除时候还原黑名单设置
                                            List<String> name = new ArrayList<>();
                                            name.add(mFriendInfo.getUserName());
//                                            JMessageClient.delUsersFromBlacklist(name, null);

                                            FriendEntry friend = FriendEntry.getFriend(IxiaApplication.getUserEntry(),
                                                    mFriendInfo.getUserName(), mFriendInfo.getAppKey());
                                            if (friend != null) {
                                                friend.delete();
                                            }
                                            FriendRecommendEntry entry = FriendRecommendEntry
                                                    .getEntry(IxiaApplication.getUserEntry(),
                                                            mFriendInfo.getUserName(), mFriendInfo.getAppKey());
                                            if (entry != null) {
                                                entry.delete();
                                            }
                                            ToastUtil.shortToast(FriendSettingActivity.this, "移除好友");
                                            mDialog.dismiss();
//                                            delConvAndReturnMainActivity();
                                        } else {
                                            mDialog.dismiss();
                                            ToastUtil.shortToast(FriendSettingActivity.this, "移除失败");
                                        }
                                    }
                                });
                                break;
                        }
                    }
                };
                mDialog = DialogCreator.createBaseDialogWithTitle(this, getString(R.string.delete_friend_dialog_title), listener);
                DisplayMetrics dm = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(dm);
                int mWidth = dm.widthPixels;
                mDialog.getWindow().setLayout((int)(mWidth*0.8), WindowManager.LayoutParams.WRAP_CONTENT);
                mDialog.show();
                break;
            default:
                break;
        }
    }

    public void delConvAndReturnMainActivity() {
//        Conversation conversation = JMessageClient.getSingleConversation(mFriendInfo.getUserName(), mFriendInfo.getAppKey());
//        EventBus.getDefault().post(new Event.Builder().setType(EventType.deleteConversation)
//                .setConversation(conversation)
//                .build());
//        JMessageClient.deleteSingleConversation(mFriendInfo.getUserName(), mFriendInfo.getAppKey());
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
        finish();
    }

}

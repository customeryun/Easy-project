package com.android.easymanager.ui.activity;

import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.android.easymanager.IxiaApplication;
import com.android.easymanager.R;
import com.android.easymanager.utils.DialogCreator;
import butterknife.BindView;
import cn.jpush.im.android.api.ContactManager;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

public class SearchFriendDetailActivity extends BaseActivity {
    @BindView(R.id.tv_userName)
    TextView mTv_userName;
    @BindView(R.id.tv_additionalMsg)
    TextView mTv_additionalMsg;
    @BindView(R.id.btn_refusal)
    Button mBtn_refusal;
    @BindView(R.id.btn_agree)
    Button mBtn_agree;

    private String mUsername;
    private String mAppKey;
    private UserInfo mToUserInfo;

    @Override
    public int getLayout() {
        return R.layout.activity_search_result_detail;
    }

    @Override
    public void init() {
        setActionbarVisible(false);
        initModel();
    }

    private void initModel() {
        final Dialog dialog = DialogCreator.createLoadingDialog(this, this.getString(R.string.jmui_loading));
        dialog.show();
        final Intent intent = getIntent();
        mUsername = intent.getStringExtra(IxiaApplication.TARGET_ID);
        mAppKey = intent.getStringExtra(IxiaApplication.TARGET_APP_KEY);
        JMessageClient.getUserInfo(mUsername, mAppKey, new GetUserInfoCallback() {
            @Override
            public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                dialog.dismiss();
                if (responseCode == 0) {
                    mToUserInfo = info;
                    mTv_additionalMsg.setText("附加消息: " + intent.getStringExtra("reason"));
                    mTv_userName.setText(mUsername);
                    long birthday = info.getBirthday();
                }
            }
        });

        final int position = intent.getIntExtra("position", -1);

        View.OnClickListener listener = new View.OnClickListener() {
            final Dialog dialog = DialogCreator.createLoadingDialog(SearchFriendDetailActivity.this, "正在加载");

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_refusal:
                        //拒绝添加
                        dialog.show();
                        ContactManager.declineInvitation(mToUserInfo.getUserName(), mToUserInfo.getAppKey(), "拒绝了您的好友请求", new BasicCallback() {
                            @Override
                            public void gotResult(int responseCode, String responseMessage) {
                                dialog.dismiss();
                                if (responseCode == 0) {
                                    //拒绝时候要修改button数据库状态,并更新内存
                                    Intent btnIntent = new Intent();
                                    btnIntent.putExtra("position", position);
                                    btnIntent.putExtra("btn_state", 1);
                                    setResult(IxiaApplication.RESULT_BUTTON, btnIntent);
                                    finish();
                                }
                            }
                        });
                        break;
                    case R.id.btn_agree:
                        //同意添加
                        dialog.show();
                        ContactManager.acceptInvitation(mToUserInfo.getUserName(), mToUserInfo.getAppKey(), new BasicCallback() {
                            @Override
                            public void gotResult(int responseCode, String responseMessage) {
                                dialog.dismiss();
                                if (responseCode == 0) {
                                    Intent btnIntent2 = new Intent();
                                    btnIntent2.putExtra("position", position);
                                    btnIntent2.putExtra("btn_state", 2);
                                    setResult(IxiaApplication.RESULT_BUTTON, btnIntent2);
//                                    EventBus.getDefault().post(new Event.Builder().setType(EventType.addFriend)
//                                            .setFriendId(SharePreferenceManager.getItem()).build());
                                    finish();
                                }
                            }
                        });
                        break;
                    default:
                        break;
                }

            }
        };
        mBtn_agree.setOnClickListener(listener);
        mBtn_refusal.setOnClickListener(listener);
    }

}
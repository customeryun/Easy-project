package com.android.easymanager.control;

import android.app.Dialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import com.android.easymanager.IxiaApplication;
import com.android.easymanager.LoginActivity;
import com.android.easymanager.LoginActivity2;
import com.android.easymanager.MainActivity;
import com.android.easymanager.R;
import com.android.easymanager.database.UserEntry;
import com.android.easymanager.ui.activity.FinishRegisterActivity;
import com.android.easymanager.utils.DialogCreator;
import com.android.easymanager.utils.SharePreferenceManager;
import com.android.easymanager.utils.ToastUtil;
import java.io.File;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;
import cn.jpush.im.api.BasicCallback;

public class LoginController implements View.OnClickListener {

    private LoginActivity2 mContext;

    public LoginController(LoginActivity2 loginActivity) {
        this.mContext = loginActivity;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                //登陆验证
                final String userId = mContext.getUserId();
                final String password = mContext.getPassword();
                if (TextUtils.isEmpty(userId)) {
                    ToastUtil.shortToast(mContext, "用户名不能为空");
//                    mContext.mLogin_userName.setShakeAnimation();
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    ToastUtil.shortToast(mContext, "密码不能为空");
//                    mContext.mLogin_passWord.setShakeAnimation();
                    return;
                }
                //登陆
                if (IxiaApplication.registerOrLogin % 2 == 1) {
                    final Dialog dialog = DialogCreator.createLoadingDialog(mContext,
                            mContext.getString(R.string.login_hint));
                    dialog.show();
                    JMessageClient.login(userId, password, new BasicCallback() {
                        @Override
                        public void gotResult(int responseCode, String responseMessage) {
                            dialog.dismiss();
                            if (responseCode == 0) {
                                SharePreferenceManager.setCachedPsw(password);
                                UserInfo myInfo = JMessageClient.getMyInfo();
                                File avatarFile = myInfo.getAvatarFile();
                                //登陆成功,如果用户有头像就把头像存起来,没有就设置null
                                if (avatarFile != null) {
                                    SharePreferenceManager.setCachedAvatarPath(avatarFile.getAbsolutePath());
                                } else {
                                    SharePreferenceManager.setCachedAvatarPath(null);
                                }
                                String username = myInfo.getUserName();
                                String appKey = myInfo.getAppKey();
                                UserEntry user = UserEntry.getUser(username, appKey);
                                if (null == user) {
                                    user = new UserEntry(username, appKey);
                                    user.save();
                                }
                                //mContext.goToActivity(mContext, MainActivity.class);
                                MainActivity.launchActivity(mContext);
                                ToastUtil.shortToast(mContext, "登陆成功");
                                mContext.finish();
                            } else {
                                ToastUtil.shortToast(mContext, "登陆失败" + responseMessage);
                            }
                        }
                    });
                    //注册
                } else {
//                    JMessageClient.register(userId, password, new BasicCallback() {
//                        @Override
//                        public void gotResult(int i, String s) {
//                            if (i == 0) {
//                                SharePreferenceManager.setRegisterName(userId);
//                                SharePreferenceManager.setRegistePass(password);
//                                mContext.startActivity(new Intent(mContext, FinishRegisterActivity.class));
//                                ToastUtil.shortToast(mContext, "注册成功");
//                            } else {
//                                //HandleResponseCode.onHandle(mContext, i, false);
//                                ToastUtil.shortToast(mContext, "注册失败:"+s);
//                            }
//                        }
//                    });
                }
                break;
//            case R.id.login_register:
//                mContext.mLogin_passWord.setText("");
//                IxiaApplication.registerOrLogin++;
//                if (IxiaApplication.registerOrLogin % 2 == 0) {
//                    mContext.mBtn_login.setText("注册");
//                    mContext.mLogin_register.setText("立即登陆");
//                    mContext.mLogin_desc.setText("已有账号? ");
//                    mContext.mLogin_forget.setVisibility(View.GONE);
//                } else {
//                    mContext.mBtn_login.setText("登录");
//                    mContext.mLogin_register.setText("立即注册");
//                    mContext.mLogin_desc.setText("还没有账号? ");
//                    mContext.mLogin_forget.setVisibility(View.VISIBLE);
//                }
//                break;
        }
    }
}

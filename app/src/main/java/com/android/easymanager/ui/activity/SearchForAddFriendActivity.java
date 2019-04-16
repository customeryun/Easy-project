package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.model.InfoModel;
import com.android.easymanager.utils.ToastUtil;
import com.android.easymanager.utils.dialog.LoadDialog;
import com.android.easymanager.utils.photochoose.SelectableRoundedImageView;
import java.io.File;
import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.callback.GetUserInfoCallback;
import cn.jpush.im.android.api.model.UserInfo;

public class SearchForAddFriendActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.et_searchUser)
    EditText mEt_searchUser;
    @BindView(R.id.btn_search)
    Button mBtn_search;
    @BindView(R.id.search_result)
    LinearLayout mSearch_result;
    @BindView(R.id.search_header)
    SelectableRoundedImageView mSearch_header;
    @BindView(R.id.search_name)
    TextView mSearch_name;
    @BindView(R.id.search_addBtn)
    Button mSearch_addBtn;
    @BindView(R.id.iv_clear)
    ImageView mIv_clear;

    @Override
    public int getLayout() {
        return R.layout.activity_search_for_add_friend;
    }

    @Override
    public void init() {
        setTitle("添加好友");
        initView();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,SearchForAddFriendActivity.class);
        context.startActivity(intent);
    }

    private void initView() {
        mBtn_search.setEnabled(false);
        mEt_searchUser.requestFocus();
        mEt_searchUser.addTextChangedListener(new TextChange());
//        Intent intent = getIntent();
//        if (intent.getFlags() == 2) {
//            initTitle(true, true, "发起单聊", "", false, "");
//            mSearch_addBtn.setVisibility(View.GONE);
//        } else {
//            initTitle(true, true, "添加好友", "", false, "");
//        }
    }

    @OnClick({R.id.btn_search,R.id.search_result,R.id.search_addBtn,R.id.iv_clear})
    public void onClick(View v) {
        final Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.btn_search:
                hintKbTwo();
                String searchUserName = mEt_searchUser.getText().toString();
                if (!TextUtils.isEmpty(searchUserName)) {
                    LoadDialog.show(this);
                    JMessageClient.getUserInfo(searchUserName, new GetUserInfoCallback() {
                        @Override
                        public void gotResult(int responseCode, String responseMessage, UserInfo info) {
                            LoadDialog.dismiss(SearchForAddFriendActivity.this);
                            if (responseCode == 0) {
                                InfoModel.getInstance().friendInfo = info;
                                mSearch_result.setVisibility(View.VISIBLE);
                                //已经是好友则不显示"加好友"按钮
                                if (info.isFriend()) {
                                    mSearch_addBtn.setVisibility(View.GONE);
                                    //如果是发起单聊.那么不能显示加好友按钮
                                } else if (!info.isFriend() && getIntent().getFlags() != 2) {
                                    mSearch_addBtn.setVisibility(View.VISIBLE);
                                }
                                //这个接口会在本地寻找头像文件,不存在就异步拉取
                                File avatarFile = info.getAvatarFile();
                                if (avatarFile != null) {
                                    mSearch_header.setImageBitmap(BitmapFactory.decodeFile(avatarFile.getAbsolutePath()));
                                    InfoModel.getInstance().setBitmap(BitmapFactory.decodeFile(avatarFile.getAbsolutePath()));
                                } else {
                                    mSearch_header.setImageResource(R.drawable.rc_default_portrait);
                                    InfoModel.getInstance().setBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.rc_default_portrait));
                                }
//                                mSearch_name.setText(TextUtils.isEmpty(info.getNickname()) ? info.getUserName() : info.getNickname());
                                mSearch_name.setText(info.getUserName());
                            } else {
                                ToastUtil.shortToast(SearchForAddFriendActivity.this, "该用户不存在");
                                mSearch_result.setVisibility(View.GONE);
                            }
                        }
                    });
                }
                break;
            case R.id.search_result://详细资料
//                if (InfoModel.getInstance().isFriend()) {//已经是好友
                    intent.setClass(SearchForAddFriendActivity.this, FriendInfoActivity.class);
                    intent.putExtra("addFriend", true);
                    intent.putExtra("targetId", InfoModel.getInstance().friendInfo.getUserName());
//                } else if (getIntent().getFlags() == 2){//直接发起单聊
//                    intent.setClass(SearchForAddFriendActivity.this, GroupNotFriendActivity.class);
//                    intent.putExtra(JGApplication.TARGET_ID, InfoModel.getInstance().friendInfo.getUserName());
//                    intent.putExtra(JGApplication.TARGET_APP_KEY, InfoModel.getInstance().friendInfo.getAppKey());
//                    startActivity(intent);
//                }else {//添加好友
//                    intent.setClass(SearchForAddFriendActivity.this, SearchFriendInfoActivity.class);
//                    startActivity(intent);
//                }
                startActivity(intent);
                break;
            case R.id.search_addBtn:
//                //添加申请
//                intent.setClass(SearchForAddFriendActivity.this, VerificationActivity.class);
//                startActivity(intent);
                break;
            case R.id.iv_clear:
                mEt_searchUser.setText("");
                break;
        }
    }

    private class TextChange implements TextWatcher {
        @Override
        public void afterTextChanged(Editable arg0) {
        }

        @Override
        public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
        }

        @Override
        public void onTextChanged(CharSequence cs, int start, int before, int count) {
            boolean feedback = mEt_searchUser.getText().length() > 0;
            if (feedback) {
                mIv_clear.setVisibility(View.VISIBLE);
                mBtn_search.setEnabled(true);
            } else {
                mIv_clear.setVisibility(View.GONE);
                mBtn_search.setEnabled(false);
            }
        }
    }

    private void hintKbTwo() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }
}

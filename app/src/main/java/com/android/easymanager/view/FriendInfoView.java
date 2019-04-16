package com.android.easymanager.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.control.FriendInfoController;
import com.android.easymanager.ui.activity.FriendInfoActivity;
import cn.jpush.im.android.api.model.UserInfo;

public class FriendInfoView extends LinearLayout {
    private FriendInfoController mListeners;
    private FriendInfoController mOnChangeListener;
    private ImageView mIv_friendPhoto;
    private TextView mTv_userName;
    private Button mBtn_goToChat;
    private ImageView mSetting;
    private ImageView mReturnBtn;
    private Context mContext;

    public FriendInfoView(Context context) {
        super(context);
    }

    public FriendInfoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FriendInfoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void initModel(FriendInfoActivity context) {
        this.mContext = context;
        mIv_friendPhoto = (ImageView) findViewById(R.id.iv_friendPhoto);
        mTv_userName = (TextView) findViewById(R.id.tv_userName);
        mSetting = (ImageView) findViewById(R.id.jmui_commit_btn);
        mBtn_goToChat = (Button) findViewById(R.id.btn_goToChat);
        mReturnBtn = (ImageView) findViewById(R.id.return_btn);
    }

    public void setListeners(OnClickListener listeners) {
        mBtn_goToChat.setOnClickListener(listeners);
        mIv_friendPhoto.setOnClickListener(listeners);
        mSetting.setOnClickListener(listeners);
        mReturnBtn.setOnClickListener(listeners);
    }

    public void setOnChangeListener(FriendInfoController onChangeListener) {
        mOnChangeListener = onChangeListener;
    }

    public void initInfo(UserInfo userInfo) {
        if (userInfo != null) {
            String userName = userInfo.getUserName();
            mTv_userName.setText("用户名："+userName);
        }
    }
}

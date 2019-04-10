package com.android.easymanager.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.android.easymanager.R;
import com.android.easymanager.ui.activity.CollectionActivity;
import com.android.easymanager.ui.activity.ContactActivity;
import com.android.easymanager.ui.activity.ContactDetailActivity;
import com.android.easymanager.ui.activity.MeDetailActivity;
import com.android.easymanager.ui.activity.MessageActivity;
import com.android.easymanager.ui.activity.UserInfoActivity;
import com.android.easymanager.ui.activity.UserSettingsActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class UserFragment extends BaseFragment {

    @BindView(R.id.icon_userinfo)
    ImageView img_userInfo;
    @BindView(R.id.icon_settings)
    ImageView img_settings;
    @BindView(R.id.message)
    LinearLayout layout_message;
    @BindView(R.id.contact)
    LinearLayout layout_contact;
    @BindView(R.id.collection)
    LinearLayout layout_collection;
    @BindView(R.id.layout_me)
    RelativeLayout meLayout;

    public static UserFragment getInstance(){
        UserFragment fragment = new UserFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout() {
        return R.layout.user_fragment_layout;
    }

    @Override
    public void init() {

    }

    @Override
    public Object crateView() {
        return null;
    }

    @Override
    public Object createPresenter() {
        return null;
    }


    @OnClick({R.id.message,R.id.contact,R.id.collection,R.id.icon_userinfo,R.id.icon_settings,R.id.layout_me})
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.message:
                MessageActivity.launchActivity(mContext);
                break;
            case R.id.contact:
                ContactActivity.launchActivity(mContext);
                break;
            case R.id.collection:
                CollectionActivity.launchActivity(mContext);
                break;
            case R.id.icon_userinfo:
                UserInfoActivity.launchActivity(mContext);
                break;
            case R.id.icon_settings:
                UserSettingsActivity.launchActivity(mContext);
                break;
            case R.id.layout_me:
                MeDetailActivity.launchActivity(mContext);
                break;
        }

    }
}

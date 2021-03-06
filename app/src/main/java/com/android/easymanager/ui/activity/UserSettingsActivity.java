package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.android.easymanager.R;
import com.android.easymanager.ui.widget.settings.SettingsItemView;
import butterknife.BindView;
import butterknife.OnClick;

public class UserSettingsActivity extends BaseActivity {

    @BindView(R.id.item_account)
    SettingsItemView item_account;
    @BindView(R.id.item_message_notification)
    SettingsItemView item_message_notification;
    @BindView(R.id.item_privacy)
    SettingsItemView iitem_privacy;
    @BindView(R.id.item_clean)
    SettingsItemView item_clean;
    @BindView(R.id.item_language)
    SettingsItemView item_language;
    @BindView(R.id.item_about)
    SettingsItemView item_about;
    @BindView(R.id.item_share)
    SettingsItemView item_share;
    @BindView(R.id.item_comment)
    SettingsItemView iitem_comment;
    @BindView(R.id.item_suggestion)
    SettingsItemView item_suggestion;

    @Override
    public int getLayout() {
        return R.layout.user_settings;
    }

    @Override
    public void init() {
        setTitle("设置");
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,UserSettingsActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.item_account,R.id.item_message_notification,R.id.item_privacy,R.id.item_clean,
            R.id.item_language,R.id.item_about,R.id.item_share,R.id.item_comment,R.id.item_suggestion})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_account:
                AccountSecuritySettingsActivity.launchActivity(mContext);
                break;
            case R.id.item_message_notification:
                break;
            case R.id.item_privacy:
                break;
            case R.id.item_clean:
                break;
            case R.id.item_language:
                break;
            case R.id.item_about:
                break;
            case R.id.item_share:
                break;
            case R.id.item_comment:
                break;
            case R.id.item_suggestion:
                break;
        }
    }
}

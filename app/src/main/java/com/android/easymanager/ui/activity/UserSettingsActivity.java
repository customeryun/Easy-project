package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.android.easymanager.R;
import com.android.easymanager.ui.widget.settings.ItemView;

public class UserSettingsActivity extends BaseActivity implements View.OnClickListener {

    ItemView item_account,item_message_notification,item_privacy,item_clean;
    ItemView item_language,item_about,item_share,item_comment,item_suggestion;

    @Override
    public int getLayout() {
        return R.layout.user_settings;
    }

    @Override
    public void init() {
        setTitle("设置");
        item_account = (ItemView)findViewById(R.id.item_account);
        item_message_notification = (ItemView)findViewById(R.id.item_message_notification);
        item_privacy = (ItemView)findViewById(R.id.item_privacy);
        item_clean = (ItemView)findViewById(R.id.item_clean);
        item_language = (ItemView)findViewById(R.id.item_language);
        item_about = (ItemView)findViewById(R.id.item_about );
        item_share = (ItemView)findViewById(R.id.item_share);
        item_comment = (ItemView)findViewById(R.id.item_comment);
        item_suggestion = (ItemView)findViewById(R.id.item_suggestion);

        item_account.setOnClickListener(this);
        item_message_notification.setOnClickListener(this);
        item_privacy.setOnClickListener(this);
        item_clean.setOnClickListener(this);
        item_language.setOnClickListener(this);
        item_about.setOnClickListener(this);
        item_share.setOnClickListener(this);
        item_comment.setOnClickListener(this);
        item_suggestion.setOnClickListener(this);
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,UserSettingsActivity.class);
        context.startActivity(intent);
    }

    @Override
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

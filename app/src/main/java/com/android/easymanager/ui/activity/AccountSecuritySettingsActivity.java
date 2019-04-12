package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.android.easymanager.R;
import com.android.easymanager.ui.widget.settings.ItemView;
import butterknife.BindView;
import butterknife.OnClick;

public class AccountSecuritySettingsActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.phone_bind)
    ItemView phone_bind_view;
    @BindView(R.id.phone_modify)
    ItemView phone_modify_view;
    @BindView(R.id.password_modify)
    ItemView password_modify;
    @BindView(R.id.password_reset)
    ItemView password_reset_view;

    @Override
    public int getLayout() {
        return R.layout.user_settings_item_account_layout;
    }

    @Override
    public void init() {
        setTitle("账号与安全");
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,AccountSecuritySettingsActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.phone_bind,R.id.phone_modify,R.id.password_modify,R.id.password_reset})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.phone_bind:
                PhoneNumberModifyActivity.launchActivity(mContext);
                break;
            case R.id.phone_modify:
                PhoneNumberModifyActivity.launchActivity(mContext);
                break;
            case R.id.password_modify:
                PasswordModifyActivity.launchActivity(mContext);
                break;
            case R.id.password_reset:
                break;
        }
    }
}

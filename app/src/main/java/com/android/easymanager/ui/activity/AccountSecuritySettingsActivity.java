package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.android.easymanager.R;
import com.android.easymanager.ui.widget.settings.ItemView;

public class AccountSecuritySettingsActivity extends BaseActivity implements View.OnClickListener {

    ItemView item_account_phone,item_account_password;

    @Override
    public int getLayout() {
        return R.layout.user_settings_item_account_layout;
    }

    @Override
    public void init() {
        setTitle("账号与安全");
        item_account_phone = (ItemView)findViewById(R.id.item_account_phone);
        item_account_password = (ItemView)findViewById(R.id.item_account_password);

        item_account_phone.setOnClickListener(this);
        item_account_password.setOnClickListener(this);
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,AccountSecuritySettingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.item_account_phone:
                break;
            case R.id.item_account_password:
                break;
        }
    }
}

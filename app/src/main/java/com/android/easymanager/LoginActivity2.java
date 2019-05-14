package com.android.easymanager;

import com.android.easymanager.ui.activity.BaseActivity;

public class LoginActivity2 extends BaseActivity {

    @Override
    public int getLayout() {
        return R.layout.activity_login_3;
    }

    @Override
    public void init() {
        setActionbarVisible(false);
    }
}

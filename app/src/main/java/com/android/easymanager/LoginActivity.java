package com.android.easymanager;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import com.android.easymanager.ui.activity.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.username)
    EditText edit_name;
    @BindView(R.id.userpassword)
    EditText edit_password;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        setActionbarVisible(false);
    }

    @OnClick({R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                startLogin();
        }
    }

    private void startLogin(){
        String name = edit_name.getText().toString();
        String passWord = edit_password.getText().toString();
        Intent intent = new Intent(this,MainActivity.class);
        this.startActivity(intent);
    }
}

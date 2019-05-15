package com.android.easymanager;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.android.easymanager.control.LoginController;
import com.android.easymanager.ui.activity.BaseActivity;
import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.username)
    public EditText mLogin_userName;
    @BindView(R.id.userpassword)
    public EditText mLogin_passWord;
	@BindView(R.id.password_forget)
    public TextView mLogin_forget;
    @BindView(R.id.login_register)
    public TextView mLogin_register;
    @BindView(R.id.login_desc)
    public TextView mLogin_desc;
    @BindView(R.id.btn_login)
    public Button mBtn_login;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void init() {
        setActionbarVisible(false);

//        mBtn_login.setOnClickListener(new LoginController(this));
//        mLogin_register.setOnClickListener(new LoginController(this));
    }
	
	public String getUserId() {
        return mLogin_userName.getText().toString().trim();
    }

    public String getPassword() {
        return mLogin_passWord.getText().toString().trim();
    }
}

package com.android.easymanager.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.android.easymanager.IxiaApplication;
import com.android.easymanager.LoginActivity;
import com.android.easymanager.R;

public class FinishRegisterActivity extends Activity {
    Button finish_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_finish_register);

        finish_btn = (Button)findViewById(R.id.finish_btn);
        finish_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.finish_btn:
                        IxiaApplication.registerOrLogin = 1;
                        Intent intent = new Intent(FinishRegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        break;
                }
            }
        });
    }

}

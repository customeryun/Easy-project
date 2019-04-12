package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;
import com.android.easymanager.R;
import butterknife.BindView;

public class PhoneNumberModifyActivity extends BaseActivity{

    @BindView(R.id.tv_current)
    TextView tv_current;

    @Override
    public int getLayout() {
        return R.layout.phone_number_modify_layout;
    }

    @Override
    public void init() {
        setTitle("绑定/更改手机号");
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,PhoneNumberModifyActivity.class);
        context.startActivity(intent);
    }
}

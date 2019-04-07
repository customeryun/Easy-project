package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import com.android.easymanager.R;
import com.android.easymanager.ui.widget.settings.ItemView;
import butterknife.BindView;
import butterknife.OnClick;

public class ContactAddActivity extends BaseActivity{

    @BindView(R.id.contact_show_code)
    ItemView item_show_code;

    @Override
    public int getLayout() {
        return R.layout.contact_add_layout;
    }

    @Override
    public void init() {
        setTitle("添加同学");
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,ContactAddActivity.class);
        context.startActivity(intent);
    }

    @OnClick({R.id.contact_show_code})
    public void onClick(View view) {
        UserInfoActivity.launchActivity(mContext);
    }
}

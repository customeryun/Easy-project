package com.android.easymanager.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.model.InfoModel;
import butterknife.BindView;
import butterknife.OnClick;

public class SearchFriendInfoActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.tv_userName)
    TextView mTv_userName;

    @Override
    public int getLayout() {
        return R.layout.activity_search_friend_info;
    }

    @Override
    public void init() {
        setActionbarVisible(false);

        InfoModel instance = InfoModel.getInstance();
        mTv_userName.setText(instance.getUserName());
    }

    @OnClick({R.id.btn_addFriend,R.id.return_btn})
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btn_addFriend://添加好友界面
                intent = new Intent(SearchFriendInfoActivity.this, VerificationActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.return_btn:
                finish();
                break;
            default:
                break;
        }
    }
}

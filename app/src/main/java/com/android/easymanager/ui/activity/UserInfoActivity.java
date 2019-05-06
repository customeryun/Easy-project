package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.easymanager.R;
import com.android.easymanager.model.User;
import com.android.easymanager.model.UserModel;
import com.android.easymanager.utils.encode.EncodingHandler;
import com.google.gson.Gson;
import butterknife.BindView;
import cn.jpush.im.android.api.JMessageClient;
import cn.jpush.im.android.api.model.UserInfo;

public class UserInfoActivity extends BaseActivity{
    @BindView(R.id.tv_userName)
    TextView tvUserName;
    @BindView(R.id.iv_erWeiMa)
    ImageView ivErWeiMa;

    @Override
    public int getLayout() {
        return R.layout.user_info;
    }

    @Override
    public void init() {
        setTitle("我的二维码");
        initData();
    }

    public static void launchActivity(Context context){
        UserInfo mMyInfo = JMessageClient.getMyInfo();
        Intent intent = new Intent(context,UserInfoActivity.class);
        intent.putExtra("appkey", mMyInfo.getAppKey());
        intent.putExtra("username", mMyInfo.getUserName());
        context.startActivity(intent);
    }

    private void initData() {
        Intent intent = getIntent();

        //根据用户名和appkey生成二维码bitmap返回
        String appKey = intent.getStringExtra("appkey");
        String userName = intent.getStringExtra("username");
        String platform = "a";
        tvUserName.setText(userName);
        Gson gson = new Gson();
        User user = new User(appKey, userName, platform);

        //生成json然后根据json生成二维码
        //json格式 {"type":"user","user":{"appkey":"4f7aef34fb361292c566a1cd","platform":"a","username":"nnnn"}}
        UserModel<User> userModel = new UserModel<>("user", user);
        String toErWeiMa = gson.toJson(userModel);

        Bitmap bitmap = null;
        try {
            bitmap = EncodingHandler.create2Code(toErWeiMa, 600);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ivErWeiMa.setImageBitmap(bitmap);
//        ivSave.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //保存二维码的底部弹窗
//                final Dialog photoDialog = new Dialog(Person2CodeActivity.this, R.style.jmui_default_dialog_style);
//                LayoutInflater inflater = LayoutInflater.from(Person2CodeActivity.this);
//                View view = inflater.inflate(R.layout.save_erweima, null);
//                photoDialog.setContentView(view);
//                Window window = photoDialog.getWindow();
//                window.setWindowAnimations(R.style.mystyle); // 添加动画
//                window.setGravity(Gravity.BOTTOM);
//                window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                photoDialog.show();
//                photoDialog.setCanceledOnTouchOutside(true);
//                Button savePhoto = (Button) view.findViewById(R.id.btn_save);
//                Button cancel = (Button) view.findViewById(R.id.btn_cancel);
//
//                View.OnClickListener listener = new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        if (v.getId() == R.id.btn_save) {
//                            //截屏dialog并保存到手机
//                            String path = screenShotView(llCopy);
//                            savePicture(path);
//                            photoDialog.dismiss();
//
//                        } else {
//                            photoDialog.cancel();
//                        }
//                    }
//                };
//                savePhoto.setOnClickListener(listener);
//                cancel.setOnClickListener(listener);
//            }
//        });
    }
}

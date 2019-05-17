package com.android.easymanager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.easymanager.control.LoginController;
import com.android.easymanager.ui.activity.LanguageSettingsActivity;
import com.android.easymanager.ui.activity.LoginPasswordForgetActivity;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity2 extends AppCompatActivity{
    @BindView(R.id.layout_resgister_group)
    LinearLayout layout_resgister_group;
    @BindView(R.id.layout_login_group)
    LinearLayout layout_login_group;
    @BindView(R.id.resgister_viewpage)
    ViewPager mViewPager;
    @BindView(R.id.tab_email)
    TextView tab_email;
    @BindView(R.id.tab_phone)
    TextView tab_phone;
    @BindView(R.id.btn_login)
    Button mBtn_login;
    @BindView(R.id.username)
    public EditText mLogin_userName;
    @BindView(R.id.userpassword)
    public EditText mLogin_passWord;
    @BindView(R.id.tv_language)
    TextView tv_language;
    @BindView(R.id.tv_password_forget)
    TextView tv_password_forget;

    private List<View> mTabs = new ArrayList<View>();
    private PagerAdapter mAdpater;

    @OnClick({R.id.btn_goto_login,R.id.btn_register,R.id.btn_goto_resgister,R.id.btn_login,R.id.tab_email,R.id.tab_phone,R.id.layout_language,R.id.tv_password_forget})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_goto_login:
                layout_resgister_group.setVisibility(View.GONE);
                layout_login_group.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_goto_resgister:
                layout_resgister_group.setVisibility(View.VISIBLE);
                layout_login_group.setVisibility(View.GONE);
                break;
            case R.id.tab_email:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.tab_phone:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.layout_language:
//                LanguageSettingsActivity.launchActivity(this);
                startActivityForResult(new Intent(this, LanguageSettingsActivity.class), 1);
                break;
            case R.id.tv_password_forget:
                LoginPasswordForgetActivity.launchActivity(this);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK && data!=null){
            String result = data.getExtras().getString("selected_language");
            tv_language.setText(result);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        ButterKnife.bind(this);
        initViewPager();
        mBtn_login.setOnClickListener(new LoginController(this));
        int flag = getIntent().getIntExtra("flag",0);
        layout_resgister_group.setVisibility(flag==0 ? View.VISIBLE : View.GONE);
        layout_login_group.setVisibility(flag==1 ? View.VISIBLE : View.GONE);

    }

    private void initViewPager(){
        LayoutInflater inflater = LayoutInflater.from(this);
        View view_email = inflater.inflate(R.layout.login_layout_resginster_email, null);
        View view_phone = inflater.inflate(R.layout.login_layout_resginster_phone, null);
        mTabs.add(view_email);
        mTabs.add(view_phone);
        mAdpater = new PagerAdapter() {
            @Override
            public int getCount() {
                return mTabs.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                View view = mTabs.get(position);
                if(view.getParent() == null)
                    container.addView(view);
                return view;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                container.removeView(mTabs.get(position));
            }
        };
        mViewPager.setAdapter(mAdpater);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                int currentItem = mViewPager.getCurrentItem();
                switch (currentItem) {
                    case 0:
                        tab_email.setTextColor(Color.parseColor("#4189FF"));
                        tab_phone.setTextColor(Color.parseColor("#333333"));
                        break;
                    case 1:
                        tab_phone.setTextColor(Color.parseColor("#4189FF"));
                        tab_email.setTextColor(Color.parseColor("#333333"));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public String getUserId() {
        return mLogin_userName.getText().toString().trim();
    }

    public String getPassword() {
        return mLogin_passWord.getText().toString().trim();
    }
}

package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.easymanager.R;
import com.android.easymanager.ui.fragment.MeApprovalFragment;
import com.android.easymanager.ui.fragment.MeWillApprovalFragment;
import com.android.easymanager.ui.fragment.MyCreateCommuntityFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;

public class MyCommunitityActivity extends BaseActivity{

    @BindView(R.id.view_page)
    ViewPager mViewPager;
    @BindView(R.id.stl)
    SlidingTabLayout mStl;

    private MyPagerAdapter mAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"我发布的", "我评论的"};

    @Override
    public int getLayout() {
        return R.layout.communitity_my_layout;
    }

    @Override
    public void init() {
        setTitle("我的互动");
        mFragments.add(MyCreateCommuntityFragment.getInstance());
        mFragments.add(MyCreateCommuntityFragment.getInstance());
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mStl.setViewPager(mViewPager);
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,MyCommunitityActivity.class);
        context.startActivity(intent);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }
}

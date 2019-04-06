package com.android.easymanager.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.android.easymanager.R;
import com.android.easymanager.ui.adapter.MeWillApprovalAdapter;
import com.android.easymanager.ui.fragment.MeApprovalFragment;
import com.android.easymanager.ui.fragment.MeWillApprovalFragment;
import com.android.easymanager.ui.fragment.ScheduleFragment;
import com.android.easymanager.ui.fragment.UserFragment;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by PC-xiaoming on 2019/4/6.
 */

public class MeApprovalActivity extends BaseActivity{
    @BindView(R.id.view_page)
    ViewPager mViewPager;
    @BindView(R.id.stl)
    SlidingTabLayout mStl;

    private MyPagerAdapter mAdapter;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private final String[] mTitles = {"待我审批的", "我已审批的"};

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,MeApprovalActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayout() {
        return R.layout.me_approval_layout;
    }

    @Override
    public void init() {
        setTitle("审批");
        mFragments.add(MeWillApprovalFragment.getInstance());
        mFragments.add(MeApprovalFragment.getInstance());
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mStl.setViewPager(mViewPager);
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

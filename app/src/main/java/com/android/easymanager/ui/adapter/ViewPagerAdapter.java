package com.android.easymanager.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.easymanager.ui.fragment.ApplyFragment;
import com.android.easymanager.ui.fragment.HomeFragment;
import com.android.easymanager.ui.fragment.ScheduleFragment;
import com.android.easymanager.ui.fragment.UserFragment;

import java.util.ArrayList;

/**
 * Created by PC-xiaoming on 2019/4/4.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> fragments = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);

        fragments.clear();
        fragments.add(HomeFragment.getInstance());
        fragments.add(ScheduleFragment.getInstance());
        fragments.add(ApplyFragment.getInstance());
        fragments.add(HomeFragment.getInstance());
        fragments.add(UserFragment.getInstance());

    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}

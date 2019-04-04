package com.android.easymanager;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.easymanager.ui.adapter.ViewPagerAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "App_main";

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottom_navigation;
    @BindView(R.id.view_page)
    AHBottomNavigationViewPager view_page;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }

    public void initView(){
        initBottomNavigation();
        initViewPage();
    }

    public void initBottomNavigation(){

        bottom_navigation.addItems(buildItem());
        bottom_navigation.setForceTitlesDisplay(true);
        bottom_navigation.setDefaultBackgroundColor(Color.parseColor("#FEFEFE"));
        bottom_navigation.setAccentColor(Color.parseColor("#1DA1F2"));
        bottom_navigation.setInactiveColor(Color.parseColor("#949494"));

        bottom_navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                view_page.setCurrentItem(position,false);
                return true;
            }
        });
    }

    public void initViewPage(){
        view_page.setOffscreenPageLimit(5);
        view_page.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));

    }

    public ArrayList<AHBottomNavigationItem> buildItem(){
        ArrayList<AHBottomNavigationItem> items = new ArrayList<>();

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.bottom_navigation_item_home,R.drawable.navtab_home,R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.bottom_navigation_item_schedule, R.drawable.navtab_we, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.bottom_navigation_item_application, R.drawable.navtab_help, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.bottom_navigation_item_community, R.drawable.navtab_cart, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.bottom_navigation_item_mine, R.drawable.navtab_user, R.color.colorBottomNavigationActiveColored);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);

        return items;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}

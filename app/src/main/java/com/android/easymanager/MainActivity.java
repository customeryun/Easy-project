package com.android.easymanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.Window;
import android.view.WindowManager;
import com.android.easymanager.ui.activity.BaseActivity;
import com.android.easymanager.ui.adapter.ViewPagerAdapter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationViewPager;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    public static final String TAG = "App_main";

    //@BindView(R.id.bottom_navigation)
    public static AHBottomNavigation bottom_navigation;
//    @BindView(R.id.view_page)
   public static AHBottomNavigationViewPager view_page;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void init() {
        initView();
        setActionbarVisible(false);
    }

    public void initView(){
        bottom_navigation = (AHBottomNavigation)findViewById(R.id.bottom_navigation);
        view_page = (AHBottomNavigationViewPager)findViewById(R.id.view_page);
        initBottomNavigation();
        initViewPage();
    }

    public static void launchActivity(Context context){
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    public void initBottomNavigation(){

        bottom_navigation.addItems(buildItem());
        bottom_navigation.setForceTitlesDisplay(true);
        bottom_navigation.setDefaultBackgroundColor(Color.parseColor("#FFFFFF"));
        bottom_navigation.setAccentColor(Color.parseColor("#4189FF"));
        bottom_navigation.setInactiveColor(Color.parseColor("#333333"));

        bottom_navigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                view_page.setCurrentItem(position,false);
                Window mWindow = getWindow();
                mWindow.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                if(position==4){
                    mWindow.setStatusBarColor(getResources().getColor(R.color.colorAccent));
                }else {
                    mWindow.setStatusBarColor(getResources().getColor(R.color.white));
                }
                return true;
            }
        });
    }

    public void initViewPage(){
        view_page.setOffscreenPageLimit(5);
        view_page.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        view_page.setCurrentItem(0);
    }

    public ArrayList<AHBottomNavigationItem> buildItem(){
        ArrayList<AHBottomNavigationItem> items = new ArrayList<>();

        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.bottom_navigation_item_home,R.drawable.ic_navtab_home,R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.bottom_navigation_item_schedule, R.drawable.ic_navtab_we, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.bottom_navigation_item_application, R.drawable.ic_navtab_help, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.bottom_navigation_item_community, R.drawable.ic_navtab_cart, R.color.colorBottomNavigationActiveColored);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.bottom_navigation_item_mine, R.drawable.ic_navtab_user, R.color.colorBottomNavigationActiveColored);

        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);

        return items;
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }

    public static void goToPageById(int position){
        view_page.setCurrentItem(position,false);
        bottom_navigation.setCurrentItem(position);
    }
}

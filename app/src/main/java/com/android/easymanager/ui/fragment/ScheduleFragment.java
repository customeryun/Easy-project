package com.android.easymanager.ui.fragment;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.android.easymanager.R;
import com.android.easymanager.model.Constant;
import com.android.easymanager.ui.activity.CommonScanActivity;
import com.android.easymanager.ui.activity.ContactAddActivity;
import com.android.easymanager.ui.adapter.SchedulePopAdapter;
import com.android.easymanager.ui.adapter.ScheduleTaskAdapter;
import com.android.easymanager.ui.bean.ScheduleItem;
import com.android.easymanager.utils.CommonUtils;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.CustomListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.zhouwei.library.CustomPopWindow;
import com.haibin.calendarview.CalendarLayout;
import com.haibin.calendarview.CalendarView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by PC-xiaoming on 2019/4/4.
 */

public class ScheduleFragment extends BaseFragment implements ScheduleTaskAdapter.RvOnItemListener {

    @BindView(R.id.recycle_view)
    RecyclerView recycle_view;
    @BindView(R.id.schedule_add)
    FrameLayout schedule_add;
    @BindView(R.id.icon_meun)
    ImageView img_menu;
    @BindView(R.id.calendarLayout)
    CalendarLayout calendarLayout;
    @BindView(R.id.iv_expand)
    ImageView iv_expand;
    @BindView(R.id.calendarView)
    CalendarView calendarView;

    PopupWindow mMenuPopWindow;
    EditText editTv_content;
    CustomPopWindow mAddSchedulePopWindow;
    TimePickerView pvCustomTime = null;
    String mSeletedDateString = "08:10-12:00";
    ScheduleTaskAdapter adapter;


    public static ScheduleFragment getInstance() {
        ScheduleFragment fragment = new ScheduleFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int initLayout() {
        return R.layout.schedule_fragment_layout;
    }

    @Override
    public Object crateView() {
        return null;
    }

    @Override
    public Object createPresenter() {
        return null;
    }

    @Override
    public void init() {
        loadRecyclerView();
        initCalendarLayout();
        initData();
    }

    public void loadRecyclerView() {
        adapter = new ScheduleTaskAdapter(mContext, buildItems(), this);
        recycle_view.setLayoutManager(new LinearLayoutManager(mContext));
        recycle_view.setAdapter(adapter);
    }

    public void initCalendarLayout(){
        calendarLayout.setListener(new CalendarLayout.StateChangeListener() {
            @Override
            public void onStateChange(boolean expand) {
                iv_expand.setRotation(expand?180:360);
            }
        });
    }

    public ArrayList<ScheduleItem> buildItems() {
        ArrayList<ScheduleItem> items = new ArrayList<>();
        items.add(new ScheduleItem(R.drawable.ic_course_done, "汉语言课程", "8:10 - 12:10"));
        items.add(new ScheduleItem(R.drawable.ic_social_activity, "体育活动课程", "8:10 - 12:10"));
        items.add(new ScheduleItem(R.drawable.ic_course_done, "材料力学课程", "8:10 - 12:10"));
//        items.add(new ScheduleItem(R.drawable.ic_social_activity,"社团活动","8:10 - 12:10"));
//        items.add(new ScheduleItem(R.drawable.ic_course_done,"看电影","8:10 - 12:10"));
//        items.add(new ScheduleItem(R.drawable.ic_social_activity,"翻译实践课程","8:10 - 12:10"));
        return items;
    }

    protected void initData() {

        Map<String, com.haibin.calendarview.Calendar> map = new HashMap<>();
        for (int y = 2010; y < 2030; y++) {
            for (int m = 1; m <= 12; m++) {
                map.put(getSchemeCalendar(y, m, 1, 0xFF40db25, "假").toString(),
                        getSchemeCalendar(y, m, 1, 0xFF40db25, "假"));
                map.put(getSchemeCalendar(y, m, 3, 0xFFdf1356, "事").toString(),
                        getSchemeCalendar(y, m, 3, 0xFFdf1356, "事"));
                map.put(getSchemeCalendar(y, m, 5, 0xFFbc13f0, "驾").toString(),
                        getSchemeCalendar(y, m, 5, 0xFFbc13f0, "驾"));
                map.put(getSchemeCalendar(y, m, 7, 0xFF4a4bd2, "会").toString(),
                        getSchemeCalendar(y, m, 7, 0xFF4a4bd2, "会"));
                map.put(getSchemeCalendar(y, m, 9, 0xFF542261, "考").toString(),
                        getSchemeCalendar(y, m, 9, 0xFF542261, "考"));

            }
        }

        //28560 数据量增长不会影响UI响应速度，请使用这个API替换
        calendarView.setSchemeDate(map);
    }

    private com.haibin.calendarview.Calendar getSchemeCalendar(int year, int month, int day, int color, String text) {
        com.haibin.calendarview.Calendar calendar = new com.haibin.calendarview.Calendar();
        calendar.setYear(year);
        calendar.setMonth(month);
        calendar.setDay(day);
        calendar.setSchemeColor(color);//如果单独标记颜色、则会使用这个颜色
        calendar.setScheme(text);
        return calendar;
    }

    @Override
    public void onItemClick(int position, ScheduleItem item) {
        if (position % 2 == 0) {
            buildS2Dialog();
        } else {
            buildS1Dialog();
        }
    }

    @OnClick({R.id.schedule_add, R.id.icon_meun, R.id.iv_expand})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.schedule_add:
                showPopWindow();
                break;
            case R.id.icon_meun:
                showMenuPopWindow();
                break;
            case R.id.iv_expand:
                expand();
                break;
        }
    }

    public void expand(){
        if(calendarLayout.isExpand()){
            calendarLayout.shrink();
        }else {
            calendarLayout.expand();
        }
    }

    private void showMenuPopWindow() {
        View mMenuView = getLayoutInflater().inflate(R.layout.schedule_drop_down_menu, null);
        LinearLayout select_cource = (LinearLayout) mMenuView.findViewById(R.id.menuitem_select_course);
        LinearLayout select_activity = (LinearLayout) mMenuView.findViewById(R.id.menuitem_select_activity);
        LinearLayout select_customize = (LinearLayout) mMenuView.findViewById(R.id.menuitem_select_customize);
        mMenuPopWindow = new PopupWindow(mMenuView, WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT, true);
        mMenuPopWindow.setTouchable(true);
        mMenuPopWindow.setOutsideTouchable(true);
        mMenuPopWindow.setBackgroundDrawable(new BitmapDrawable(getResources(), (Bitmap) null));
        if (mMenuPopWindow.isShowing()) {
            mMenuPopWindow.dismiss();
        } else {
            mMenuPopWindow.showAsDropDown(getActivity().findViewById(R.id.icon_meun), -200, 10);
        }
        select_cource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("linmei", "**列表按课程排序**");
            }
        });
        select_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("linmei", "**列表按活动排序**");
            }
        });
        select_customize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("linmei", "**列表自定义排序**");
            }
        });
    }

    public void buildS1Dialog() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.schedule_dialog_s1_layout, null, false);
        final AlertDialog dialog = new AlertDialog.Builder(mActivity).setView(view).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void buildS2Dialog() {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.schedule_dialog_s2_layout, null, false);
        final AlertDialog dialog = new AlertDialog.Builder(mActivity).setView(view).create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    public void showPopWindow() {
        View contentView = LayoutInflater.from(mActivity).inflate(R.layout.schedule_pop_add_layout, null);
        //处理popWindow 显示内容
        final LinearLayout rvParent = contentView.findViewById(R.id.rv_schedule_pop_add);
        final LinearLayout rv_input = contentView.findViewById(R.id.rv_input);
        editTv_content = contentView.findViewById(R.id.input_content);
        final ImageView imageView = contentView.findViewById(R.id.img_schedule_set_time);
        final EditText input_content = contentView.findViewById(R.id.input_content);
        rv_input.setVisibility(View.VISIBLE);
        CommonUtils.showSoftInput(mContext, input_content);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                rv_input.setVisibility(View.GONE);
                initCustomTimePicker(rvParent);
            }
        });
        //创建并显示popWindow
        mAddSchedulePopWindow = new CustomPopWindow.PopupWindowBuilder(mActivity)
                .setView(contentView)
                .size(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)//显示大小
                .create();
        mAddSchedulePopWindow.showAtLocation(contentView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    private void initCustomTimePicker(View parent) {

        /**
         * @description
         *
         * 注意事项：
         * 1.自定义布局中，id为 optionspicker 或者 timepicker 的布局以及其子控件必须要有，否则会报空指针.
         * 具体可参考demo 里面的两个自定义layout布局。
         * 2.因为系统Calendar的月份是从0-11的,所以如果是调用Calendar的set方法来设置时间,月份的范围也要是从0-11
         * setRangDate方法控制起始终止时间(如果不设置范围，则使用默认时间1900-2100年，此段代码可注释)
         */
        final Calendar selectedDate = Calendar.getInstance();//系统当前时间
        Calendar startDate = Calendar.getInstance();
        startDate.set(2014, 1, 23);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2027, 2, 28);
        //时间选择器 ，自定义布局
        pvCustomTime = new TimePickerBuilder(mActivity, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {//选中事件回调
//                btn_CustomTime.setText(getTime(date));
                mSeletedDateString = date.toLocaleString();
            }
        })
                /*.setType(TimePickerView.Type.ALL)//default is all
                .setCancelText("Cancel")
                .setSubmitText("Sure")
                .setContentTextSize(18)
                .setTitleSize(20)
                .setTitleText("Title")
                .setTitleColor(Color.BLACK)
               /*.setDividerColor(Color.WHITE)//设置分割线的颜色
                .setTextColorCenter(Color.LTGRAY)//设置选中项的颜色
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setTitleBgColor(Color.DKGRAY)//标题背景颜色 Night mode
                .setBgColor(Color.BLACK)//滚轮背景颜色 Night mode
                .setSubmitColor(Color.WHITE)
                .setCancelColor(Color.WHITE)*/
               /*.animGravity(Gravity.RIGHT)// default is center*/
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .isDialog(true)
                .setDividerColor(Color.WHITE)
                .setLineSpacingMultiplier(1.6f)//设置两横线之间的间隔倍数
                .setLayoutRes(R.layout.schedule_pick_time, new CustomListener() {

                    @Override
                    public void customLayout(View v) {
                        final TextView tvSubmit = (TextView) v.findViewById(R.id.tv_finish);
                        TextView ivCancel = (TextView) v.findViewById(R.id.iv_cancel);
                        tvSubmit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.returnData();
                                pvCustomTime.dismiss();
                                mAddSchedulePopWindow.dissmiss();
                                //添加一天日程数据，刷新列表
                                int[] resourceIds = {R.drawable.ic_social_activity, R.drawable.ic_course_done};
                                int idex = (int) (Math.random() * (resourceIds.length));
                                String title = editTv_content.getText().toString();
                                ScheduleItem item = new ScheduleItem(resourceIds[idex], title, mSeletedDateString);
                                adapter.addData(item);
                            }
                        });
                        ivCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                pvCustomTime.dismiss();
                                mAddSchedulePopWindow.dissmiss();
                            }
                        });
                    }
                })
                .setContentTextSize(18)
                .setType(new boolean[]{false, false, true, true, true, false})
                .setLabel("年", "月", "日", "时", "分", "秒")
                .setLineSpacingMultiplier(1.2f)
                .setTextXOffset(0, 0, 0, 40, 0, -40)
                .isCenterLabel(false) //是否只显示中间选中项的label文字，false则每项item全部都带有label。
                .setDividerColor(0xFF24AD9D)
                .setDecorView((ViewGroup) parent)
                .isDialog(false)
                .build();
        pvCustomTime.show();

    }

    public ArrayList<String> buildPopItems() {
        ArrayList<String> data = new ArrayList<>();
        data.add("ss");
        data.add("ss");
        data.add("ss");
        data.add("ss");
        data.add("ss");
        data.add("ss");
        data.add("ss");
        return data;
    }
}

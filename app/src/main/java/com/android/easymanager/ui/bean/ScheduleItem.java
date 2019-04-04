package com.android.easymanager.ui.bean;

/**
 * Created by PC-xiaoming on 2019/4/4.
 */

public class ScheduleItem {

    public String title;
    public int res;
    public String time;

    public ScheduleItem(int res,String title,String time){
        this.res = res;
        this.title = title;
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRes() {
        return res;
    }

    public void setRes(int res) {
        this.res = res;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

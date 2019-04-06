package com.android.easymanager.ui.bean;

import android.graphics.Bitmap;

/**
 * Created by PC-xiaoming on 2019/4/6.
 */

public class MeApprovalChildEntry {

    public Bitmap photo;
    public String name;
    public String startTime;
    public String endTime;
    public String result;
    public int type;

    public MeApprovalChildEntry(){

    }

    public Bitmap getPhoto() {
        return photo;
    }

    public void setPhoto(Bitmap photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

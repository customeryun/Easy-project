package com.android.easymanager.ui.bean;

import android.graphics.Bitmap;

/**
 * Created by PC-xiaoming on 2019/4/5.
 */

public class StudentManagerEntry {

    public Bitmap photo;
    public String name;
    public String status1;
    public String status2;

    public StudentManagerEntry(){

    }

    public StudentManagerEntry(Bitmap photo,String name,String status1,String status2){
        this.photo = photo;
        this.name =name;
        this.status1 = status1;
        this.status2 = status2;
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

    public String getStatus1() {
        return status1;
    }

    public void setStatus1(String status1) {
        this.status1 = status1;
    }

    public String getStatus2() {
        return status2;
    }

    public void setStatus2(String status2) {
        this.status2 = status2;
    }
}

package com.android.easymanager.ui.bean;

import java.io.Serializable;

public class StudentItem implements Serializable {
    private int id;
    private String name;
    private String sex;
    private String country;
    private String studengNumber;

    public StudentItem(int mId, String mName,String mSex, String mCountry,String number) {
        id = mId;
        name = mName;
        sex = mSex;
        country = mCountry;
        studengNumber = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStudengNumber() {
        return studengNumber;
    }

    public void setStudengNumber(String studengNumber) {
        this.studengNumber = studengNumber;
    }
}

package com.android.easymanager.ui.bean;

import java.io.Serializable;

public class Contact implements Serializable {
    private String mName;
    private int mType;
    private String mClassName = "汉语言1901班";
    private boolean isFrident;

    public Contact(String name, int type) {
        mName = name;
        mType = type;
    }

    public Contact(String name, String className) {
        mName = name;
        mClassName = className;
    }

    public Contact(String name, String className,boolean frident) {
        mName = name;
        mClassName = className;
        isFrident = frident;

    }

    public String getmName() {
        return mName;
    }

    public int getmType() {
        return mType;
    }

    public String getmClassName(){
        return mClassName;
    }

    public boolean isFrident() {
        return isFrident;
    }

}

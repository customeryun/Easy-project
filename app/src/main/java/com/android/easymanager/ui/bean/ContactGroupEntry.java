package com.android.easymanager.ui.bean;

import java.io.Serializable;

public class ContactGroupEntry implements Serializable {
    private int id;
    private String name;
    private int childSize;

    public ContactGroupEntry(int mId,String mName,int count){
        id = mId;
        name = mName;
        childSize = count;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getChildSize() {
        return childSize;
    }
}

package com.android.easymanager.ui.bean;

import java.io.Serializable;

public class TeacherItem implements Serializable {
    private int id;
    private String name;
    private String type;//职称

    public TeacherItem(int mId,String mName, String mType) {
        id = mId;
        name = mName;
        type = mType;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

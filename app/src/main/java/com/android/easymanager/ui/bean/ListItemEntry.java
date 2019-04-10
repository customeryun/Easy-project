package com.android.easymanager.ui.bean;

import java.io.Serializable;

public class ListItemEntry implements Serializable {
    private String title;
    private String name;

    public ListItemEntry(String mTitle, String mName){
        title = mTitle;
        name = mName;
    }

    public String getName() {
        return name;
    }

    public String getTitle(){
        return title;
    }

}

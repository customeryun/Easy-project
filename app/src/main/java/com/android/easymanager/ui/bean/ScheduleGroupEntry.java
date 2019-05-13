package com.android.easymanager.ui.bean;

import java.util.ArrayList;

/**
 * Created by PC-xiaoming on 2019/5/13.
 */

public class ScheduleGroupEntry {
    private String header;
    private String footer;
    private ArrayList<ScheduleItem> children;

    public ScheduleGroupEntry(String header, String footer, ArrayList<ScheduleItem> children) {
        this.header = header;
        this.footer = footer;
        this.children = children;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getFooter() {
        return footer;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public ArrayList<ScheduleItem> getChildren() {
        return children;
    }

    public void setChildren(ArrayList<ScheduleItem> children) {
        this.children = children;
    }
}

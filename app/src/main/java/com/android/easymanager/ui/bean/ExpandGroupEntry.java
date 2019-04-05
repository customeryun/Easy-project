package com.android.easymanager.ui.bean;

import java.util.ArrayList;

/**
 * Created by PC-xiaoming on 2019/4/5.
 */

public class ExpandGroupEntry {

    private String header;
    private String footer;
    private ArrayList<ApplyChildEntry> childEntries;
    private boolean isExpand;

    public ExpandGroupEntry(String header,String footer,boolean isExpand,ArrayList<ApplyChildEntry> childEntries){
        this.header = header;
        this.footer = footer;
        this.isExpand = isExpand;
        this.childEntries = childEntries;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getHeader() {
        return header;
    }

    public void setFooter(String footer) {
        this.footer = footer;
    }

    public String getFooter() {
        return footer;
    }

    public void setChildEntries(ArrayList<ApplyChildEntry> childEntries) {
        this.childEntries = childEntries;
    }

    public ArrayList<ApplyChildEntry> getChildEntries() {
        return childEntries;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public boolean isExpand() {
        return isExpand;
    }
}

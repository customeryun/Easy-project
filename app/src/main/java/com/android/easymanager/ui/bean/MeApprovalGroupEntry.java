package com.android.easymanager.ui.bean;

import java.util.ArrayList;

/**
 * Created by PC-xiaoming on 2019/4/6.
 */

public class MeApprovalGroupEntry {

    public ArrayList<MeApprovalChildEntry> entries;
    public String header;
    public String footer;
    private boolean isExpand;

    public MeApprovalGroupEntry(String header,String footer,ArrayList<MeApprovalChildEntry> entries,boolean isExpand){
        this.header = header;
        this.footer = footer;
        this.entries = entries;
        this.isExpand = isExpand;
    }

    public void setEntries(ArrayList<MeApprovalChildEntry> entries) {
        this.entries = entries;
    }

    public ArrayList<MeApprovalChildEntry> getEntries() {
        return entries;
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

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public boolean isExpand() {
        return isExpand;
    }
}

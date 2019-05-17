package com.android.easymanager.ui.bean;

public class SocietyBean {
    String name;
    String type;
    int status;//0：已加入  1：审核中

    public SocietyBean(String name,String type, int status){
        this.name = name;
        this.type = type;
        this.status = status;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

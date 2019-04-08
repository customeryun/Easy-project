package com.android.easymanager.ui.bean;

public class MessageGroupEntry {

    private String contactName;
    private String title;
    private String body;
    private String time;

    public MessageGroupEntry(){
        contactName = "张三";
        title = "选课通知";
        body = "测试数据测试测试测试消息主题内容试数据测试测试测试消息主题内试数据测试测试测试消息主题内试数据测试测试测试消息主题内";
        time = "2019-04-04";
    }

    public void setContactName(String name){
        this.contactName = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}

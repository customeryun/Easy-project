package com.android.easymanager.model;

public class UserModel<T> {
    public String type;
    public T user;

    public UserModel(String type, T user) {
        this.type = type;
        this.user = user;
    }
}

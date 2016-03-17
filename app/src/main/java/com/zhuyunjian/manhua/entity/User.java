package com.zhuyunjian.manhua.entity;

/**
 * Created by dell on 2016/3/16.
 */
public class User {

    private int resId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User(String name) {
        this.name = name;
    }

    private String name;

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public User(int resId) {

        this.resId = resId;
    }
}

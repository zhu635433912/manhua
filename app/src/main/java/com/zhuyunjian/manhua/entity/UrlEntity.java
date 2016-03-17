package com.zhuyunjian.manhua.entity;

/**
 * Created by dell on 2016/3/17.
 */
public class UrlEntity {

    private String tag;
    private String type;
    private String days;

    public UrlEntity(String tag, String type, String days, int count) {
        this.tag = tag;
        this.type = type;
        this.days = days;
        this.count = count;
    }

    public UrlEntity(int count) {
        this.count = count;
    }

    private int count;
    public UrlEntity(String tag, String type, String days) {
        this.tag = tag;
        this.type = type;
        this.days = days;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

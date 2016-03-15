package com.zhuyunjian.manhua.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dell on 2016/3/15.
 */
public class CommentEntity {


    private long group_id;

    @SerializedName("data")
    private List<UserEntity> data;

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public void setData(List<UserEntity> data) {
        this.data = data;
    }

    public long getGroup_id() {
        return group_id;
    }

    public List<UserEntity> getData() {
        return data;
    }


}

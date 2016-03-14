package com.zhuyunjian.manhua.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by dell on 2016/3/14.
 */
public class HeavyEntity {

    private List<DataEntity> data;

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public List<DataEntity> getData() {
        return data;
    }


}

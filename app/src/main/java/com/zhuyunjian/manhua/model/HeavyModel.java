package com.zhuyunjian.manhua.model;

import com.zhuyunjian.manhua.entity.HeavyEntity;

import retrofit2.Callback;


/**
 * Created by dell on 2016/3/14.
 */
public interface HeavyModel {
    void getHeavyData(Callback<HeavyEntity> callback,String tag,String days);
}

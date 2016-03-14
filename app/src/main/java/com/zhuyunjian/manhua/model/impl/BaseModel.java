package com.zhuyunjian.manhua.model.impl;

import com.zhuyunjian.manhua.api.ApiService;
import com.zhuyunjian.manhua.utils.UrlUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 2016/3/14.
 */
public class BaseModel {
    ApiService service;
    public BaseModel(String type){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.BASE_URL+type+"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }
}

package com.zhuyunjian.manhua.model.impl;

import com.zhuyunjian.manhua.api.ApiService;
import com.zhuyunjian.manhua.utils.UrlUtils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dell on 2016/3/15.
 */
public class CommentBaseModel {
    ApiService service;

    public CommentBaseModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UrlUtils.COMMENT_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }
}

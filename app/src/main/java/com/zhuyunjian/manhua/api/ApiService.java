package com.zhuyunjian.manhua.api;

import com.zhuyunjian.manhua.entity.CommentEntity;
import com.zhuyunjian.manhua.entity.HeavyEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by dell on 2016/3/14.
 */
public interface ApiService {
    @GET("?iid=3868215609")
    Call<HeavyEntity> getHeavyEntiyt(@QueryMap Map<String,String> map);

    @GET("data/v4/get_comments/?iid=3868215609")
    Call<CommentEntity> getComment(@QueryMap Map<String,String> map);
}

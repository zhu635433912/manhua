package com.zhuyunjian.manhua.api;

import com.zhuyunjian.manhua.entity.HeavyEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by dell on 2016/3/14.
 */
public interface ApiService {
    @GET()
    Call<HeavyEntity> getHeavyEntiyt(@QueryMap Map<String,String> map);

}

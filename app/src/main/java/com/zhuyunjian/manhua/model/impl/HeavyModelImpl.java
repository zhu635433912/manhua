package com.zhuyunjian.manhua.model.impl;

import com.zhuyunjian.manhua.entity.HeavyEntity;
import com.zhuyunjian.manhua.model.HeavyModel;
import com.zhuyunjian.manhua.utils.ParamsMap;
import com.zhuyunjian.manhua.utils.SpinnerData;
import com.zhuyunjian.manhua.utils.UrlUtils;

import retrofit2.Callback;

/**
 * Created by dell on 2016/3/14.
 */
public class HeavyModelImpl extends BaseModel implements HeavyModel {


    public HeavyModelImpl(String type) {
        super(type);
    }

    @Override
    public void getHeavyData(Callback<HeavyEntity> callback,String tag,String days) {
        ParamsMap map = new ParamsMap();
        map.put(SpinnerData.TAG,tag);
        map.put(SpinnerData.DAYS,days);
        service.getHeavyEntiyt(map).enqueue(callback);
    }
}

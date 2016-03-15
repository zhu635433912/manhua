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
    public void getHeavyData(Callback<HeavyEntity> callback,String tag,String days,int count) {
        ParamsMap map = new ParamsMap();
        map.put(SpinnerData.TAG,tag);
        if (days.equals("0")) {

        }else {
            map.put(SpinnerData.DAYS, days);
        }
        map.put(UrlUtils.COUNT,count);
        service.getHeavyEntiyt(map).enqueue(callback);
    }
}

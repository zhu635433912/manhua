package com.zhuyunjian.manhua.model;

import com.zhuyunjian.manhua.model.impl.HeavyModelImpl;

/**
 * Created by dell on 2016/3/14.
 */
public class ModelFactory {
    private static ModelFactory factory;
    private ModelFactory(){

    }
    public static ModelFactory getInstance(){
        return factory == null ? new ModelFactory() : factory;
    }

    public HeavyModelImpl getHeavyModel(String type){
        return new HeavyModelImpl(type);
    }
}

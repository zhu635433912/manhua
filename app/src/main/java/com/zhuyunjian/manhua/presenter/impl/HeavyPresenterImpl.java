package com.zhuyunjian.manhua.presenter.impl;

import com.zhuyunjian.manhua.entity.HeavyEntity;
import com.zhuyunjian.manhua.model.ModelFactory;
import com.zhuyunjian.manhua.presenter.HeavyPresenter;
import com.zhuyunjian.manhua.view.RecentView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by dell on 2016/3/14.
 */
public class HeavyPresenterImpl implements HeavyPresenter {
    private RecentView recentView;
    private String type;
    private String tag;
    private String days;
    private int count;



    public HeavyPresenterImpl(RecentView recentViewView, String type, String tag, String days,int count) {
        this.recentView = recentViewView;
        this.type = type;
        this.tag = tag;
        this.days = days;
        this.count = count;
    }

    @Override
    public void getHeavy() {
        ModelFactory.getInstance().getHeavyModel(type).getHeavyData(new Callback<HeavyEntity>() {
            @Override
            public void onResponse(Call<HeavyEntity> call, Response<HeavyEntity> response) {
                recentView.success(response.body());
            }

            @Override
            public void onFailure(Call<HeavyEntity> call, Throwable t) {
                recentView.failed();
            }
        },tag,days,count);
    }
}

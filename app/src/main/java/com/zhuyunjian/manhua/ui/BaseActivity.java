package com.zhuyunjian.manhua.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuyunjian.manhua.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.simple.eventbus.EventBus;

import cn.jpush.android.api.JPushInterface;

@EActivity(R.layout.activity_base)
public class BaseActivity extends AppCompatActivity {
    @AfterInject
    public void before(){
        EventBus.getDefault().register(this);

    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//        JPushInterface.onResume(this);
//    }

    @AfterViews
    public void init(){
        initView();
        initData();
    }

    public void initView() {

    }

    public void initData() {
    }

    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        JPushInterface.onPause(this);
//    }


}

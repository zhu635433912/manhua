package com.zhuyunjian.manhua.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.zhuyunjian.manhua.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_base)
public class BaseActivity extends AppCompatActivity {
    @AfterInject
    public void before(){

    }
    @AfterViews
    public void init(){
        initView();
        initData();
    }

    public void initView() {

    }

    public void initData() {
    }
}

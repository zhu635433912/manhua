package com.zhuyunjian.manhua.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhuyunjian.manhua.R;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.simple.eventbus.EventBus;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_base)
public class BaseFragment extends Fragment {


    @AfterInject
    public void before(){
        EventBus.getDefault().register(getContext());
    }
    @AfterViews
    public final void init(){

        intiView();
        initData();
    }

    public void initData() {

    }

    public void intiView() {

    }


}

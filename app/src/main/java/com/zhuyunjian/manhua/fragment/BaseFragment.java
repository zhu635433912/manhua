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

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_base)
public class BaseFragment extends Fragment {



    @AfterInject
    public void before(){

    }
    @AfterViews
    public void init(){
        intiView();
        initData();
    }

    public void initData() {

    }

    public void intiView() {

    }

}

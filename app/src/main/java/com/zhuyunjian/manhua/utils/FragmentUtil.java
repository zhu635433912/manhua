package com.zhuyunjian.manhua.utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * Created by dell on 2016/3/14.
 */
public class FragmentUtil {
    private List<Fragment> fragments;
    private FragmentManager manager;
    private String days;
    private int containerID;

    public FragmentTransaction getFragmentTransaction(){
        FragmentTransaction transaction = manager.beginTransaction();
        return transaction;
    }

    public FragmentUtil(List<Fragment> fragments, FragmentManager manager, String days,int containerID) {
        this.fragments = fragments;
        this.manager = manager;
        this.days = days;
        this.containerID = containerID;
        for (int i = 0; i < fragments.size(); i++) {
            getFragmentTransaction().add(containerID,fragments.get(i)).commit();
        }
    }

    public void show(int position){
        for (int i = 0; i < fragments.size(); i++) {
            if (i == position)
                getFragmentTransaction().show(fragments.get(i)).commit();
            else
                getFragmentTransaction().hide(fragments.get(i)).commit();
        }
    }

}

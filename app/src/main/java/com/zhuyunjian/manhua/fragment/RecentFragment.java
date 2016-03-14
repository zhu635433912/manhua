package com.zhuyunjian.manhua.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.entity.HeavyEntity;
import com.zhuyunjian.manhua.presenter.HeavyPresenter;
import com.zhuyunjian.manhua.presenter.impl.HeavyPresenterImpl;
import com.zhuyunjian.manhua.utils.SpinnerData;
import com.zhuyunjian.manhua.view.RecentView;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_recent)
public class RecentFragment extends BaseFragment implements RecentView {
    @ViewById(R.id.recent_text)
    TextView textView;
    private String tag,days,type;

    private HeavyPresenter presenter;
    @Override
    public void before() {
        tag = getArguments().getString(SpinnerData.TAG);
        days = getArguments().getString(SpinnerData.DAYS);
        type = getArguments().getString(SpinnerData.TYPE);
        presenter = new HeavyPresenterImpl(this,type,tag,days);
    }

    @Override
    public void intiView() {
        textView.setText(tag+"     "+days+"       "+type);
        presenter.getHeavy();
    }

    @Override
    public void success(HeavyEntity entity) {

    }

    @Override
    public void failed() {

    }
}

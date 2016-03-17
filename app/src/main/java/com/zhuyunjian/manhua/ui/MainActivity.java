package com.zhuyunjian.manhua.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.zhuyunjian.library.FragmentTabUtils;
import com.zhuyunjian.library.StartUtil;
import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.fragment.HeavyFragment_;
import com.zhuyunjian.manhua.fragment.MoreFragment_;
import com.zhuyunjian.manhua.function.BackgroundEntity;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewById(R.id.widget_bottom_radio)
    RadioGroup radioGroup ;
    @ViewById(R.id.main_back)
    View view;

    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public void before() {
        EventBus.getDefault().register(this);

    }

    @Override
    public void initView() {
        boolean isLight = StartUtil.isLight(this);
        view.setVisibility(isLight ? View.GONE : View.VISIBLE);
        fragments.add(HeavyFragment_.builder().arg(AppConstants.FRAG_TYPE_ID,AppConstants.TAG_HEAVY).build());
        fragments.add(HeavyFragment_.builder().arg(AppConstants.FRAG_TYPE_ID,AppConstants.TAG_COMIC).build());
        fragments.add(HeavyFragment_.builder().arg(AppConstants.FRAG_TYPE_ID,AppConstants.TAG_MENG).build());
        fragments.add(HeavyFragment_.builder().arg(AppConstants.FRAG_TYPE_ID,AppConstants.TAG_GIF).build());
        fragments.add(MoreFragment_.builder().arg(AppConstants.FRAG_TYPE_ID,AppConstants.TAG_MORE).build());
        new FragmentTabUtils(this,getSupportFragmentManager(),radioGroup,fragments,R.id.main_container);
    }
    @Subscriber
    public void changeBackground(BackgroundEntity entity){
        if (entity.isNight())
            view.setVisibility(View.VISIBLE);
        else
            view.setVisibility(View.GONE);
    }
}

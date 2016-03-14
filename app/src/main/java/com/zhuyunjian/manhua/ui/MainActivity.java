package com.zhuyunjian.manhua.ui;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.zhuyunjian.library.FragmentTabUtils;
import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.fragment.HeavyFragment_;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @ViewById(R.id.widget_bottom_radio)
    RadioGroup radioGroup ;

    private ArrayList<Fragment> fragments = new ArrayList<>();

    @Override
    public void initView() {
        fragments.add(HeavyFragment_.builder().arg(AppConstants.FRAG_TYPE_ID,AppConstants.TAG_HEAVY).build());
        fragments.add(HeavyFragment_.builder().arg(AppConstants.FRAG_TYPE_ID,AppConstants.TAG_COMIC).build());
        fragments.add(HeavyFragment_.builder().arg(AppConstants.FRAG_TYPE_ID,AppConstants.TAG_MENG).build());
        fragments.add(HeavyFragment_.builder().arg(AppConstants.FRAG_TYPE_ID,AppConstants.TAG_GIF).build());
        fragments.add(HeavyFragment_.builder().arg(AppConstants.FRAG_TYPE_ID,AppConstants.TAG_MORE).build());
        new FragmentTabUtils(this,getSupportFragmentManager(),radioGroup,fragments,R.id.main_container);
    }
}

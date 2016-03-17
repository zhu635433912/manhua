package com.zhuyunjian.manhua.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.entity.User;

import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;


/**
 * A simple {@link Fragment} subclass.
 */
@EFragment(R.layout.fragment_more)
public class MoreFragment extends BaseFragment implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    @ViewById(R.id.more_title)
    LinearLayout titleLayout;
    @ViewById(R.id.more_collect)
    LinearLayout collectLayout;
    @ViewById(R.id.more_outline_number)
    LinearLayout outlineLayout;
    @ViewById(R.id.more_clear_cache)
    LinearLayout clearLayout;
    @ViewById(R.id.more_suggest)
    LinearLayout suggestLayout;
    @ViewById(R.id.more_check_version)
    LinearLayout checkLayout;
    @ViewById(R.id.more_recommed)
    LinearLayout recommondLayout;
    @ViewById(R.id.more_collect_and_share)
    CheckBox collectShareCb;
    @ViewById(R.id.more_show_comment)
    CheckBox showCommentCb;
    @ViewById(R.id.more_save_progress)
    CheckBox saveProgressCb;
    @ViewById(R.id.more_night_model)
    CheckBox nightModelCb;
    @ViewById(R.id.more_new_pic)
    CheckBox newPicCb;
    @ViewById(R.id.more_title_image)
    ImageView titleImage;
    @Override
    public void before() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void intiView() {

        collectShareCb.setOnCheckedChangeListener(this);
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked){
            EventBus.getDefault().post(new User(R.mipmap.title_hot_today));
        }else {
            EventBus.getDefault().post(new User(R.mipmap.title_hot_week));
        }
    }

    @Override
    public void onClick(View v) {

    }
    @Subscriber
    public void onEventMainThread(User user){
        titleImage.setImageResource(user.getResId());
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }
}

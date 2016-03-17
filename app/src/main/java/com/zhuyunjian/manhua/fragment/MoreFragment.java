package com.zhuyunjian.manhua.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuyunjian.library.StartUtil;
import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.entity.User;
import com.zhuyunjian.manhua.function.BackgroundEntity;
import com.zhuyunjian.manhua.model.impl.AlertDialogModelImpl;
import com.zhuyunjian.manhua.utils.BtnUtil;
import com.zhuyunjian.manhua.utils.DeviceUtil;

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
    @ViewById(R.id.more_clear_text)
    TextView nowCacheText;
    @ViewById(R.id.more_user_name)
    TextView usernameText;
    @ViewById(R.id.more_save_number)
    TextView saveNumberText;
    private AlertDialog clearDialog,chooseDialog;
    @Override
    public void before() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void intiView() {
        String s = "当前缓存:"+ DeviceUtil.getFormatSize(DeviceUtil.getFolderSize(AppConstants.getCacheFile()));
        nowCacheText.setText(s);
        nightModelCb.setChecked(!StartUtil.isLight(getContext()));
        nightModelCb.setOnCheckedChangeListener(this);
        chooseDialog = AlertDialogModelImpl.setChooseDialog(getContext(),saveNumberText);
        clearDialog = AlertDialogModelImpl.setClearDialog(getContext(),nowCacheText);
        clearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "删除", Toast.LENGTH_SHORT).show();
                clearDialog.show();
            }
        });
        outlineLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseDialog.show();
            }
        });
    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        StartUtil.editLight(getContext(),!isChecked);
        EventBus.getDefault().post(new BackgroundEntity(isChecked));
    }

    @Override
    public void onClick(View v) {

    }
    @Subscriber
    public void onEventMainThread(User user){
        titleImage.setImageResource(user.getResId());
    }
    @Subscriber(tag = AppConstants.USER_NAME)
    public void getName(User user){
        usernameText.setText(user.getName());
    }
    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void initData() {
        BtnUtil.loginClick(titleLayout,getContext());
    }
}

package com.zhuyunjian.manhua.utils;

import android.view.View;
import android.widget.ImageView;

import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.function.RefreshEntity;

import org.simple.eventbus.EventBus;

/**
 * Created by dell on 2016/3/17.
 */
public class RefreshUtil {
    public RefreshUtil() {
    }

    public static void refreshView(final ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.mipmap.ic_refresh_pressed);
                EventBus.getDefault().post(new RefreshEntity(true), AppConstants.REFRESH_TAG_TO);
            }
        });
    }
}

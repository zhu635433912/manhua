package com.zhuyunjian.manhua.utils;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.zhuyunjian.manhua.ui.LoginActivity_;

/**
 * Created by dell on 2016/3/17.
 */
public class BtnUtil {


    public static void loginClick(LinearLayout linearLayout, final Context context){
        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(LoginActivity_.intent(context).get());
            }
        });
    }
}

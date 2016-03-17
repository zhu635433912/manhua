package com.zhuyunjian.manhua.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.zhuyunjian.manhua.AppConstants;
import com.zhuyunjian.manhua.R;
import com.zhuyunjian.manhua.entity.User;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.simple.eventbus.EventBus;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;

@EActivity(R.layout.activity_login)
public class LoginActivity extends BaseActivity implements PlatformActionListener {

     @Click(R.id.zone_btn)
     public void zongLogin(){
         Platform qq = ShareSDK.getPlatform(QQ.NAME);
         qq.setPlatformActionListener(this);
         qq.showUser(null);
    }
    @Click(R.id.xinlang_btn)
    public void xinlangLogin(){
        Platform qq = ShareSDK.getPlatform(SinaWeibo.NAME);
        qq.setPlatformActionListener(this);
        qq.showUser(null);
//移除授权
//weibo.removeAccount(true);
    }
    @Click(R.id.tencent_btn)
    public void tencentLogin(){

    }
    @Click(R.id.renren_btn)
    public void renrenLogin(){

    }
    @Click(R.id.kaixin_btn)
    public void kaixinLogin(){

    }
    @Click(R.id.head_offline_btn)
    public void finished(){
        finish();
    }

    @Override
    public void onComplete(Platform platform, int i, HashMap<String, Object> res) {
        String name = "";
        String profile_image_url="";
        if (i == Platform.ACTION_USER_INFOR) {
            //解析部分用户资料字段
            name = res.get("nickname").toString();//用户名
            EventBus.getDefault().post(new User(name), AppConstants.USER_NAME);
            finish();
        }
    }

    @Override
    public void onError(Platform platform, int i, Throwable throwable) {

    }

    @Override
    public void onCancel(Platform platform, int i) {

    }
}
